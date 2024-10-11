package com.exemplo.web;

import java.io.IOException;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/acesso")
public class ContadorDeAcessosPorUsuario extends HttpServlet {

    private static HashMap<String, Integer> accessCounts = new HashMap<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");

        if (nome != null && !nome.isEmpty()) {
            synchronized (accessCounts) {
                accessCounts.put(nome, accessCounts.getOrDefault(nome, 0) + 1);
            }
        }

        response.setContentType("text/html");

        response.getWriter().println("<html>");
        response.getWriter().println("<head><title>Acessos por Usuário</title></head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<h1>Contador de Acessos</h1>");
        response.getWriter().println("<ul>");

        synchronized (accessCounts) {
            for (String usuario : accessCounts.keySet()) {
                response.getWriter().println("<li>" + usuario + ": " + accessCounts.get(usuario) + " acessos</li>");
            }
        }

        response.getWriter().println("</ul>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}
