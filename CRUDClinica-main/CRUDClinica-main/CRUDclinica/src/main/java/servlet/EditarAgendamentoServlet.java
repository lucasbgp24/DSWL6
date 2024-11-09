package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import dao.AgendamentoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Agendamento;

/**
 * Servlet implementation class EditarAgendamentoServlet
 */
@WebServlet("/EditarAgendamentoServlet")
public class EditarAgendamentoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AgendamentoDAO agendamentoDAO = new AgendamentoDAO();

    // Método GET: carrega o formulário com dados do agendamento existente
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id")); // ID do agendamento
        Agendamento agendamento = agendamentoDAO.buscarAgendamentoPorId(id); // Busca o agendamento no banco

        if (agendamento != null) {
            request.setAttribute("agendamento", agendamento); // Passa o agendamento para o JSP
            request.getRequestDispatcher("/editarAgendamento.jsp").forward(request, response); // Exibe o JSP de edição
        } else {
            response.sendRedirect("index.jsp"); // Se não encontrado, redireciona para a tela principal
        }
    }

    // Método POST: atualiza os dados do agendamento no banco de dados
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int pacienteId = Integer.parseInt(request.getParameter("pacienteId"));
        int medicoId = Integer.parseInt(request.getParameter("medicoId"));
        Date dataAgendamento = Date.valueOf(request.getParameter("data")); // Converte para Date

        // Adiciona ":00" ao final do valor de hora para seguir o formato "HH:mm:ss"
        String horaString = request.getParameter("hora") + ":00"; 
        Time horaAgendamento = Time.valueOf(horaString); // Converte para Time no formato correto

        // Cria um novo objeto Agendamento com os dados atualizados
        Agendamento agendamento = new Agendamento();
        agendamento.setId(id);
        agendamento.setPacienteId(pacienteId);
        agendamento.setMedicoId(medicoId);
        agendamento.setDataAgendamento(dataAgendamento);
        agendamento.setHoraAgendamento(horaAgendamento);

        // Atualiza o agendamento no banco de dados
        agendamentoDAO.atualizarAgendamento(agendamento);

        // Redireciona de volta para a tela principal após a edição
        response.sendRedirect("index.jsp");
    }
}
