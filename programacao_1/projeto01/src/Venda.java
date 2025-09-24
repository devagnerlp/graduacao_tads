import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Venda {
    private String numvenda;
    private LocalDate data;
    private Double valorTotal;
    private Produto produto; // Produto é a classe ligada ao atributo produto
    public LocalDate getData() {
        return data;
    }

    
    public void setData(LocalDate data) {
        this.data = data;
    }
    public Double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }


    public String getNumvenda() {
        return numvenda;
    }


    public void setNumvenda(String numvenda) {
        this.numvenda = numvenda;
    }


public void imprimirvenda() {

System.out.println("---------- DADOS DA VENDA ---------");
        System.out.println("Número da venda: " + getNumvenda());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = getData().format(formatter);
        System.out.println("Data de venda: " + dataFormatada);
        System.out.println("\n"); 


        System.out.println("---------- DADOS DO PRODUTO ---------");        
        System.out.println("Pruduto Vendido: " + getProduto().getNome());
        System.out.println("Marca: " + getProduto().getMarca());
        System.out.println("Preço: " + getProduto().getPreco());
        System.out.println("Quantidade em estoeque: " + getProduto().getQtde());

}    



}



