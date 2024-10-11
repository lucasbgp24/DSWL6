package com.exemplo.web;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/nomeManagement")
public class RemoverNomes extends HttpServlet {

    private static ArrayList<String> nomes = new ArrayList<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeParaRemover = request.getParameter("nome");
        
        if (nomeParaRemover != null && !nomeParaRemover.isEmpty()) {
            synchronized (nomes) {
                nomes.remove(nomeParaRemover);
            }
        }

        response.setContentType("text/html");

        response.getWriter().println("<html>");
        response.getWriter().println("<head><title>Gerenciamento de Nomes</title></head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<h1>Gerenciamento de Nomes</h1>");
        response.getWriter().println("<form action='nomeManagement' method='POST'>");
        response.getWriter().println("Nome: <input type='text' name='nome' required><br>");
        response.getWriter().println("<input type='submit' value='Adicionar Nome'>");
        response.getWriter().println("</form>");

        response.getWriter().println("<h2>Nomes Cadastrados:</h2>");
        response.getWriter().println("<ul>");

        synchronized (nomes) {
            for (String nome : nomes) {
                response.getWriter().println("<li>" + nome + "</li>");
            }
        }

        response.getWriter().println("</ul>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");

        if (nome != null && !nome.isEmpty()) {
            synchronized (nomes) {
                nomes.add(nome);
            }
        }

        doGet(request, response);
    }
}

