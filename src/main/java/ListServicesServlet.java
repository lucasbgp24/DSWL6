// ListServicesServlet.java
package com.example.servlet;

import com.example.model.Service;
import com.example.dao.ServiceDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class ListServicesServlet
 * Handles listing of services.
 */
@WebServlet("/listServices")
public class ListServicesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ServiceDao serviceDao = new ServiceDao();
            List<Service> services = serviceDao.getAllServices();
            request.setAttribute("services", services);
            RequestDispatcher dispatcher = request.getRequestDispatcher("listServices.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while listing services.");
        }
    }
}