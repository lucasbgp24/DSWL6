package servlet;

import java.io.IOException;
import java.util.List;

import dao.MedicoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Medico;

@WebServlet("/MedicoServlet")
public class MedicoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private MedicoDAO medicoDAO = new MedicoDAO();

    public MedicoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtendo a ação solicitada do parâmetro da requisição
        String action = request.getParameter("action");
        
        // Verifica se a ação é para deletar um paciente
        if ("delete".equals(action)) {
          
        	int id = Integer.parseInt(request.getParameter("id")); // Obtém o ID do paciente a ser deletado
            medicoDAO.deletarMedico(id); 
    // Chama o método para deletar o paciente
        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Medico medico = medicoDAO.buscarMedicoPorId(id);
            request.setAttribute("medico", medico);
            request.getRequestDispatcher("Medico.jsp").forward(request, response);
            return;
        }

        // Obtém a lista de pacientes cadastrados
        List<Medico> medicos = medicoDAO.listarMedicos();
        // Define a lista de pacientes como um atributo da requisição
        request.setAttribute("medicos", medicos);
        
        // Encaminha a requisição para o JSP
        request.getRequestDispatcher("Medico.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String especialidade = request.getParameter("especialidade");

        Medico medico = new Medico();
        medico.setNome(nome);
        medico.setCpf(cpf);
        medico.setEspecialidade(especialidade);

        if (id == null || id.isEmpty()) {
            medicoDAO.adicionarMedico(medico);
        } else {
            medico.setId(Integer.parseInt(id));
            medicoDAO.atualizarMedico(medico);
        }
        response.sendRedirect("MedicoServlet");
    }

}
