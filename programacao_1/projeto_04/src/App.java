public class App {
    public static void main(String[] args) {
        
        Garagem garagem1 = new Garagem("Roda Presa", "Jataí");
        Garagem garagem2 = new Garagem("Novo car", "Jataí");

        Categoria categoria1 = new Categoria("Hatch");
        Categoria categoria2 = new Categoria("Sedã");
        
        Carros carro1 = new Carros("Gol", 2010, 2010, "Branco", "ABC-1234", false, categoria1, garagem2,1000, 5, "Direção e ar");

        Motos moto1 = new Motos("Titan", 2020, 2020, "Vermelha", "ASD-8760", false,null, garagem1, 150);


        carro1.salvar();







    }
}
