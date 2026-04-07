package vendas.controller;

import vendas.dao.FornecedorProdutoDAO;
import vendas.model.FornecedorProduto;

public class FornecedorProdutoController {

    private FornecedorProdutoDAO fornecedorProdutoDAO = new FornecedorProdutoDAO();

        public void salvar (FornecedorProduto fornecedorProduto){
            fornecedorProdutoDAO.salvar(fornecedorProduto);
        }

        public void excluir (FornecedorProduto fornecedorProduto){
            fornecedorProdutoDAO.excluir(fornecedorProduto);
        }

        public void pesquisar (FornecedorProduto fornecedorProduto){
            fornecedorProdutoDAO.pesquisar(fornecedorProduto);
        }


}
