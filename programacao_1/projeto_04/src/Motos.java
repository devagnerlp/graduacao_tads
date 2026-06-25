public class Motos extends Veiculo {

    private int cilindradas;
    
    public Motos () {

    }   
    public Motos(int cilindradas) {
        this.cilindradas = cilindradas;
    }

    public Motos(String nome, int ano, int modelo, String cor, String placa, boolean unico_dono, Categoria categoria,
            Garagem garagem, int cilindradas) {
        super(nome, ano, modelo, cor, placa, unico_dono, categoria, garagem);
        this.cilindradas = cilindradas;
    }



    public int getCilindradas() {
        return cilindradas;
    }
    public void setCilindradas(int cilindradas) {
        this.cilindradas = cilindradas;
    }
    
    
    public void salvar( ){
        System.out.println("Solicitação de salvamento em Motos");
    };
    
    public void alterar(){
        System.out.println("Solicitação de alteração em Motos");
    };

    public void excluir(){
        System.out.println("Solicitação de exclusão em Motos");
    };    

}
