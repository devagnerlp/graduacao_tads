package vendas.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {
    public static void main(String[] args) {
        try {
            Connection conn = Conexao.getConexao();
            if (conn != null) {
                System.out.println("Conexão bem-sucedida!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        } finally {
            Conexao.fecharConexao();
            System.out.println("Conexão fechada.");
        }
    }
}