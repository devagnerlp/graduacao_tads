public class Categoria {

    private String descricao;

    
    public Categoria() {
    }

    public Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void salvar( ){
        System.out.println("Solicitação de salvamento em Categoria");
    };
    
    public void alterar(){
        System.out.println("Solicitação de alteração em Categoria");
    };

    public void excluir(){
        System.out.println("Solicitação de exclusão em Categoria");
    };    

}
