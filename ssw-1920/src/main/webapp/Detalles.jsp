<%@page import="javax.json.JsonArray"%>
<%@page import="javax.json.JsonObject"%>
<%@page import="javax.json.JsonReader"%>
<%@page import="java.io.StringReader"%>
<%@page import="javax.json.JsonReaderFactory"%>
<%@page import="javax.json.Json"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-ES">
    <head>
        <!-- Aqui van todos los metadatos de la pagina -->
        <meta charset="utf-8">
        <title>Detalles</title>
        <!-- Importar css -->
        <link rel="stylesheet" type="text/css" href="home.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    </head>

    <body>
        <!-- Aqui van todos los datos de la pagina -->
        <%
            session = request.getSession();
            String usuario = (String) session.getAttribute("usuario");

            /* Desempaquetar solicitudes */
            JsonReaderFactory jr = Json.createReaderFactory(null);
            JsonReader lector = jr.createReader(new StringReader((String) session.getAttribute("solicitudes")));
            JsonObject json = lector.readObject();

        %>
        <!-- Comienzo del header -->
        <header style="display: inline-block; background-color: #ffffff;">

            <!-- <head>Anyfest - Aquí tiene que ir el logo en forma de imagen</head> -->
            <div class="cabecera">
                <div style="float:left">
                    <a class="boton" href="index.html"><img style="margin: 20px 20px; max-width: 15%; width: auto;" src="img/anyfest-logo-transp.png" />
                    </a> </div>
                <div style="float: right; padding-top: 30px;">
                    <a class="botonCabecera" href="">Hola, <%=usuario%></a>    
                    <a class="botonCabecera" href="configProveedor.jsp">Configuración</a>
                    <a class="botonCabecera" href="CerrarSesion">Cerrar sesión</a>
                </div>
            </div>

        </div>
    </header>
    <!-- Fin del header -->
    <!-- Comienza el contenido de la página -->
    <div class="solicitudes" style="text-align: center;">
        <h1 style=" font-family: 'Montserrat', sans-serif;">Solicitudes pendientes</h1>

        <table class="tablaSolicitudes">

        </table>
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