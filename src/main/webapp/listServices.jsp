<!-- listServices.jsp -->
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Service" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Servi�os</title>
</head>
<body>
    <h1>Servi�os Dispon�veis</h1>
    <form action="addService" method="post">
        Nome: <input type="text" name="name"><br>
        Descri��o: <input type="text" name="description"><br>
        Pre�o: <input type="number" step="0.01" name="price"><br>
        <input type="submit" value="Adicionar Servi�o">
    </form>
    <table border="1">
        <tr>
            <th>Nome</th>
            <th>Descri��o</th>
            <th>Pre�o</th>
            <th>A��es</th>
        </tr>
        <%
            List<Service> services = (List<Service>) request.getAttribute("services");
            for (Service service : services) {
        %>
        <tr>
            <td><%= service.getName() %></td>
            <td><%= service.getDescription() %></td>
            <td><%= service.getPrice() %></td>
            <td>
                <a href="viewService?id=<%= service.getId() %>">Ver Detalhes</a>
                <a href="deleteService?id=<%= service.getId() %>">Excluir</a>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>