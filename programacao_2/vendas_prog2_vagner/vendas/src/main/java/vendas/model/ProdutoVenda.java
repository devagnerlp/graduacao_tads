package vendas.model;

public class ProdutoVenda {
    private int id;
    private Produto produto;
    private Venda venda;
    private double qtde_vendida;
    
    
    public ProdutoVenda() {
    }
    public ProdutoVenda(int id, Produto produto, Venda venda, double qtde_vendida) {
        this.id = id;
        this.produto = produto;
        this.venda = venda;
        this.qtde_vendida = qtde_vendida;
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

    public double getQtde_vendida() {
        return qtde_vendida;
    }
    public void setQtde_vendida(double qtde_vendida) {
        this.qtde_vendida = qtde_vendida;
    }


}
