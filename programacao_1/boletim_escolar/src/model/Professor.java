package model;

public class Professor extends PessoaGen {
    private String matricula;

    
    public Professor(String matricula) {
        this.matricula = matricula;
    }

    public Professor() {
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

}

