public class TipoCapa {
    private int id;
    private String descricao;
    
    // geração de construtor vazio e com parâmetro.    
    public TipoCapa() {
    }  
    public TipoCapa(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
