package model;

public class Aluno extends PessoaGen {
    private int matricula;
    private String nome_mae;
    private String nome_pai;
    private String data_nascimento;
    private String status;
    
    
    public Aluno() {
    }

    public Aluno(int matricula, String nome_mae, String nome_pai, String data_nascimento, String status) {
        this.matricula = matricula;
        this.data_nascimento = data_nascimento;
        this.nome_mae = nome_mae;
        this.nome_pai = nome_pai;
        this.status = status;
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


    public String getData_nascimento() {
        return data_nascimento;
    }
    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
    

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
