<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Agendar Sessão</title>
</head>
<body>
    <h1>Agendar Sessão para ${service.name}</h1>
    <form action="AddSessionServlet" method="post">
        <input type="hidden" name="serviceId" value="${service.id}"/>
        Data: <input type="date" name="date" required/><br/>
        Hora: <input type="time" name="time" required/><br/>
        <input type="submit" value="Agendar"/>
    </form>
</body>
</html>
