package vendas.controller;

import vendas.dao.CategoriaDAO;
import vendas.model.Categoria;

public class CategoriaController {

    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    public void salvar (Categoria categoria){
        categoriaDAO.salvar(categoria);
    }

    public void alterar (Categoria categoria){
        categoriaDAO.alterar(categoria);
    }

    public void excluir (Categoria categoria){
        categoriaDAO.excluir(categoria);
    }

    public void pesquisar (Categoria categoria){
        categoriaDAO.pesquisar(categoria);
    }

}
