package servlet;

import java.io.IOException;
import java.util.List;

import dao.PacienteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Paciente;

@WebServlet("/PacienteServlet")
public class PacienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private PacienteDAO pacienteDAO = new PacienteDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            pacienteDAO.deletarPaciente(id);
            response.sendRedirect("PacienteServlet");
            return;  // Adiciona o return para evitar o forward após o redirect
        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Paciente paciente = pacienteDAO.buscarPacientePorId(id);
            request.setAttribute("paciente", paciente);
            request.getRequestDispatcher("Paciente.jsp").forward(request, response);
            return;
        }

        List<Paciente> pacientes = pacienteDAO.listarPacientes();
        request.setAttribute("pacientes", pacientes);
        request.getRequestDispatcher("Paciente.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");

        Paciente paciente = new Paciente();
        paciente.setNome(nome);
        paciente.setCpf(cpf);
        paciente.setTelefone(telefone);

        if (id == null || id.isEmpty()) {
            pacienteDAO.adicionarPaciente(paciente);
        } else {
            paciente.setId(Integer.parseInt(id));
            pacienteDAO.atualizarPaciente(paciente);
        }
        response.sendRedirect("PacienteServlet");
    }
}