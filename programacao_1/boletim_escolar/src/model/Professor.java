package model;

public class Professor extends PessoaGen {
    private int matricula;
    private String status;

    
    public Professor(int matricula, String status) {
        this.matricula = matricula;
        this.status = status;
    }

    public Professor() {
    }


    public int getMatricula() {
        return matricula;
    }
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }


}

