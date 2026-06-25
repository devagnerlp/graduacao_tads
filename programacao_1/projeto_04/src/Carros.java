public class Carros extends Veiculo {

    private int motor_cv;
    private int marchas;
    private String opcionais;

    

    public Carros() {

    }  

    public Carros(int motor_cv, int marchas, String opcionais) {
        this.motor_cv = motor_cv;
        this.marchas = marchas;
        this.opcionais = opcionais;
    }

    public Carros(String nome, int ano, int modelo, String cor, String placa, boolean unico_dono, Categoria categoria,
            Garagem garagem, int motor_cv, int marchas, String opcionais) {
        super(nome, ano, modelo, cor, placa, unico_dono, categoria, garagem);
        this.motor_cv = motor_cv;
        this.marchas = marchas;
        this.opcionais = opcionais;
    }




    public int getMotor_cv() {
        return motor_cv;
    }
    public void setMotor_cv(int motor_cv) {
        this.motor_cv = motor_cv;
    }
    public int getMarchas() {
        return marchas;
    }
    public void setMarchas(int marchas) {
        this.marchas = marchas;
    }
    public String getOpcionais() {
        return opcionais;
    }
    public void setOpcionais(String opcionais) {
        this.opcionais = opcionais;
    }

    @Override
    public void salvar( ){
        System.out.println("Solicitação de salvamento em Carros");
        super.salvar();
    };
    
    public void alterar(){
        System.out.println("Solicitação de alteração em Carros");
    };

    public void excluir(){
        System.out.println("Solicitação de exclusão em Carros");
    };
    

}
