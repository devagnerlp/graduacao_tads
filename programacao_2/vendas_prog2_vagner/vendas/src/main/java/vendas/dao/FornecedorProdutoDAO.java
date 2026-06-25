package vendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vendas.model.FornecedorProduto;

public class FornecedorProdutoDAO {

    public void salvar (FornecedorProduto fornecedorProduto){
        String sql = "INSERT INTO fornecedorproduto (fornecedor_id, produto_id) VALUES (?, ?)";
        try{
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt= conn.prepareStatement(sql);

            stmt.setInt(1, fornecedorProduto.getFornecedor().getId());
            stmt.setInt(2, fornecedorProduto.getProduto().getId());

            stmt.execute();
            
            System.out.println("Produto associado ao fornecedor com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir (FornecedorProduto fornecedorProduto){
        String sql = "DELETE FROM fornecedorproduto WHERE fornecedor_id=? AND produto_id=?";
        try{
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt= conn.prepareStatement(sql);

            stmt.setInt(1, fornecedorProduto.getFornecedor().getId());
            stmt.setInt(2, fornecedorProduto.getProduto().getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Produto desassociado do fornecedor com sucesso!");
            } else {
                System.out.println("O produto não estava associado ao fornecedor!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pesquisar (FornecedorProduto fornecedorProduto){
        String sql = "SELECT * FROM fornecedorproduto WHERE fornecedor_id=?";
        try{
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt= conn.prepareStatement(sql);

            stmt.setInt(1, fornecedorProduto.getFornecedor().getId());            

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Fornecedor ID: " + rs.getInt("fornecedor_id"));
                System.out.println("Produto ID: " + rs.getInt("produto_id"));
            }


        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
