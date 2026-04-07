package vendas.controller;

import vendas.dao.ClienteDAO;
import vendas.model.Cliente;

public class ClienteController {
    
    private ClienteDAO dao = new ClienteDAO();

    public void salvar(Cliente cliente) {
        dao.salvar(cliente);

    }

    public void alterar(Cliente cliente) {
        dao.alterar(cliente);
    }

    public void excluir(Cliente cliente) {
        dao.excluir(cliente);
    }

    public void pesquisar(Cliente cliente) {
        dao.pesquisar(cliente);
    }




}

