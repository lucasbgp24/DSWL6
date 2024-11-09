package servlet;

import dao.PacienteDAO;
import dao.AgendamentoDAO;
import dao.MedicoDAO;
import model.Paciente;
import model.Agendamento;
import model.Medico;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@WebServlet("/AgendamentoServlet")
public class AgendamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String tipo = request.getParameter("tipo");
        String keyword = request.getParameter("keyword");

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        StringBuilder jsonOutput = new StringBuilder("[");

        if ("paciente".equalsIgnoreCase(tipo)) {
        	PacienteDAO pacienteDAO = new PacienteDAO();
            List<Paciente> pacientes = pacienteDAO.buscarPacientesPorNome(keyword);            
            for (int i = 0; i < pacientes.size(); i++) {
                Paciente paciente = pacientes.get(i);
                jsonOutput.append("{\"id\":").append(paciente.getId()).append(",")
                          .append("\"nome\":\"").append(paciente.getNome()).append("\"}");
                if (i < pacientes.size() - 1) {
                    jsonOutput.append(",");
                }
            }
        } else if ("medico".equalsIgnoreCase(tipo)) {
            MedicoDAO medicoDAO = new MedicoDAO();
            List<Medico> medicos = medicoDAO.buscarMedicosPorNome(keyword);

            for (int i = 0; i < medicos.size(); i++) {
                Medico medico = medicos.get(i);
                jsonOutput.append("{\"id\":").append(medico.getId()).append(",")
                          .append("\"nome\":\"").append(medico.getNome()).append("\"}");
                if (i < medicos.size() - 1) {
                    jsonOutput.append(",");
                }
            }
        }

        jsonOutput.append("]");
        out.print(jsonOutput.toString());
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int pacienteId = Integer.parseInt(request.getParameter("pacienteId"));
            int medicoId = Integer.parseInt(request.getParameter("medicoId"));

            Date dataAgendamento = Date.valueOf(request.getParameter("dataAgendamento"));
            Time horaAgendamento = Time.valueOf(request.getParameter("horaAgendamento") + ":00");

            // Cria um objeto Agendamento com os dados recebidos
            Agendamento agendamento = new Agendamento();
            agendamento.setPacienteId(pacienteId);
            agendamento.setMedicoId(medicoId);
            agendamento.setDataAgendamento(dataAgendamento);
            agendamento.setHoraAgendamento(horaAgendamento);

            // Chama o AgendamentoDAO para inserir o agendamento no banco
            AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
            agendamentoDAO.adicionarAgendamento(agendamento);

            // Redireciona para a tela de agendamentos
            response.sendRedirect("Agendamento.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
    }
}