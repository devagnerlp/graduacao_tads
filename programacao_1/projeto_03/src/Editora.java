public class Editora {
    private int id;
    private String nomeEditora;    

   
   
    public Editora() {
    }
    
    public Editora(int id, String nomeEditora) {
        this.id = id;
        this.nomeEditora = nomeEditora;        
    }
       
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNomeEditora() {
        return nomeEditora;
    }
    public void setNomeEditora(String nomeEditora) {
        this.nomeEditora = nomeEditora;
    }

    public void salvar( ){
        System.out.println("Pedido para Salvar");
    };
    
    public void alterar(){
        System.out.println("Pedido para alterar");
    };

    public void excluir(){
        System.out.println("Pedido para excluir");
    };

    public void pesquisar(){
        System.out.println("Pedido para pesquisar");
    };
  

}
