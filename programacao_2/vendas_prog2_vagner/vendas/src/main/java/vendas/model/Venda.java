package vendas.model;

import java.time.LocalDate;
import java.util.List;

public class Venda {
    private int id;
    private LocalDate dataVenda;
    private double valorTotal;
    private Cliente cliente;
    private List<ProdutoVenda> produtosVendidos; // cria lista de produtos vendidos nessa venda

    public Venda() {
    }
    public Venda(int id, LocalDate dataVenda, double valorTotal, Cliente cliente, List<ProdutoVenda> produtosVendidos) {
        this.id = id;
        this.dataVenda = dataVenda;
        this.valorTotal = valorTotal;
        this.cliente = cliente;
        this.produtosVendidos = produtosVendidos;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }
    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ProdutoVenda> getProdutosVendidos() {
        return produtosVendidos;
    }
    public void setProdutosVendidos(List<ProdutoVenda> produtosVendidos) {
        this.produtosVendidos = produtosVendidos;
    }

}
