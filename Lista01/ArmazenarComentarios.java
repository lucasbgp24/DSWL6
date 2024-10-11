package com.exemplo.web;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/comment")
public class ArmazenarComentarios extends HttpServlet {

    private static ArrayList<String> comentarios = new ArrayList<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        response.getWriter().println("<html>");
        response.getWriter().println("<head><title>Comentários</title></head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<h1>Enviar Comentário</h1>");
        response.getWriter().println("<form action='comment' method='POST'>");
        response.getWriter().println("<textarea name='comentario' rows='4' cols='50' placeholder='Digite seu comentário' required></textarea><br>");
        response.getWriter().println("<input type='submit' value='Enviar Comentário'>");
        response.getWriter().println("</form>");

        response.getWriter().println("<h2>Comentários Submetidos:</h2>");
        response.getWriter().println("<ul>");

        synchronized (comentarios) {
            for (String comentario : comentarios) {
                response.getWriter().println("<li>" + comentario + "</li>");
            }
        }

        response.getWriter().println("</ul>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtendo o comentário do parâmetro "comentario"
        String comentario = request.getParameter("comentario");

        // Adicionando o comentário ao ArrayList
        if (comentario != null && !comentario.isEmpty()) {
            synchronized (comentarios) {
                comentarios.add(comentario);
            }
        }

        // Redirecionando para o método doGet para exibir a página novamente
        doGet(request, response);
    }
}

