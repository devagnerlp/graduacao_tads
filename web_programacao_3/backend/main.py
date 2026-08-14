from fastapi import FastAPI

# A instancia da aplicacao. O titulo aparece na pagina /docs.

app = FastAPI(title="API do Corrige Provas", version="0.1.0")

# O decorador diz: "esta funcao atende GET na raiz".

@app.get("/")

def raiz():

    # Devolvemos um dicionario. O FastAPI transforma em JSON sozinho.

    return {"mensagem": "A API do meu projeto esta no ar!"}

@app.get("/mockUsers")

def mockUsers():

    # Devolvemos um dicionario. O FastAPI transforma em JSON sozinho.

    return [
        {"id": "prof-1", "nome": "Mariana Souza", "email": "professor@corrigeprovas.com", "senha": "123456", "perfil": "professor"},
        {"id": "aluno-1", "nome": "Lucas Oliveira", "email": "aluno@corrigeprovas.com", "senha": "123456", "perfil": "aluno"},
    ]