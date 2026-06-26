import os
import pg8000
from dotenv import load_dotenv

# Carrega as variáveis definidas no arquivo .env para que possam ser acessadas via os.environ.get()
load_dotenv()


# CONEXÃO COM O BANCO DE DADOS

def get_db_connection(): #Retorna uma conexão ativa com o PostgreSQL usando as credenciais do .env.
    try:
        return pg8000.connect(
            host=os.environ.get('DB_HOST'),         # Endereço do servidor do banco
            database=os.environ.get('DB_NAME'),     # Nome do banco de dados
            user=os.environ.get('DB_USER'),         # Usuário do banco
            password=os.environ.get('DB_PASSWORD'), # Senha do banco
            port=int(os.environ.get('DB_PORT'))     # Porta (convertida para inteiro)
        )
    except Exception as e:
        # Se a conexão falhar, exibe o erro e retorna None
        print(f"Erro de conexão: {e}")
        return None

# EXECUTOR CENTRAL DE QUERIES

def executar_query(sql, params=None, fetch=False):
    # Executa qualquer instrução SQL de forma segura.
    #- sql: instrução SQL a ser executada
    #- params: parâmetros que substituem os %s no SQL (evita SQL Injection)
    #- fetch: se True, retorna os dados como lista de dicionários
    #         se False, apenas confirma a operação (INSERT, UPDATE, DELETE)    
    conn = get_db_connection()
    if not conn: return None  # Aborta se não conseguiu conectar
    response = None

    try:
        cursor = conn.cursor()        
        cursor.execute(sql, params) if params else cursor.execute(sql) # Executa com parâmetros (seguro) ou sem parâmetros

        if fetch:            
            brutos = cursor.fetchall() # Busca todos os registros retornados pelo SELECT            
            colunas = [col[0] for col in cursor.description]
            # Mapeia cada linha para um dicionário {coluna: valor}
            # Assim o template HTML acessa chamado['cliente'] em vez de chamado[0]
            response = [dict(zip(colunas, linha)) for linha in brutos]
        else:
            # Confirma a transação no banco (necessário para INSERT/UPDATE/DELETE)
            conn.commit()
            response = True
        cursor.close()

    except Exception as e:
        # Em caso de erro, exibe a causa e desfaz a transação
        print(f"ERRO SQL: {e}")
        conn.rollback()
    finally:
        # Sempre fecha a conexão ao final, com ou sem erro
        if conn:
            conn.close()
    return response



# CRIAÇÃO DAS TABELAS, é executado uma vez para verificar existência das tabelas no banco.

def criar_tabelas(): #Cria as tabelas usuarios e chamados caso ainda não existam no banco.

    # Tabela de usuários do sistema
    executar_query("""
        CREATE TABLE IF NOT EXISTS usuarios (
            id SERIAL PRIMARY KEY,
            nome VARCHAR(100) NOT NULL UNIQUE,  -- Nome único para login
            senha VARCHAR(255) NOT NULL          -- Senha do usuário
        )
    """)

    # Tabela principal de chamados de suporte - o CHECK garante que apenas valores válidos sejam aceitos no banco
    executar_query("""
        CREATE TABLE IF NOT EXISTS chamados (
            id SERIAL PRIMARY KEY,
            cliente VARCHAR(100) NOT NULL,
            descricao TEXT NOT NULL,
            prioridade VARCHAR(10) NOT NULL
                CHECK (prioridade IN ('Baixa', 'Média', 'Alta')),
            status VARCHAR(20) NOT NULL DEFAULT 'Aberto'
                CHECK (status IN ('Aberto', 'Em Atendimento', 'Resolvido', 'Cancelado')),
            statusfinal BOOLEAN NOT NULL DEFAULT FALSE  -- FALSE = ativo, TRUE = arquivado
        )
    """)

if __name__ == "__main__":
    conn = get_db_connection()
    if conn:
        print("✅ Conexão bem-sucedida!")
        conn.close()
    else:
        print("❌ Falha na conexão. Verifique o .env")