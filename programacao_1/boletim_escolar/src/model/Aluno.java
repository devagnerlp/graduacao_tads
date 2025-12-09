package model;

public class Aluno extends PessoaGen {
    private int matricula;
    private String nome_mae;
    private String nome_pai;
    
    
    public Aluno() {
    }
    
    public Aluno(int matricula, String nome_mae, String nome_pai) {
        this.matricula = matricula;
        this.nome_mae = nome_mae;
        this.nome_pai = nome_pai;
    }

    public int getMatricula() {
        return matricula;
    }


    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }


    public String getNome_mae() {
        return nome_mae;
    }


    public void setNome_mae(String nome_mae) {
        this.nome_mae = nome_mae;
    }


    public String getNome_pai() {
        return nome_pai;
    }


    public void setNome_pai(String nome_pai) {
        this.nome_pai = nome_pai;
    } 

}
