package vendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vendas.model.Fornecedor;

public class FornecedorDAO {

    public void salvar(Fornecedor fornecedor){
        String sql = "INSERT INTO fornecedor (nome_fantasia, razao_social, cnpj) VALUES (?, ?, ?)";
        
        try{
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt= conn.prepareStatement(sql);

            stmt.setString(1, fornecedor.getNomeFantasia());
            stmt.setString(2, fornecedor.getRazaoSocial());
            stmt.setString(3, fornecedor.getCnpj());

            stmt.executeUpdate();
            System.out.println("Fornecedor salvo com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        } 

    }

    public void alterar(Fornecedor fornecedor){
        String sql= "UPDATE fornecedor SET nome_fantasia=?, razao_social=?, cnpj=? WHERE id=?";

        try{
            Connection conn= Conexao.getConexao();
            PreparedStatement stmt= conn.prepareStatement(sql);
            
            stmt.setString(1, fornecedor.getNomeFantasia());
            stmt.setString(2, fornecedor.getRazaoSocial());
            stmt.setString(3, fornecedor.getCnpj());
            stmt.setInt(4, fornecedor.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {   
                System.out.println("Fornecedor alterado com sucesso!");
            } else {
                System.out.println("Fornecedor não encontrado!");
            }  

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir (Fornecedor fornecedor){
        String sql = "DELETE FROM fornecedor WHERE id=?";
        try{
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt= conn.prepareStatement(sql);

            stmt.setInt(1, fornecedor.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Fornecedor excluído com sucesso!");
            } else {
                System.out.println("Fornecedor não encontrado!");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void pesquisar(Fornecedor fornecedor){
        String sql = "SELECT * FROM fornecedor WHERE nome_fantasia=?"; 
        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt= conn.prepareStatement(sql);

            stmt.setString(1, fornecedor.getNomeFantasia());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome Fantasia: " + rs.getString("nome_fantasia"));  
                System.out.println("Razão Social: " + rs.getString("razao_social"));
                System.out.println("CNPJ: " + rs.getString("cnpj"));
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }




}
