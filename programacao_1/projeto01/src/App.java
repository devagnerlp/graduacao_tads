import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class App {
    public static void main(String[] args) throws Exception {
        
       

        Produto produto1 = new Produto(); // crriado um objeto produto1 da classe Produto (nomeado lado esquerdo)
            produto1.setNome("Macarrão");
            produto1.setMarca("Renata");
            produto1.setPreco(3.99);
            produto1.setQtde(20);

        Produto produto2 = new Produto(); // crriado um objeto produto2 da classe Produto (nomeado lado esquerdo)
            produto2.setNome("Extrato de tomate");
            produto2.setMarca("Elefante");
            produto2.setPreco(5.99);
            produto2.setQtde(15);



        Venda venda = new Venda(); 
            
            venda.setNumvenda("123456");
            venda.setProduto(produto2);
            venda.setData(LocalDate.now());
            
        
        venda.imprimirvenda();  
             
       


    }
}


    /* exemplo para dar entrada em um produto manualmente
        prod.setNome("Fita Isolante");
        prod.setMarca("3M");
        prod.setPreco(5.5);
        prod.setQtde(56);
        System.out.println("Nome: " + prod.getNome());
        System.out.println("Marca: " + prod.getMarca());
        System.out.println("Preço: " + prod.getPreco());
        System.out.println("Qtde: " + prod.getQtde());
    */