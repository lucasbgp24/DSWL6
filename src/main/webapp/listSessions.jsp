<!-- listSessions.jsp -->
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Session" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Sessões</title>
</head>
<body>
    <h1>Sessões Agendadas</h1>
    <table border="1">
        <tr>
            <th>Data</th>
            <th>Hora</th>
            <th>Ações</th>
        </tr>
        <%
            List<Session> sessions = (List<Session>) request.getAttribute("sessions");
            for (Session <!-- listSessions.jsp -->
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Session" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Sessões</title>
</head>
<body>
    <h1>Sessões Agendadas</h1>
    <table border="1">
        <tr>
            <th>Data</th>
            <th>Hora</th>
            <th>Ações</th>
        </tr>
        <%
            List<Session> sessions = (List<Session>) request.getAttribute("sessions");
            for (Session mySession : sessions) {
        %>
        <tr>
            <td><%= mySession.getDate() %></td>
            <td><%= mySession.getTime() %></td>
            <td>
                <a href="deleteSession?id=<%= mySession.getId() %>&serviceId=<%= mySession.getServiceId() %>">Excluir</a>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html> : sessions) {
        %>
        <tr>
            <td><%= <!-- listSessions.jsp -->
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Session" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Sessões</title>
</head>
<body>
    <h1>Sessões Agendadas</h1>
    <table border="1">
        <tr>
            <th>Data</th>
            <th>Hora</th>
            <th>Ações</th>
        </tr>
        <%
            List<Session> sessions = (List<Session>) request.getAttribute("sessions");
            for (Session mySession : sessions) {
        %>
        <tr>
            <td><%= mySession.getDate() %></td>
            <td><%= mySession.getTime() %></td>
            <td>
                <a href="deleteSession?id=<%= mySession.getId() %>&serviceId=<%= mySession.getServiceId() %>">Excluir</a>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>.getDate() %></td>
            <td><%= <!-- listSessions.jsp -->
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Session" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Sessões</title>
</head>
<body>
    <h1>Sessões Agendadas</h1>
    <table border="1">
        <tr>
            <th>Data</th>
            <th>Hora</th>
            <th>Ações</th>
        </tr>
        <%
            List<Session> sessions = (List<Session>) request.getAttribute("sessions");
            for (Session mySession : sessions) {
        %>
        <tr>
            <td><%= mySession.getDate() %></td>
            <td><%= mySession.getTime() %></td>
            <td>
                <a href="deleteSession?id=<%= mySession.getId() %>&serviceId=<%= mySession.getServiceId() %>">Excluir</a>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>.getTime() %></td>
            <td>
                <a href="deleteSession?id=<%= <!-- listSessions.jsp -->
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Session" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Sessões</title>
</head>
<body>
    <h1>Sessões Agendadas</h1>
    <table border="1">
        <tr>
            <th>Data</th>
            <th>Hora</th>
            <th>Ações</th>
        </tr>
        <%
            List<Session> sessions = (List<Session>) request.getAttribute("sessions");
            for (Session mySession : sessions) {
        %>
        <tr>
            <td><%= mySession.getDate() %></td>
            <td><%= mySession.getTime() %></td>
            <td>
                <a href="deleteSession?id=<%= mySession.getId() %>&serviceId=<%= mySession.getServiceId() %>">Excluir</a>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>.getId() %>&serviceId=<%= <!-- listSessions.jsp -->
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Session" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Sessões</title>
</head>
<body>
    <h1>Sessões Agendadas</h1>
    <table border="1">
        <tr>
            <th>Data</th>
            <th>Hora</th>
            <th>Ações</th>
        </tr>
        <%
            List<Session> sessions = (List<Session>) request.getAttribute("sessions");
            for (Session mySession : sessions) {
        %>
        <tr>
            <td><%= mySession.getDate() %></td>
            <td><%= mySession.getTime() %></td>
            <td>
                <a href="deleteSession?id=<%= mySession.getId() %>&serviceId=<%= mySession.getServiceId() %>">Excluir</a>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>.getServiceId() %>">Excluir</a>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>