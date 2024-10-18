// UpdateServiceServlet.java
package com.example.servlet;

import com.example.model.Service;
import com.example.dao.ServiceDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class UpdateServiceServlet
 * Handles updating of a service.
 */
@WebServlet("/updateService")
public class UpdateServiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));

            Service service = new Service();
            service.setId(id);
            service.setName(name);
            service.setDescription(description);
            service.setPrice(price);

            ServiceDao serviceDao = new ServiceDao();
            serviceDao.updateService(service);

            response.sendRedirect("listServices");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating the service.");
        }
    }
}