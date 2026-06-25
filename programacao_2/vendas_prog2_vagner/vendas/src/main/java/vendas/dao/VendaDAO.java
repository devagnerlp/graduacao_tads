package vendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vendas.model.Venda;

public class VendaDAO {
    public void salvar (Venda venda){
        String sql = "INSERT INTO venda (data_venda, valor_total, cliente_id) VALUES (?, ?, ?)";
        try{
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt= conn.prepareStatement(sql);

            stmt.setDate(1, java.sql.Date.valueOf(venda.getDataVenda()));// converte LocalDate para java.sql.Date
            stmt.setDouble(2, venda.getValorTotal());
            stmt.setInt(3, venda.getCliente().getId());

            stmt.execute();
            
            System.out.println("Venda registrada com sucesso!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterar (Venda venda){
        String sql ="UPDATE venda SET data_venda=?, valor_total=?, cliente_id=? WHERE id=?";

        try{
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt= conn.prepareStatement(sql);

            stmt.setDate(1, java.sql.Date.valueOf(venda.getDataVenda()));
            stmt.setDouble(2, venda.getValorTotal());
            stmt.setInt(3, venda.getCliente().getId());
            stmt.setInt(4, venda.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Venda alterada com sucesso!");
            } else {
                System.out.println("Nenhuma venda encontrada com o ID fornecido.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir (Venda venda){
        String sql= "DELETE FROM venda WHERE id=?";
        try{
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt= conn.prepareStatement(sql);

            stmt.setInt(1, venda.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Venda excluída com sucesso!");
            } else {
                System.out.println("Nenhuma venda encontrada com o ID fornecido.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pesquisar (Venda venda){
        String sql1 = "SELECT v.id, v.data_venda, v.valor_total, c.nome AS cliente " +
              "FROM venda v " +
              "JOIN cliente c ON v.cliente_id = c.id " +
              "WHERE v.id = ?";
        
        String sql2 = "SELECT p.nome AS produto, pv.qtde_vendida " +
              "FROM produtovenda pv " +
              "JOIN produto p ON pv.produto_id = p.id " +
              "WHERE pv.venda_id = ?";

        try{
            Connection conn = Conexao.getConexao();
            
            PreparedStatement stmt1 = conn.prepareStatement(sql1);
            stmt1.setInt(1, venda.getId());
            ResultSet rs1 = stmt1.executeQuery();

            PreparedStatement stmt2 = conn.prepareStatement(sql2);
            stmt2.setInt(1, venda.getId());
            ResultSet rs2 = stmt2.executeQuery();

            while (rs1.next()) {
            System.out.println("ID da Venda: " + rs1.getInt("id"));
            System.out.println("Data: " + rs1.getDate("data_venda").toLocalDate());
            System.out.println("Valor Total: " + rs1.getDouble("valor_total"));
            System.out.println("Cliente: " + rs1.getString("cliente"));
            System.out.println("Produtos:");
            System.out.println("-----------------------------");
            }

            while (rs2.next()) {
                System.out.println("Produto: " + rs2.getString("produto"));
                System.out.println("Quantidade: " + rs2.getDouble("qtde_vendida"));
                System.out.println("-----------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
