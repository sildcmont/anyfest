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
        <title>Búsqueda</title>
        <!-- Importar css -->
        <link rel="stylesheet" type="text/css" href="vistaBusqueda.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    </head>

    <body>
        <!-- Aqui van todos los datos de la pagina -->
        <%
            session = request.getSession();
            String usuario = (String) session.getAttribute("usuario");
            String categoriaProveedor = (String) session.getAttribute("categoriaProveedor");


        %>
        <!-- Comienzo del header -->
        <header style="display: inline-block; background-color: #ffffff;">

            <!-- <head>Anyfest - Aquí tiene que ir el logo en forma de imagen</head> -->
            <div class="cabecera">
                <div style="float:left">
                    <a class="boton" href="https://www.anyfest.com">
                        <img id="logo-cabecera" src="img/anyfest-logo-transp.png"/>
                    </a></div>
                <div id="botones-superiores">
                    <a class="boton" style=padding-left:3px;padding-right:3px; href="">Hola, <%=usuario%></a>    
                    <a class="boton" style=padding-left:3px;padding-right:3px; href="configCliente.jsp">Configuracion</a>
                    <a class="boton" style=padding-left:3px;padding-right:10px; href="CerrarSesion">Cerrar sesión</a>
                </div>
            </div>

        </div>
    </header>
    <!-- Fin del header -->
    <!-- Comienza el contenido de la página -->
    <div class="solicitudes" style="text-align: center;">
        <h1 style="font-family: 'Montserrat' ,sans-serif; text-align: center; ">Búsqueda</h1>
        <form method="get" action="Busqueda"> 
            <input name="textoBusqueda" type="text" size ="25" maxlength="100" placeholder="Buscando en <%= session.getAttribute("categoriaProveedor")%>"</p>
            <p><input name="categoriaProveedor" type="hidden" value="<%=categoriaProveedor%>"/></p> 
            <p class="center"><input class="login" type="submit" value="Mostrar todos los proveedores"></p>
            <p class="center"><input class="login" type="submit" value="Buscar"></p>
            
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