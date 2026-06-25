package vendas.model;

public class Produto {
    private int id;
    private String nome;
    private Categoria categoria;
    private double preco;
    private double qtdeEstoque;
    
    public Produto() {
    }
    public Produto(int id, String nome, double preco, double qtdeEstoque, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.qtdeEstoque = qtdeEstoque;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getQtdeEstoque() {
        return qtdeEstoque;
    }
    public void setQtdeEstoque(double qtdeEstoque) {
        this.qtdeEstoque = qtdeEstoque;
    }  

}
