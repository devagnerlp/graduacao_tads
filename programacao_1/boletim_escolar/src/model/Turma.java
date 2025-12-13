package model;

public class Turma {
    private int id;
    private String nome_turma;
    private Periodo periodo; // para conseguir associar turma e período na tela de pesquisa
    
    public Turma() {
    }

    public Turma(int id, String nome_turma, Periodo periodo) {
        this.id = id;
        this.nome_turma = nome_turma;
        this.periodo = periodo;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_turma() {
        return nome_turma;
    }

    public void setNome_turma(String nome_turma) {
        this.nome_turma = nome_turma;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }
    
}
