package vendas.controller;


import vendas.dao.FornecedorDAO;
import vendas.model.Fornecedor;

public class FornecedorController {
    
    private FornecedorDAO fornecedorDAO = new FornecedorDAO();

    public void salvar (Fornecedor fornecedor){
        fornecedorDAO.salvar(fornecedor);
    }

    public void alterar (Fornecedor fornecedor){
        fornecedorDAO.alterar(fornecedor);
    }

    public void excluir (Fornecedor fornecedor){
        fornecedorDAO.excluir(fornecedor);
    }

    public void pesquisar (Fornecedor fornecedor){
        fornecedorDAO.pesquisar(fornecedor);
    }

}
