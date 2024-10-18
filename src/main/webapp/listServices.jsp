<!-- listServices.jsp -->
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Service" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Serviços</title>
</head>
<body>
    <h1>Serviços Disponíveis</h1>
    <form action="addService" method="post">
        Nome: <input type="text" name="name"><br>
        Descrição: <input type="text" name="description"><br>
        Preço: <input type="number" step="0.01" name="price"><br>
        <input type="submit" value="Adicionar Serviço">
    </form>
    <table border="1">
        <tr>
            <th>Nome</th>
            <th>Descrição</th>
            <th>Preço</th>
            <th>Ações</th>
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