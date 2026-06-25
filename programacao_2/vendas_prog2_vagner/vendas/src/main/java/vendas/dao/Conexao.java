package vendas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:postgresql://localhost:5432/vendas_prog2_Vagner";
    private static final String usuario = "postgres";
    private static final String senha = "user123";

    private static Connection conexao;

    public static Connection getConexao() throws SQLException { //throws AVISA que pode dar erro para que quem chamar o método saiba que precisa tratar esse erro com try-catch
        if (conexao == null || conexao.isClosed()) { /*só cria uma nova conexão se ela ainda não existe ou se já foi fechada */
            conexao = DriverManager.getConnection(url, usuario, senha);
        }
        return conexao;
    }

    public static void fecharConexao(){
        if (conexao != null){
            try{
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
