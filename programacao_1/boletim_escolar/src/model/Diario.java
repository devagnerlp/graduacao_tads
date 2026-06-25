package model;

public class Diario {
    private int id;
    private String status;
    private Aluno aluno;
    private Disciplina disciplina;
    private Nota nota;
    private Turma turma;
    private Periodo periodo;
    
    public Diario() {
    }
    
    public Diario(int id, String status, Aluno aluno, Disciplina disciplina, Nota nota, Turma turma, Periodo periodo) {
        this.id = id;
        this.status = status;
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.nota = nota;
        this.turma = turma;
        this.periodo = periodo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }  
    
}
