import os
from dotenv import load_dotenv
from flask import Flask, render_template, request, redirect, url_for, session, flash
from werkzeug.security import check_password_hash
from database import executar_query, criar_tabelas

load_dotenv()  # carrega o .env ANTES de fazer qualquer coisa com a SECRET_KEY

app = Flask(__name__)

# Carrega a SECRET_KEY do .env para proteger as sessões
app.secret_key = os.environ.get('SECRET_KEY')
app.config['SESSION_PERMANENT'] = False  # sessão expira ao fechar o navegador

# Cria as tabelas automaticamente ao iniciar a aplicação
criar_tabelas()

# TELA 1 - LOGIN / PAINEL INICIAL

@app.route('/', methods=['GET', 'POST']) # rota razi aceita dois tipos requisição
def index():
    # Se já estiver logado, vai direto para o painel
    if session.get('usuario'):
        return render_template('index.html', usuario=session['usuario']) # carrega a área do usuário 
    
    if request.method == 'POST': # caso o usuário tenha preenchido o dados login e clicado em logar
        nome = request.form.get('nome')
        senha = request.form.get('senha')

        # Busca o usuário no banco pelo nome e compara a senha
        sql = "SELECT id, nome, senha FROM usuarios WHERE nome = %s"
        resultado = executar_query(sql, (nome,), fetch=True)
        
        if resultado and check_password_hash(resultado[0]['senha'], senha): # Verifica se o usuário existe e se a senha confere com o hash
            session['usuario'] = resultado[0]['nome']  # Salva o nome do usuário na sessão
            flash('Login realizado com sucesso!', 'success')
            return redirect(url_for('index')) # redireciona para a mesma página, mas agora com o usuário logado
        else:
            flash('Usuário ou senha inválidos.', 'error')
            return redirect(url_for('index'))

    # se for GET exibe o formulário de login quando o usuário acessa pagina sem estar logado
    return render_template('index.html', usuario=None) 


# FAZER O LOGOUT

@app.route('/logout')
def logout():
    session.clear()  # Remove todos os dados da sessão
    flash('Você saiu do sistema.', 'success')
    return redirect(url_for('index'))


# TELA 2 - ABRIR CHAMADO (CREATE)

@app.route('/abrir_chamado', methods=['GET', 'POST']) # aceita dois tipos requisição, get para acessar a página e post para enviar os dados do formulário
def abrir_chamado():
    # Rota protegida — redireciona para login se não estiver logado
    if not session.get('usuario'):
        flash('Faça login para acessar o sistema.', 'error')
        return redirect(url_for('index'))

    if request.method == 'POST': # quando o usuário preenche o forumulário de chammado e clica em abrir chamado
        cliente   = request.form.get('cliente')
        descricao = request.form.get('descricao')
        prioridade = request.form.get('prioridade')

        sql = """
            INSERT INTO chamados (cliente, descricao, prioridade, status, statusfinal)
            VALUES (%s, %s, %s, 'Aberto', FALSE)
        """
        #o chamado já é iserido como aberto e statusfinal como falso para aparecer na fila
        executar_query(sql, (cliente, descricao, prioridade))
        flash('Chamado aberto com sucesso!', 'success')
        return redirect(url_for('fila'))

    # se for GET exibe o formulário para usuário preencher os dados do chamado
    return render_template('abrir_chamado.html')


# TELA 3 - FILA DE SUPORTE (READ)

@app.route('/fila') #só vai mostrar dados então só usa requisição GET
def fila():
    if not session.get('usuario'): #verifica se há usuário logado, se não houver redireciona para login
        flash('Faça login para acessar o sistema.', 'error')
        return redirect(url_for('index'))

    sql = """
        SELECT id, cliente, descricao, prioridade, status FROM chamados
        WHERE statusfinal = FALSE
        ORDER BY
            CASE prioridade
                WHEN 'Alta'  THEN 1
                WHEN 'Média' THEN 2
                WHEN 'Baixa' THEN 3
            END ASC,
            id ASC -- faz o mas antigo aparecer primeiro quando a prioridade for a mesma
    """
    #case para ordenar por prioridade: Alta → 1, Média → 2, Baixa → 3
    chamados = executar_query(sql, fetch=True) #true para pegar dados de volta e retornar como lista de dicionários, assim o template html acessa chamado['cliente'] em vez de chamado[0]
    return render_template('fila.html', chamados=chamados) #passa a lista de chamados para o template html renderizar a tabela de chamados


# ATENDER CHAMADO (UPDATE → Em Atendimento)

@app.route('/atender/<int:id_chamado>', methods=['POST']) #usa id do chamado (associado no html ao botão) para saber a rota certa 
def atender(id_chamado):
    if not session.get('usuario'): #verifica se há usuário logado, se não houver redireciona para login
        flash('Faça login para acessar o sistema.', 'error')
        return redirect(url_for('index'))

    sql = "UPDATE chamados SET status = 'Em Atendimento' WHERE id = %s AND statusfinal = FALSE"
    executar_query(sql, (id_chamado,))
    flash('Chamado em atendimento.', 'success')
    return redirect(url_for('fila'))


# CONCLUIR CHAMADO (UPDATE → Resolvido)

@app.route('/concluir/<int:id_chamado>', methods=['POST']) #segue mesmo padrão de atender
def concluir(id_chamado):
    if not session.get('usuario'):
        flash('Faça login para acessar o sistema.', 'error')
        return redirect(url_for('index'))

    sql = "UPDATE chamados SET status = 'Resolvido', statusfinal = TRUE WHERE id = %s AND statusfinal = FALSE"
    executar_query(sql, (id_chamado,))
    flash('Chamado concluído com sucesso!', 'success')
    return redirect(url_for('fila'))


# CANCELAR CHAMADO (DISABLE → arquiva)

@app.route('/cancelar/<int:id_chamado>', methods=['POST']) #segue mesmo padrão de atender
def cancelar(id_chamado):
    if not session.get('usuario'):
        flash('Faça login para acessar o sistema.', 'error')
        return redirect(url_for('index'))

    sql = "UPDATE chamados SET status = 'Cancelado', statusfinal = TRUE WHERE id = %s AND statusfinal = FALSE"
    executar_query(sql, (id_chamado,))
    flash('Chamado cancelado.', 'error')
    return redirect(url_for('fila'))


if __name__ == '__main__':
    app.run(debug=True, port=8000)