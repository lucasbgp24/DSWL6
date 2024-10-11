package com.exemplo.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/accessCounter")
public class ContadorDeAcessos extends HttpServlet {
    
    private static int accessCount = 0;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this) {
            accessCount++;
        }

        response.setContentType("text/html");
        
        response.getWriter().println("<html>");
        response.getWriter().println("<head><title>Contador de Acessos</title></head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<h1>Número de acessos: " + accessCount + "</h1>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}

