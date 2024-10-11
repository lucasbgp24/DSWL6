package com.exemplo.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/welcome")
public class CapturarParametrosDaURL extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String nome = request.getParameter("nome");
        
        response.setContentType("text/html");
        
        response.getWriter().println("<html>");
        response.getWriter().println("<head><title>Bem-vindo</title></head>");
        response.getWriter().println("<body>");
        if (nome != null && !nome.isEmpty()) {
            response.getWriter().println("<h1>Olá, " + nome + "!</h1>");
        } else {
            response.getWriter().println("<h1>Olá, visitante!</h1>");
        }
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}
