package model;

public class AlunoTurma {
    private int id;
    private int alunoId;
    private int turmaId;

    // Construtor padrão
    public AlunoTurma() {}

    // Construtor com parâmetros
    public AlunoTurma(int id, int alunoId, int turmaId) {
        this.id = id;
        this.alunoId = alunoId;
        this.turmaId = turmaId;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }

    public int getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(int turmaId) {
        this.turmaId = turmaId;
    }
}
