<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.PacienteDAO" %>
<%@ page import="dao.MedicoDAO" %>
<%@ page import="model.Paciente" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Agendamento</title>
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

        /* Formulário de agendamento */
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

        .form-container input[type="text"], .form-container select {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            box-sizing: border-box;
            background-color: #333;
            color: #E0E0E0;
            border: 1px solid #555;
            border-radius: 4px;
        }

        /* Estilo para inputs de data e hora */
        .form-container input[type="date"], .form-container input[type="time"] {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            box-sizing: border-box;
            background-color: #333;
            color: #E0E0E0;
            border: 1px solid #555;
            border-radius: 4px;
            text-align: left;
            appearance: none; /* Remove o estilo padrão para personalizar o alinhamento */
        }

        .form-container input[type="submit"] {
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

        .form-container input[type="submit"]:hover {
            background-color: #FF8C00;
        }
    </style>
    <script>
        function buscarPacientes() {
            const keyword = document.getElementById("buscaPaciente").value;
            fetch("AgendamentoServlet?tipo=paciente&keyword=" + keyword)
                .then(response => response.json())
                .then(data => {
                    let select = document.getElementById("pacienteId");
                    select.innerHTML = "";
                    data.forEach(paciente => {
                        let option = document.createElement("option");
                        option.value = paciente.id;
                        option.textContent = paciente.nome;
                        select.appendChild(option);
                    });
                });
        }

        function buscarMedicos() {
            const keyword = document.getElementById("buscaMedico").value;
            fetch("AgendamentoServlet?tipo=medico&keyword=" + keyword)
                .then(response => response.json())
                .then(data => {
                    let select = document.getElementById("medicoId");
                    select.innerHTML = "";
                    data.forEach(medico => {
                        let option = document.createElement("option");
                        option.value = medico.id;
                        option.textContent = medico.nome;
                        select.appendChild(option);
                    });
                });
        }
    </script>
</head>
<body>
    <!-- Cabeçalho -->
    <div class="header">
        Clínica - Agendamento de Consulta
    </div>

    <div class="layout">
        <!-- Menu lateral esquerdo -->
        <div class="menu">
            <button onclick="window.location.href='index.jsp'">Home</button>
            <button onclick="window.location.href='Agendamento.jsp'">Novo Agendamento</button>
            <button onclick="window.location.href='PacienteServlet'">Cadastrar Pacientes</button>
            <button onclick="window.location.href='MedicoServlet'">Cadastrar Médicos</button>
        </div>

        <!-- Conteúdo principal com formulário de agendamento -->
        <div class="content">
            <div class="form-container">
                <h1>Agendamento de Consulta</h1>
                <form action="AgendamentoServlet" method="POST">
                    <label for="pacienteId">Paciente:</label>
                    <input type="text" id="buscaPaciente" onkeyup="buscarPacientes()" placeholder="Buscar paciente...">
                    <select id="pacienteId" name="pacienteId">
                        <!-- Pacientes serão carregados aqui -->
                    </select>
                    <br><br>

                    <label for="medicoId">Médico:</label>
                    <input type="text" id="buscaMedico" onkeyup="buscarMedicos()" placeholder="Buscar médico...">
                    <select id="medicoId" name="medicoId">
                        <!-- Médicos serão carregados aqui -->
                    </select>
                    <br><br>

                    <label for="dataAgendamento">Data:</label>
                    <input type="date" id="dataAgendamento" name="dataAgendamento" required>
                    <br><br>

                    <label for="horaAgendamento">Hora:</label>
                    <input type="time" id="horaAgendamento" name="horaAgendamento" required>
                    <br><br>

                    <input type="submit" value="Agendar">
                </form>
            </div>
        </div>

        <!-- Divisão direita para futuros botões -->
        <div class="right-menu">
            <p>Espaço para futuros botões</p>
        </div>
    </div>
</body>
</html>
