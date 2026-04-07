package vendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vendas.model.Produto;

public class ProdutoDAO {

    public void salvar (Produto produto){
        String sql = "INSERT INTO produto (nome, categoria_id, preco, qtde_estoque) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getCategoria().getId());
            stmt.setDouble(3, produto.getPreco());
            stmt.setDouble(4, produto.getQtdeEstoque());

            stmt.executeUpdate();
            System.out.println("Produto salvo com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public void alterar (Produto produto){
        String sql = "UPDATE produto SET nome=?, preco=?, qtde_estoque=? WHERE id=?";
        try{
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt= conn.prepareStatement(sql);

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setDouble(3, produto.getQtdeEstoque());
            stmt.setInt(4, produto.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Produto alterado com sucesso!");
            } else {
                System.out.println("Produto não encontrado!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir (Produto produto){
        String sql = "DELETE FROM produto WHERE id=?";
        try{
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt= conn.prepareStatement(sql);

            stmt.setInt(1, produto.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Produto excluído com sucesso!");
            } else {
                System.out.println("Produto não encontrado!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pesquisar (Produto produto){
        String sql = "SELECT * FROM produto WHERE nome=?";
        try{
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt= conn.prepareStatement(sql);

            stmt.setString(1, produto.getNome());

            ResultSet rs = stmt.executeQuery();

             while (rs.next()) {  
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Preço: " + rs.getDouble("preco"));
                System.out.println("Quantidade: " + rs.getDouble("quantidade"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double verificarEstoque (Produto produto){
        String sql = "SELECT qtde_estoque FROM produto WHERE id=?";

        try{
            Connection conn= Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, produto.getId());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("qtde_estoque");
            }else {
                System.out.println("Produto não encontrado!");
                return -1; // Indica que o produto não foi encontrado
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Indica que ocorreu um erro durante a consulta
    }

    public void atualizarEstoque (Produto produto, double qtdeVendida){
        String sql = "UPDATE produto SET qtde_estoque = qtde_estoque - ? WHERE id=?";

        try{
            Connection conn= Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setDouble(1, qtdeVendida);
            stmt.setInt(2, produto.getId());

            stmt.executeUpdate();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
