package vendas.controller;

import vendas.dao.ProdutoDAO;
import vendas.model.Produto;

public class ProdutoController {

    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public void salvar (Produto produto){
        produtoDAO.salvar(produto);
    }
    
    public void alterar (Produto produto){
        produtoDAO.alterar(produto);
    }

    public void excluir (Produto produto){
        produtoDAO.excluir(produto);
    }

    public void pesquisar (Produto produto){
        produtoDAO.pesquisar(produto);
    }

}
