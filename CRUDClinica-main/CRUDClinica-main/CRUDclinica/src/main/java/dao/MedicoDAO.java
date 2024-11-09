package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Medico;
import util.DatabaseConnection;

public class MedicoDAO {
	public List<Medico> listarMedicos() {
	    List<Medico> medicos = new ArrayList<>();
	    
	    String sql = "SELECT * FROM Medicos";

	    try (Connection conn = DatabaseConnection.getConnection();
	
	    Statement stmt = conn.createStatement();
	    ResultSet rs = stmt.executeQuery(sql)) {
	    	while (rs.next()) {
	     
	    		Medico medico = new Medico();
				medico.setId(rs.getInt("id"));
				medico.setNome(rs.getString("nome"));
				medico.setCpf(rs.getString("cpf"));
				medico.setEspecialidade(rs.getString("especialidade"));
				medicos.add(medico);
	        }
	    } 
	  
	catch (SQLException e) {
	        e.printStackTrace();
	    }

	    System.out.println("Medicos encontrados: " + medicos.size()); // Verifica quantos pacientes foram encontrados
	    return medicos;
	}

    public void adicionarMedico (Medico medico) {
        String sql = "INSERT INTO medicos (nome, cpf, especialidade) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getCpf());
            stmt.setString(3, medico.getEspecialidade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarMedico (Medico medico) {
        String sql = "UPDATE medicos SET nome = ?, cpf = ?, especialidade = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getCpf());
            stmt.setString(3, medico.getEspecialidade());
            stmt.setInt(4, medico.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deletarMedico (int id) {
        String sql = "DELETE FROM medicos WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Medico> buscarMedicosPorNome(String nome) {
        List<Medico> medicos = new ArrayList<>();
        String sql = "SELECT id, nome FROM medicos WHERE nome LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Medico medico = new Medico();
                medico.setId(rs.getInt("id"));
                medico.setNome(rs.getString("nome"));
                medicos.add(medico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicos;
    }
    public List<Medico> getAllMedicos() {
        List<Medico> medicos = new ArrayList<>();
        String sql = "SELECT * FROM medicos";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Medico medico = new Medico();
                medico.setId(rs.getInt("id"));
                medico.setNome(rs.getString("nome"));
                // adicione mais atributos, se necessário
                medicos.add(medico);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicos;
    }
    
    public Medico buscarMedicoPorId(int id) {
        String sql = "SELECT * FROM medicos WHERE id = ?";
        Medico medico = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
            	medico = new Medico();
            	medico.setId(rs.getInt("id"));
            	medico.setNome(rs.getString("nome"));
            	medico.setCpf(rs.getString("cpf"));
            	medico.setEspecialidade(rs.getString("especialidade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medico;
    }
    
}
