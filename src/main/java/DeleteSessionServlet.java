// DeleteSessionServlet.java
package com.example.servlet;

import com.example.dao.SessionDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class DeleteSessionServlet
 * Handles deleting of a session.
 */
@WebServlet("/deleteSession")
public class DeleteSessionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            SessionDao sessionDao = new SessionDao();
            sessionDao.deleteSession(id);
            response.sendRedirect("listSessions?serviceId=" + request.getParameter("serviceId"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid session ID.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while deleting the session.");
        }
    }
}