package com.exemplo.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Formulario extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<html>");
        response.getWriter().println("<head><title>Formulário de Login</title></head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<h2>Login</h2>");
        response.getWriter().println("<form action='login' method='POST'>");
        response.getWriter().println("<label for='username'>Nome de Usuário:</label>");
        response.getWriter().println("<input type='text' id='username' name='username' required>");
        response.getWriter().println("<input type='submit' value='Entrar'>");
        response.getWriter().println("</form>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        
        response.setContentType("text/html");
        response.getWriter().println("<html>");
        response.getWriter().println("<head><title>Bem-vindo</title></head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<h1>Bem-vindo, " + username + "!</h1>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}
