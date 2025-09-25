public class Livro {
    private int id;
    private String nome;
    private String resenha;
    private int edicao;
    private int anoPublicacao;
    private int nPaginas;
    private TipoCapa tipoCapa;
    private Editora editora; 
    private Autor autor;

    public Livro() {
    }

    public Livro(int id, String nome, String resenha, int edicao, int anoPublicacao, int nPaginas, TipoCapa tipoCapa,
            Editora editora, Autor autor) {
        this.id = id;
        this.nome = nome;
        this.resenha = resenha;
        this.edicao = edicao;
        this.anoPublicacao = anoPublicacao;
        this.nPaginas = nPaginas;
        this.tipoCapa = tipoCapa;
        this.editora = editora;
        this.autor = autor;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getResenha() {
        return resenha;
    }
    public void setResenha(String resenha) {
        this.resenha = resenha;
    }
    public int getEdicao() {
        return edicao;
    }
    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }
    public int getAnoPublicacao() {
        return anoPublicacao;
    }
    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
    public int getnPaginas() {
        return nPaginas;
    }
    public void setnPaginas(int nPaginas) {
        this.nPaginas = nPaginas;
    }
    public TipoCapa getTipoCapa() {
        return tipoCapa;
    }
    public void setTipoCapa(TipoCapa tipoCapa) {
        this.tipoCapa = tipoCapa;
    }
    public Editora getEditora() {
        return editora;
    }
    public void setEditora(Editora editora) {
        this.editora = editora;
    }
    public Autor getAutor() {
        return autor;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    
    


    

    
    
    


     


    

    


    
    

    

}
