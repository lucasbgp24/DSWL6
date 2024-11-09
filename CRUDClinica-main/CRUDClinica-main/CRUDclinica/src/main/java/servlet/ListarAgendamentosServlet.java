package servlet;

import java.io.IOException;
import java.util.List;

import dao.AgendamentoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Agendamento;

@WebServlet("/ListarAgendamentosServlet")
public class ListarAgendamentosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
        List<Agendamento> listaAgendamentos = agendamentoDAO.listarAgendamentosComNomes();
        
        // Define a lista de agendamentos como atributo para a JSP
        request.setAttribute("listaAgendamentos", listaAgendamentos);
        
        // Encaminha para a página index.jsp
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}