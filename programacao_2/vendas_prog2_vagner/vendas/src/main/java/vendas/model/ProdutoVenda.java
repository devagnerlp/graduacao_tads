package vendas.model;

public class ProdutoVenda {
    private int id;
    private Produto produto;
    private Venda venda;
    private double qtdeVendida;
    
    
    public ProdutoVenda() {
    }
    public ProdutoVenda(int id, Produto produto, Venda venda, double qtdeVendida) {
        this.id = id;
        this.produto = produto;
        this.venda = venda;
        this.qtdeVendida = qtdeVendida;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }
    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public double getQtdeVendida() {
        return qtdeVendida;
    }
    public void setQtdeVendida(double qtdeVendida) {
        this.qtdeVendida = qtdeVendida;
    }


}
