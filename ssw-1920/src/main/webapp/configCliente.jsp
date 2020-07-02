<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-ES">

    <head>
        <!-- Aqui van todos los metadatos de la pagina -->
        <meta charset="utf-8">
        <title>Configuración</title>
        <!-- Importar css -->
        <link rel="stylesheet" type="text/css" href="configCliente.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    </head>


    <body>
        <%
            String usuario = (String) session.getAttribute("usuario");
        %>
        <!-- Aqui van todos los datos de la pagina -->
        <!-- Comienzo del header -->
        <header style="display: inline-block; background-color: #ffffff;">

            <!-- <head>Anyfest - Aquí tiene que ir el logo en forma de imagen</head> -->
            <div class="cabecera">
                <div style="float:left">
                    <a class="boton" href="index.html">
                        <img id="logo-cabecera" src="img/anyfest-logo-transp.png"/>
                    </a></div>
                <div id="botones-superiores">
                    <a class="boton" style=padding-left:3px;padding-right:3px; href="">Hola, <%=usuario%></a>    
                    <a class="boton" style=padding-left:3px;padding-right:10px; href="CerrarSesion">Cerrar sesión</a>
                </div>
            </div>

        </header>
        <!-- Fin del header -->

        <!-- Comienzo del formulario -->
        <!--<div style="padding-top: 100px;">-->
        <div class="signupprovider">
            <form class="form" method="post" action="configCliente">
                <p class="center"><label class="labelForm">Nuevo nombre:</label>
                    <input name="nombre" type="text" size="25" maxlength="30" placeholder="Nuevo nombre..."></p><br/>
                <p class="center"><label class="labelForm">Nuevos apellidos:</label>
                    <input name="apellidos" type="text" placeholder="Nuevos apellidos..."></p><br/>
                <p class="center"><label class="labelForm">Nuevo correo electrónico:</label>
                    <input name="email" type="text" size="25" maxlength="30" type="email" id="mail" placeholder="Nuevo correo electrónico..."></p><br/>
                <p class="center"><label class="labelForm">Nueva dirección:</label>
                    <input name="direccion" type="text" placeholder="Nueva dirección..."></p><br/>
                <p class="center"><label class="labelForm" for="pwd">Nueva password:</label>
                    <input name="passwd" type="password" id="pwd"  placeholder="Nueva password..."></p><br/>
                <p class="center"><button class="login" type="submit">Actualizar</button></p>
            </form>
        </div>


         <!-- Comienzo del footer -->
        <footer class="footer">

            <a class="boton" href="anyfest.com">¿Quiénes somos?</a>

            <a class="boton" href="anyfest.com/jobs">Trabaja con nosotros</a>

            <a class="boton" href="anyfest.com/help">¿Necesitas ayuda?</a>

            <a class="boton" href="anyfest.com/tos">Condiciones de uso</a>

        </footer>
        <!-- Aqui va el script para que el navegador interprete todo el html antes de aplicar JS -->
        <!--<script src="my-js-file.js"></script>-->
    </body>

    </html>
