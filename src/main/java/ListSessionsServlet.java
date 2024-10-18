// ListSessionsServlet.java
package com.example.servlet;

import com.example.model.Session;
import com.example.dao.SessionDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class ListSessionsServlet
 * Handles listing of sessions.
 */
@WebServlet("/listSessions")
public class ListSessionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int serviceId = Integer.parseInt(request.getParameter("serviceId"));
            SessionDao sessionDao = new SessionDao();
            List<Session> sessions = sessionDao.getSessionsByServiceId(serviceId);
            request.setAttribute("sessions", sessions);
            RequestDispatcher dispatcher = request.getRequestDispatcher("listSessions.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,

 "Invalid service ID.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while listing sessions.");
        }
    }
}