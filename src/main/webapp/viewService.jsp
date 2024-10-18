<!-- viewService.jsp -->
<%@ page import="com.example.model.Service" %>
<!DOCTYPE html>
<html>
<head>
    <title>Detalhes do Servi�o</title>
</head>
<body>
    <%
        Service service = (Service) request.getAttribute("service");
    %>
    <h1><%= service.getName() %></h1>
    <p><%= service.getDescription() %></p>
    <p>Pre�o: <%= service.getPrice() %></p>
    <h2>Agendar Sess�o</h2>
    <form action="addSession" method="post">
        <input type="hidden" name="serviceId" value="<%= service.getId() %>">
        Data: <input type="date" name="date"><br>
        Hora: <input type="time" name="time"><br>
        <input type="submit" value="Agendar">
    </form>
    <h2>Sess�es Agendadas</h2>
    <a href="listSessions?serviceId=<%= service.getId() %>">Ver Sess�es</a>
</body>
</html>