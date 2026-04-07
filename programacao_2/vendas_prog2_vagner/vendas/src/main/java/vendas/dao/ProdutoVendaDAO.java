package vendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vendas.model.ProdutoVenda;

public class ProdutoVendaDAO {

    public void salvar (ProdutoVenda produtoVenda){
        String sql = "INSERT INTO produtovenda (qtde_vendida, produto_id, venda_id) VALUES (?, ?, ?)";
        try{
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt= conn.prepareStatement(sql);

            stmt.setDouble(1, produtoVenda.getQtdeVendida());
            stmt.setInt(2, produtoVenda.getProduto().getId());
            stmt.setInt(3, produtoVenda.getVenda().getId());

            stmt.execute();
            
            System.out.println("Produto adicionado à venda com sucesso!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pesquisar (ProdutoVenda produtoVenda){
        String sql = "SELECT * FROM produtovenda WHERE venda_id=?";
        try{
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt= conn.prepareStatement(sql);

            stmt.setInt(1, produtoVenda.getVenda().getId());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Quantidade Vendida: " + rs.getDouble("qtde_vendida"));
                System.out.println("Produto ID: " + rs.getInt("produto_id"));
                System.out.println("Venda ID: " + rs.getInt("venda_id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir (ProdutoVenda produtoVenda){
        String sql = "DELETE FROM produtovenda WHERE id=?";
        try{
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt= conn.prepareStatement(sql);

            stmt.setInt(1, produtoVenda.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Produto removido da venda com sucesso!");
            } else {
                System.out.println("O produto não faz parte da venda!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
