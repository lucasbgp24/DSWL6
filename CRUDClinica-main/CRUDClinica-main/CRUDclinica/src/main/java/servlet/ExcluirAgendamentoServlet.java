package servlet;

import java.io.IOException;

import dao.AgendamentoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ExcluirAgendamentoServlet")
public class ExcluirAgendamentoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
            boolean sucesso = agendamentoDAO.excluirAgendamento(id);
            
            if (sucesso) {
                response.sendRedirect("ListarAgendamentosServlet");
            } else {
                response.getWriter().write("Erro ao excluir o agendamento.");
            }
        } else {
            response.getWriter().write("ID do agendamento não encontrado.");
        }
    }
}
