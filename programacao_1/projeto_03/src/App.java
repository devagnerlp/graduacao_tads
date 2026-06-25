public class App {
    public static void main(String[] args) {

        TipoCapa tipocapa1 = new TipoCapa();
            tipocapa1.setId(0001);
            tipocapa1.setDescricao("Comum");
        

        Autor autor1 = new Autor();
            autor1.setId(0001);
            autor1.setNome("João de Souza");
            autor1.setCidade("Goiânia");

        
        Editora editora1 = new Editora();
            editora1.setId(0001);
            editora1.setNomeEditora("Goianiense");        


        Livro livro1 = new Livro();
            livro1.setId(0001);
            livro1.setNome("Até agora");
            livro1.setResenha("Livro de aventura");
            livro1.setnPaginas(94);
            livro1.setEdicao(1);
            livro1.setAnoPublicacao(2024);
            livro1.setAutor(autor1);
            livro1.setEditora(editora1);
            livro1.setTipoCapa(tipocapa1);

    
    autor1.pesquisar();





    }
}
