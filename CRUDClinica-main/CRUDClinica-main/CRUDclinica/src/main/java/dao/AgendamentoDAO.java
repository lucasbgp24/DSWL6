package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Agendamento;
import model.Paciente;
import util.DatabaseConnection;

public class AgendamentoDAO {
	public void adicionarAgendamento(Agendamento agendamento) {
        String sql = "INSERT INTO agendamentos (paciente_id, medico_id, data, hora) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Preenchendo os parâmetros da consulta
            stmt.setInt(1, agendamento.getPacienteId());
            stmt.setInt(2, agendamento.getMedicoId());
            stmt.setDate(3, agendamento.getDataAgendamento());
            stmt.setTime(4, agendamento.getHoraAgendamento());

            // Executa a inserção
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Agendamento> listarAgendamentos() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM agendamentos";
        List<Agendamento> agendamentos = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Agendamento agendamento = new Agendamento();
                agendamento.setPacienteId(rs.getInt("paciente_id"));
                agendamento.setMedicoId(rs.getInt("medico_id"));
                agendamento.setDataAgendamento(rs.getDate("data_agendamento"));
                agendamento.setHoraAgendamento(rs.getTime("hora_agendamento"));
                agendamentos.add(agendamento);
            }
        } finally {
            connection.close();
        }
        return agendamentos;
    }
    public List<Paciente> buscarPacientesPorNome(String nome) {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM pacientes WHERE nome LIKE ?";
        
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return pacientes;
    }
    public List<Agendamento> listarAgendamentosComNomes() {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT a.id, p.nome AS pacienteNome, m.nome AS medicoNome, a.data, a.hora " +
                     "FROM agendamentos a " +
                     "JOIN pacientes p ON a.paciente_id = p.id " +
                     "JOIN medicos m ON a.medico_id = m.id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Agendamento agendamento = new Agendamento();
                agendamento.setId(rs.getInt("id"));
                agendamento.setPacienteNome(rs.getString("pacienteNome"));
                agendamento.setMedicoNome(rs.getString("medicoNome"));
                agendamento.setDataAgendamento(rs.getDate("data"));
                agendamento.setHoraAgendamento(rs.getTime("hora"));
                agendamentos.add(agendamento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agendamentos;
    }
    
    public boolean excluirAgendamento(int id) {
        String sql = "DELETE FROM agendamentos WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            
            return linhasAfetadas > 0;  // Retorna verdadeiro se uma linha foi excluída
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Agendamento buscarAgendamentoPorId(int id) {
        String sql = "SELECT * FROM agendamentos WHERE id = ?";
        Agendamento agendamento = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); // Define o parâmetro com o ID
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                agendamento = new Agendamento();
                agendamento.setId(rs.getInt("id"));
                agendamento.setPacienteId(rs.getInt("paciente_id"));
                agendamento.setMedicoId(rs.getInt("medico_id"));
                agendamento.setDataAgendamento(rs.getDate("data"));
                agendamento.setHoraAgendamento(rs.getTime("hora"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return agendamento;
    }

    public void atualizarAgendamento(Agendamento agendamento) {
        String sql = "UPDATE agendamentos SET paciente_id = ?, medico_id = ?, data = ?, hora = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Define os parâmetros do PreparedStatement com base nos dados do agendamento
            stmt.setInt(1, agendamento.getPacienteId());  // ID do paciente
            stmt.setInt(2, agendamento.getMedicoId());    // ID do médico
            stmt.setDate(3, agendamento.getDataAgendamento()); // Data do agendamento
            stmt.setTime(4, agendamento.getHoraAgendamento()); // Hora do agendamento
            stmt.setInt(5, agendamento.getId());          // ID do agendamento (para identificar qual editar)

            // Executa o comando de atualização
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

