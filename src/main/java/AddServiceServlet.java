// AddServiceServlet.java
package com.example.servlet;

import java.io.IOException;

import com.example.dao.ServiceDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddServiceServlet
 * Handles adding of a new service.
 */
@WebServlet("/addService")
public class AddServiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));

            Service service = new Service();
            service.setName(name);
            service.setDescription(description);
            service.setPrice(price);

            ServiceDao serviceDao = new ServiceDao();
            serviceDao.addService(service);

            response.sendRedirect("listServices");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid price format.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while adding the service.");
        }
    }
}