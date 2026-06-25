public class Produto {
    private String nome;
    private Double preco;
    private Integer qtde;
    private String marca;

    // métodos para a classe
    public String getNome(){ // coloca algo no atributo
        return nome; 
    }
    public void setNome (String nome) { // pega algo no atributo
        this.nome = nome;
    }

    public Double getPreco(){
        return preco;
    }
    public void setPreco (Double preco) {
        this.preco = preco;
    }

    public Integer getQtde(){
        return qtde;
    }
    public void setQtde (Integer qtde) {
        this.qtde = qtde;
    }

    public String getMarca(){
        return marca;
    }
    public void setMarca (String marca) {
        this.marca = marca;
    }

}
