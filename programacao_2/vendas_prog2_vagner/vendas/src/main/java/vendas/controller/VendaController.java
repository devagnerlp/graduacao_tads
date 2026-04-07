package vendas.controller;

import vendas.dao.ClienteDAO;
import vendas.dao.ProdutoDAO;
import vendas.dao.ProdutoVendaDAO;
import vendas.dao.VendaDAO;
import vendas.model.Produto;
import vendas.model.ProdutoVenda;
import vendas.model.Venda;

public class VendaController {
    private VendaDAO vendaDAO = new VendaDAO();//instancia o DAO para usar os métodos de acesso ao banco de dados
    private ProdutoVendaDAO produtoVendaDAO = new ProdutoVendaDAO();
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();

    public void salvar(Venda venda){ //faz duas verificações antes de salvar a venda: se o cliente já atingiu o limite de 3 compras e se há estoque suficiente para os produtos vendidos nessa venda
        int totalVendas = clienteDAO.contarVendasCliente(venda.getCliente().getId());
        if(totalVendas == -1){
            System.out.println("Erro ao consultar vendas do cliente. Venda não registrada.");
            return;
        }
        if(totalVendas > 3){
            System.out.println("Cliente já atingiu o limite de 3 compras. Venda não registrada.");
            return;
        }

        for (ProdutoVenda pv : venda.getProdutosVendidos()) { // para cada produto vendido nessa venda, verifica o estoque disponível
            double estoqueAtual= produtoDAO.verificarEstoque(pv.getProduto());

            if (estoqueAtual == -1) {
                System.out.println("Erro ao consultar estoque do produto " + pv.getProduto().getNome() + ". Venda não registrada.");
                return;
            }

            if (pv.getQtdeVendida() > estoqueAtual) {
                System.out.println("Estoque insuficiente para o produto " + pv.getProduto().getNome() +
                                   ". Estoque atual: " + estoqueAtual + ". Venda não registrada.");
                return;
            }
        }
        vendaDAO.salvar(venda); // se todas as verificações passarem, chama o método salvar do DAO para registrar a venda no banco de dados

        for(ProdutoVenda pv : venda.getProdutosVendidos()) { // para cada produto vendido, chama o método salvar do ProdutoVendaDAO para registrar a relação entre produto e venda no banco de dados e depois atualiza o estoque do produto
            produtoVendaDAO.salvar(pv); //registra o produto e a quantidade vendida nessa venda no banco de dados
            produtoDAO.atualizarEstoque(pv.getProduto(), pv.getQtdeVendida());
            // atualiza estoque o getProduto mostra o produto e getQtdeVendida informa o valor deve ser subtraído do estoque atual do produto
        }
    }

    public void alterar(Venda venda) {
        vendaDAO.alterar(venda);
    }

    public void excluir(Venda venda) {
        vendaDAO.excluir(venda);
    }

    public void pesquisar(Venda venda) {
        vendaDAO.pesquisar(venda);
    }


}
