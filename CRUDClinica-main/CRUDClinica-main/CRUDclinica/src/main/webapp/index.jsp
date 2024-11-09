<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Agendamento" %>
<%@ page import="dao.AgendamentoDAO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Página Inicial - Agendamentos</title>
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
            padding-top: 60px; /* Ajusta para compensar o cabeçalho fixo */
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
          
        }

        /* Área da lista de agendamentos */
        .appointments {
            background-color: #2D2D2D;
            padding: 20px;
            border-radius: 8px;
            width: calc(100% - 40px);/* Ajusta a largura para considerar o menu lateral */
            margin: 0 auto; /* Centraliza a div */
            margin-top: 10px; /* Aproxima a div do cabeçalho */
        }

        .appointments h1 {
            color: #FFA726;
            text-align: center;
        }

        .appointments table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        .appointments th, .appointments td {
            padding: 12px;
            border-bottom: 1px solid #444;
            text-align: center;
        }

        .appointments th {
            background-color: #555;
            color: #E0E0E0;
        }

        .appointments td {
            background-color: #333;
        }

        .appointments a {
            color: #FFA726;
            text-decoration: none;
        }

        .appointments a:hover {
            text-decoration: underline;
        }

        /* Seção de ações futuras à direita */
                /* Divisão direita para futuros botões */
        .right-menu {
            width: 200px;
            background-color: #2A2A2A;
            padding: 20px;
            box-shadow: -2px 0 5px rgba(0, 0, 0, 0.5);
            height: 100vh;
        }
    </style>
</head>
<body>
    <!-- Cabeçalho -->
    <div class="header">
        Clínica - Agendamentos
    </div>

    <div class="layout">
        <!-- Menu lateral esquerdo -->
        <div class="menu">
            <button onclick="window.location.href='index.jsp'">Home</button>
            <button onclick="window.location.href='Agendamento.jsp'">Novo Agendamento</button>
            <button onclick="window.location.href='PacienteServlet'">Cadastrar Pacientes</button>
            <button onclick="window.location.href='MedicoServlet'">Cadastrar Médicos</button>
        </div>

        <!-- Conteúdo principal com lista de agendamentos -->
        <div class="content">
            <div class="appointments">
                <h1>Agendamentos</h1>
                <table>
                    <tr>
                        <th>Paciente</th>
                        <th>Médico</th>
                        <th>Data</th>
                        <th>Hora</th>
                        <th>Ações</th>
                    </tr>
                    <%
                        AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
                        List<Agendamento> listaAgendamentos = agendamentoDAO.listarAgendamentosComNomes();

                        for (Agendamento agendamento : listaAgendamentos) {
                    %>
                    <tr>
                        <td><%= agendamento.getPacienteNome() %></td>
                        <td><%= agendamento.getMedicoNome() %></td>
                        <td><%= agendamento.getDataAgendamento() %></td>
                        <td><%= agendamento.getHoraAgendamento() %></td>
                        <td>
                            <a href="EditarAgendamentoServlet?id=<%= agendamento.getId() %>">Editar</a> |
                            <a href="ExcluirAgendamentoServlet?id=<%= agendamento.getId() %>" onclick="return confirm('Tem certeza que deseja excluir?');">Excluir</a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
        </div>

        <!-- Seção de botões futuros à direita -->
        <div class="right-menu">
            <p>Espaço para futuros botões</p>
        </div>
    </div>
</body>
</html>
