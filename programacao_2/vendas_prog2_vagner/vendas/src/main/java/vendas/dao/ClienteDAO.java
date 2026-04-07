package vendas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vendas.model.Cliente;  

public class ClienteDAO {
    public void salvar(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, cpf, rg, endereco, telefone) "
                + "VALUES (?, ?, ?, ?, ?)"; //? são placeholders que evitam SQL Injection

        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql); 
            // PreparedStatement  monta e executa o SQL substituindo cada ? pelo valor real
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getRg());
            stmt.setString(4, cliente.getEndereco());
            stmt.setString(5, cliente.getTelefone());

            stmt.executeUpdate(); //executeUpdate é usado para comandos SQL que modificam o banco (INSERT, UPDATE, DELETE)
            System.out.println("Cliente salvo com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void alterar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome=?, cpf=?, rg=?, endereco=?, telefone=? WHERE id=?"; // a busca é feita por id
        try {
            Connection conn = Conexao.getConexao(); //obtem a conexão com o banco
            PreparedStatement stmt = conn.prepareStatement(sql); // substitui os ? pelos valores do cliente usando os métodos setString, setInt, etc.

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getRg());
            stmt.setString(4, cliente.getEndereco());
            stmt.setString(5, cliente.getTelefone());
            stmt.setInt(6, cliente.getId());

            int linhasAfetadas = stmt.executeUpdate(); //executeUpdate com o código sql montado e retorna o número de linhas afetadas pela operação,
            // que pode ser usado para verificar se a atualização foi bem-sucedida
            if (linhasAfetadas > 0) {
                System.out.println("Cliente alterado com sucesso!");
            } else {
                System.out.println("Cliente não encontrado!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(Cliente cliente){
        String sql = "DELETE FROM cliente WHERE id=?"; // a busca é feita por id
        try {
            Connection conn = Conexao.getConexao(); //obtem a conexão com o banco
            PreparedStatement stmt = conn.prepareStatement(sql); // substitui os ? pelos valores do cliente usando os métodos setString, setInt, etc.

            stmt.setInt(1, cliente.getId());

            int linhasAfetadas = stmt.executeUpdate(); //executeUpdate com o código sql montado e retorna o número de linhas afetadas pela operação,
            // que pode ser usado para verificar se a exclusão foi bem-sucedida
            if (linhasAfetadas > 0) {
                System.out.println("Cliente excluído com sucesso!");
            } else {
                System.out.println("Cliente não encontrado!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void pesquisar(Cliente cliente){
        String sql = "SELECT * FROM cliente WHERE nome=?";
        try {
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, cliente.getNome());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {//enquanto houver linhas retornadas pelo banco, ele avança para a próxima e você extrai os dados
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("CPF: " + rs.getString("cpf"));
                System.out.println("RG: " + rs.getString("rg"));
                System.out.println("Endereço: " + rs.getString("endereco"));
                System.out.println("Telefone: " + rs.getString("telefone"));
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int contarVendasCliente(int cliente){
        String sql = "SELECT COUNT(*) FROM venda v" +
                " JOIN cliente c ON v.cliente_id = c.id" +
                " WHERE c.id = 'Flávio Vilela' AND v.cliente_id = ?";

        try{
            Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, cliente);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1); // Retorna o número de vendas
            }
        } catch (Exception e) {
            e.printStackTrace();            
            }
        return -1; // Indica que ocorreu um erro
        }
        
    

}
