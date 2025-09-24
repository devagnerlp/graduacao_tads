public class App {

    public static void main(String[] args) throws Exception {

        Fabricante fabricante1 = new Fabricante();
            fabricante1.setId(0001);
            fabricante1.setNome("FIAT");
            fabricante1.setCidade("Belo Horizonte");
            fabricante1.setCnpj("42.345.987.0001");

        Fabricante fabricante2 = new Fabricante();
            fabricante2.setId(0001);
            fabricante2.setNome("VW");
            fabricante2.setCidade("São Paulo");
            fabricante2.setCnpj("12.432.654.0001");
            

        Veiculo veiculo1 = new Veiculo();
            veiculo1.setId(0001);
            veiculo1.setNome("Palio");
            veiculo1.setFabricante(fabricante1);
            veiculo1.setPlaca("abc1232");
            veiculo1.setAno(2005);
            veiculo1.setModelo(2005);
        
        Veiculo veiculo2 = new Veiculo();
            veiculo2.setId(0002);
            veiculo2.setNome("GOL");
            veiculo1.setFabricante(fabricante2);
            veiculo1.setPlaca("abc1232");
            veiculo2.setAno(210);
            veiculo2.setModelo(2010);

        
        veiculo1.imprimirdadosveiculo();
        veiculo2.imprimirdadosveiculo();

  
    }
}
