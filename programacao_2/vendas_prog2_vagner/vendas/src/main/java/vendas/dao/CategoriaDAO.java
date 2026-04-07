package vendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vendas.model.Categoria;

public class CategoriaDAO {

    public void salvar (Categoria categoria){
        String sql = "INSERT INTO categoria (nome) VALUES (?)";

        try{
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt= conn.prepareStatement(sql);

            stmt.setString(1, categoria.getNome());

            stmt.execute();
            System.out.println("Categoria salva com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterar (Categoria categoria){
        String sql = "UPDATE categoria SET nome=? WHERE id=?";

        try{
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt= conn.prepareStatement(sql);

            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Categoria alterada com sucesso!");
            } else {
                System.out.println("Categoria não encontrada!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir (Categoria categoria){
        String sql = "DELETE FROM categoria WHERE id=?";
        try{
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt= conn.prepareStatement(sql);

            stmt.setInt(1, categoria.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Categoria excluída com sucesso!");
            } else {
                System.out.println("Categoria não encontrada!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pesquisar (Categoria categoria){
        String sql= "SELECT * FROM categoria WHERE nome=?";
        try{
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt= conn.prepareStatement(sql);

            stmt.setString(1, categoria.getNome());

            var rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome: " + rs.getString("nome"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
