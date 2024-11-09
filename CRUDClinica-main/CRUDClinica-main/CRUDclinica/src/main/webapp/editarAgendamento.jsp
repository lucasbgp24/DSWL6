<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Agendamento</title>
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
        .container {
            flex: 1;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        /* Formulário de edição de agendamento */
        .form-container {
            background-color: #2D2D2D;
            padding: 20px;
            border-radius: 8px;
            width: 400px;
            color: #E0E0E0;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.5);
        }

        .form-container h2 {
            color: #FFA726;
            text-align: center;
            margin-bottom: 20px;
        }

        .form-container label {
            font-weight: bold;
            color: #E0E0E0;
            display: block;
            margin-top: 10px;
        }

        .form-container input[type="text"],
        .form-container input[type="date"],
        .form-container input[type="time"] {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #444;
            border-radius: 5px;
            background-color: #333;
            color: #FFF;
            box-sizing: border-box;
        }

        .form-container button[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #FFA726;
            color: #1B1B1B;
            font-weight: bold;
            cursor: pointer;
            margin-top: 15px;
            transition: background-color 0.3s;
        }

        .form-container button[type="submit"]:hover {
            background-color: #FFB947;
        }

        /* Menu lateral direito */
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

        <!-- Conteúdo principal -->
        <div class="container">
            <div class="form-container">
                <h2>Editar Agendamento</h2>
                <form action="EditarAgendamentoServlet" method="post">
                    <input type="hidden" name="id" value="${agendamento.id}" />

                    <label for="pacienteId">Paciente:</label>
                    <input type="text" name="pacienteId" id="pacienteId" value="${agendamento.pacienteId}" required />

                    <label for="medicoId">Médico:</label>
                    <input type="text" name="medicoId" id="medicoId" value="${agendamento.medicoId}" required />

                    <label for="data">Data:</label>
                    <input type="date" name="data" id="data" value="${agendamento.dataAgendamento}" required />

                    <label for="hora">Hora:</label>
                    <input type="time" name="hora" id="hora" value="${agendamento.horaAgendamento}" required />

                    <button type="submit">Salvar</button>
                </form>
            </div>
        </div>

        <!-- Menu lateral direito -->
        <div class="right-menu">
            <p>Espaço para futuros botões</p>
        </div>
    </div>
</body>
</html>
