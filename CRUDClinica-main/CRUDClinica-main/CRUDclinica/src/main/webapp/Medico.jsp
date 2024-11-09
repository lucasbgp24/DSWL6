<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Medico" %>
<%@ page import="dao.MedicoDAO" %>
<%@ page import="servlet.MedicoServlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Médicos</title>
    <style>
        /* Estilos globais */
        body {
            font-family: Arial, sans-serif;
            background-color: #1B1B1B;
            color: #E0E0E0;
            margin: 0;
            display: flex;
        }

        /* Cabeçalho fixo */
        .header {
            position: fixed;
            top: 0;
            width: 100%;
            height: 60px;
            background-color: #333;
            color: #E0E0E0;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 24px;
            font-weight: bold;
            box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.5);
            z-index: 10;
        }

        /* Layout principal */
        .layout {
            display: flex;
            width: 100%;
            padding-top: 60px;
        }

        /* Menu lateral esquerdo */
        .menu {
            width: 200px;
            background-color: #2A2A2A;
            padding: 20px;
            display: flex;
            flex-direction: column;
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.5);
            height: 100vh;
        }

        .menu button {
            background-color: #444;
            color: #FFF;
            border: none;
            padding: 12px 15px;
            margin: 8px 0;
            cursor: pointer;
            font-size: 16px;
            text-align: left;
            width: 100%;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        .menu button:hover {
            background-color: #555;
        }

        /* Conteúdo principal */
        .content {
            flex: 1;
            padding: 20px;
            display: flex;
            justify-content: center;
            flex-direction: column;
        }

        /* Divisão direita para futuros botões */
        .right-menu {
            width: 200px;
            background-color: #2A2A2A;
            padding: 20px;
            box-shadow: -2px 0 5px rgba(0, 0, 0, 0.5);
            height: 100vh;
        }

        /* Formulário de cadastro */
        .form-container {
            background-color: #2D2D2D;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 20px;
        }

        .form-container h1 {
            color: #FFA726;
            text-align: center;
        }

        .form-container input[type="text"] {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            box-sizing: border-box;
            background-color: #333;
            color: #E0E0E0;
            border: 1px solid #555;
            border-radius: 4px;
        }

        .form-container button {
            background-color: #FFA726;
            color: #1B1B1B;
            border: none;
            padding: 12px 15px;
            cursor: pointer;
            font-size: 16px;
            border-radius: 4px;
            width: 100%;
            transition: background-color 0.3s;
        }

        .form-container button:hover {
            background-color: #FF8C00;
        }

        /* Tabela de médicos */
        .doctors-table {
            width: 100%;
            border-collapse: collapse;
            background-color: #2D2D2D;
            border-radius: 8px;
            overflow: hidden;
        }

        .doctors-table th, .doctors-table td {
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid #444;
        }

        .doctors-table th {
            background-color: #555;
            color: #E0E0E0;
        }

        .doctors-table td {
            background-color: #333;
        }

        .doctors-table a {
            color: #FFA726;
            text-decoration: none;
        }

        .doctors-table a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <!-- Cabeçalho -->
    <div class="header">
        Clínica - Cadastro de Médicos
    </div>

    <div class="layout">
        <!-- Menu lateral esquerdo -->
        <div class="menu">
            <button onclick="window.location.href='index.jsp'">Home</button>
            <button onclick="window.location.href='Agendamento.jsp'">Novo Agendamento</button>
            <button onclick="window.location.href='PacienteServlet'">Cadastrar Pacientes</button>
            <button onclick="window.location.href='MedicoServlet'">Cadastrar Médicos</button>
        </div>

        <!-- Conteúdo principal com formulário e tabela -->
        <div class="content">
            <!-- Formulário de cadastro -->
            <div class="form-container">
                <h1>Cadastro de Médicos</h1>
                <form action="MedicoServlet" method="post">
                    <input type="hidden" name="id" value="${medico.id}">
                    Nome: <input type="text" name="nome" value="${medico.nome}" required><br>
                    CPF: <input type="text" name="cpf" value="${medico.cpf}" required><br>
                    Especialidade: <input type="text" name="especialidade" value="${medico.especialidade}" required><br>
                    <button type="submit">Salvar</button>
                </form>
            </div>

            <!-- Tabela de médicos cadastrados -->
            <h2 style="text-align: center; color: #FFA726;">Médicos Cadastrados</h2>
            <table class="doctors-table">
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>CPF</th>
                    <th>Especialidade</th>
                    <th>Ações</th>
                </tr>
                <c:forEach var="medico" items="${medicos}">
                    <tr>
                        <td>${medico.id}</td>
                        <td>${medico.nome}</td>
                        <td>${medico.cpf}</td>
                        <td>${medico.especialidade}</td>
                        <td>
                            <a href="MedicoServlet?action=edit&id=${medico.id}">Editar</a> |
                            <a href="MedicoServlet?action=delete&id=${medico.id}" onclick="return confirm('Tem certeza que deseja excluir?');">Excluir</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <!-- Divisão direita para futuros botões -->
        <div class="right-menu">
            <p>Espaço para futuros botões</p>
        </div>
    </div>
</body>
</html>
