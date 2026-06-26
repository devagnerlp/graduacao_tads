# Sistema de Gerenciamento de Chamados

![Python](https://img.shields.io/badge/Python-3.x-3776AB?logo=python&logoColor=white)
![Flask](https://img.shields.io/badge/Flask-Framework-000000?logo=flask)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-336791?logo=postgresql&logoColor=white)
![Status](https://img.shields.io/badge/Status-Em%20desenvolvimento-orange)

Aplicação web desenvolvida em **Python** com **Flask** para gerenciamento de chamados, com integração a banco de dados **PostgreSQL**.

O sistema permite **abrir chamados**, **visualizar a fila** e **alterar o status** dos atendimentos de forma simples, organizada e funcional.

---

## Funcionalidades

- Abertura de novos chamados
- Listagem dos chamados em fila
- Atendimento de chamados
- Conclusão de chamados
- Cancelamento de chamados
- Conexão com banco de dados PostgreSQL
- Uso de variáveis de ambiente para credenciais sensíveis
- Criação automática das tabelas no banco na primeira execução

---

## Tecnologias utilizadas

- **Python 3**
- **Flask**
- **PostgreSQL**
- **pg8000**
- **python-dotenv**
- **Jinja2**
- **HTML**

---

## Estrutura do projeto

```text
GERENCIAMENTOCHAMADOS/
├── app.py
├── database.py
├── requirements.txt
├── .gitignore
├── .env
├── README.md
├── templates/
│   ├── index.html
│   ├── fila.html
│   └── abrir_chamado.html
└── venv/
```

> Observação: a pasta `venv/` e o arquivo `.env` **não devem ser enviados para o GitHub**.  
> Eles estão no `.gitignore` para evitar exposição de dependências locais e credenciais.

---

## Pré-requisitos

- **Python 3**
- **Git**
- **PostgreSQL**
- Um terminal (PowerShell, CMD, Linux ou Mac)

---

## Instalação

### 1. Clonar o repositório

```bash
git clone https://github.com/devagnerlp/graduacao_tads.git
```

### 2. Acessar a pasta do projeto

```bash
cd graduacao_tads/web_programacao_2/gerenciamentochamados
```

### 3. Criar e ativar a virtualenv

No Windows:

```bash
python -m venv venv
venv\Scripts\activate
```

No Linux/Mac:

```bash
python3 -m venv venv
source venv/bin/activate
```

### 4. Instalar as dependências

```bash
pip install -r requirements.txt
```

---

## Configuração do arquivo `.env`

Crie um arquivo chamado `.env` na raiz do projeto com as credenciais do banco de dados:

```env
DB_HOST=localhost
DB_NAME=seu_banco
DB_USER=seu_usuario
DB_PASSWORD=sua_senha
DB_PORT=5432
```

---

## Banco de dados

O projeto utiliza **PostgreSQL** como banco de dados.  
As tabelas são criadas **automaticamente** pelo arquivo `database.py` na primeira execução da aplicação — não é necessário rodar scripts SQL manualmente.

Certifique-se de que:

- o banco de dados já existe no PostgreSQL
- as credenciais do arquivo `.env` estão corretas

---

## Execução

Depois de configurar o ambiente e o banco de dados, execute a aplicação com:

```bash
python app.py
```

A aplicação ficará disponível no navegador, normalmente em:

```text
http://127.0.0.1:5000
```

---

## Rotas principais

- `/` — página inicial
- `/fila` — exibe a fila de chamados
- `/abrir_chamado` — formulário e processamento de abertura
- `/atender/<id>` — atendimento do chamado
- `/concluir/<id>` — conclusão do chamado
- `/cancelar/<id>` — cancelamento do chamado

---

## Boas práticas adotadas

- Uso de `.gitignore` para excluir arquivos sensíveis e desnecessários (`venv/`, `.env`, `__pycache__/`)
- Separação entre lógica de aplicação (`app.py`) e acesso ao banco (`database.py`)
- Uso de `requirements.txt` para documentar dependências
- Organização da interface em templates HTML

---

## Autor

Desenvolvido por **Vagner Paulino** como trabalho da disciplina programação WEB II.