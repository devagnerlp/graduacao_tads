public class Autor {

    private int id;
    private String nome;
    private String Cidade;
    
    
    public Autor() {
    }
    public Autor(int id, String nome, String cidade) {
        this.id = id;
        this.nome = nome;
        Cidade = cidade;
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
    public String getCidade() {
        return Cidade;
    }
    public void setCidade(String cidade) {
        Cidade = cidade;
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
