public class Garagem {

    private String nome;
    private String cidade;
    
    public Garagem() {
    }
    public Garagem(String nome, String cidade) {
        this.nome = nome;
        this.cidade = cidade;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void salvar( ){
        System.out.println("Solicitação de salvamento em Garagem");
    };
    
    public void alterar(){
        System.out.println("Solicitação de alteração em Garagem");
    };

    public void excluir(){
        System.out.println("Solicitação de exclusão em Garagem");
    };    

}
