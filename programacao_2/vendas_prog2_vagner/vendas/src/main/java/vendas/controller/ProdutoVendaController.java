package vendas.controller;

import vendas.dao.ProdutoVendaDAO;
import vendas.model.ProdutoVenda;


public class ProdutoVendaController {

    private ProdutoVendaDAO produtoVendaDAO = new ProdutoVendaDAO();

    public void salvar (ProdutoVenda produtoVenda){
        produtoVendaDAO.salvar(produtoVenda);
    }

    public void excluir (ProdutoVenda produtoVenda){
        produtoVendaDAO.excluir(produtoVenda);
    }

    public void pesquisar (ProdutoVenda produtoVenda){
        produtoVendaDAO.pesquisar(produtoVenda);
    }

}
