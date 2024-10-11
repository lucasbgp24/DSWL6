package com.exemplo.web;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/produto")
public class CadastroDeProdutos extends HttpServlet {

    private static ArrayList<Product> produtos = new ArrayList<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        response.getWriter().println("<html>");
        response.getWriter().println("<head><title>Cadastro de Produtos</title></head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<h1>Cadastro de Produto</h1>");
        response.getWriter().println("<form action='produto' method='POST'>");
        response.getWriter().println("Nome: <input type='text' name='nome' required><br>");
        response.getWriter().println("Preço: <input type='text' name='preco' required><br>");
        response.getWriter().println("<input type='submit' value='Cadastrar Produto'>");
        response.getWriter().println("</form>");

        response.getWriter().println("<h2>Produtos Cadastrados:</h2>");
        response.getWriter().println("<ul>");

        synchronized (produtos) {
            for (Product produto : produtos) {
                response.getWriter().println("<li>" + produto.getNome() + " - R$ " + produto.getPreco() + "</li>");
            }
        }

        response.getWriter().println("</ul>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String precoStr = request.getParameter("preco");

        double preco = Double.parseDouble(precoStr);

        if (nome != null && !nome.isEmpty()) {
            synchronized (produtos) {
                produtos.add(new Product(nome, preco));
            }
        }

        doGet(request, response);
    }

    public static class Product {
        private String nome;
        private double preco;

        public Product(String nome, double preco) {
            this.nome = nome;
            this.preco = preco;
        }

        public String getNome() {
            return nome;
        }

        public double getPreco() {
            return preco;
        }
    }
}
