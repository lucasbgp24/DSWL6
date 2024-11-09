package dao;

import model.Paciente;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {
	public List<Paciente> listarPacientes() {
	    List<Paciente> pacientes = 
	 
	new ArrayList<>();
	    String sql = "SELECT * FROM pacientes";

	try (Connection conn = DatabaseConnection.getConnection();
	
	Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            
	       
	Paciente paciente = new Paciente();
	            paciente.setId(rs.getInt("id"));
	            paciente.setNome(rs.getString("nome"));
	            paciente.setCpf(rs.getString("cpf"));
	            paciente.setTelefone(rs.getString("telefone"));
	            pacientes.add(paciente);
	        }
	    } 
	  
	catch (SQLException e) {
	        e.printStackTrace();
	    }

	    System.out.println("Pacientes encontrados: " + pacientes.size()); // Verifica quantos pacientes foram encontrados
	    return pacientes;
	}

    public void adicionarPaciente(Paciente paciente) {
        String sql = "INSERT INTO pacientes (nome, cpf, telefone) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getTelefone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarPaciente(Paciente paciente) {
        String sql = "UPDATE pacientes SET nome = ?, cpf = ?, telefone = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getTelefone());
            stmt.setInt(4, paciente.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Paciente buscarPacientePorId(int id) {
        String sql = "SELECT * FROM pacientes WHERE id = ?";
        Paciente paciente = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setTelefone(rs.getString("telefone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paciente;
    }


    public void deletarPaciente(int id) {
        String sql = "DELETE FROM pacientes WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Paciente> buscarPacientesPorNome(String nome) {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT id, nome FROM pacientes WHERE nome LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pacientes;
    }
    public List<Paciente> getAllPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM pacientes";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getInt("id"));
                paciente.setNome(rs.getString("nome"));
                // adicione mais atributos, se necessário
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pacientes;
    }
}