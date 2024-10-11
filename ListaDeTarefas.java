package com.exemplo.web;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/tarefas")
public class ListaDeTarefas extends HttpServlet {

    private static ArrayList<String> tarefas = new ArrayList<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        response.getWriter().println("<html>");
        response.getWriter().println("<head><title>Lista de Tarefas</title></head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<h1>Adicionar Tarefa</h1>");
        response.getWriter().println("<form action='tarefas' method='POST'>");
        response.getWriter().println("Tarefa: <input type='text' name='tarefa' required><br>");
        response.getWriter().println("<input type='submit' value='Adicionar Tarefa'>");
        response.getWriter().println("</form>");

        response.getWriter().println("<h2>Tarefas Cadastradas:</h2>");
        response.getWriter().println("<ul>");

        synchronized (tarefas) {
            for (String tarefa : tarefas) {
                response.getWriter().println("<li>" + tarefa + "</li>");
            }
        }

        response.getWriter().println("</ul>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tarefa = request.getParameter("tarefa");

        if (tarefa != null && !tarefa.isEmpty()) {
            synchronized (tarefas) {
                tarefas.add(tarefa);
            }
        }

        doGet(request, response);
    }
}

