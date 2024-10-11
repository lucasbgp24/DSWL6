package com.exemplo.web;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addNome")
public class ArmazenarNomes extends HttpServlet {

    private static ArrayList<String> nomes = new ArrayList<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        
        if (nome != null && !nome.isEmpty()) {
            synchronized (nomes) {
                nomes.add(nome);
            }
        }

        response.setContentType("text/html");
        response.getWriter().println("<html>");
        response.getWriter().println("<head><title>Lista de Nomes</title></head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<h1>Nomes Armazenados:</h1>");
        response.getWriter().println("<ul>");

        synchronized (nomes) {
            for (String storedName : nomes) {
                response.getWriter().println("<li>" + storedName + "</li>");
            }
        }

        response.getWriter().println("</ul>");
        response.getWriter().println("<form action='addNome' method='get'>");
        response.getWriter().println("<input type='text' name='nome' placeholder='Digite um nome' required>");
        response.getWriter().println("<input type='submit' value='Adicionar Nome'>");
        response.getWriter().println("</form>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}

