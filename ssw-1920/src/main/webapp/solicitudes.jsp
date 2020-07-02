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
    </head>
    <meta charset="utf-8">
    <title>Solicitudes</title>
    <!-- Importar css -->
    <link rel="stylesheet" type="text/css" href="solicitudes.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <body>
        <!-- Aqui van todos los datos de la pagina -->
        <%
            session = request.getSession();
            String usuario = (String) session.getAttribute("usuario");

            /* Desempaquetar solicitudes */
            JsonReaderFactory jr = Json.createReaderFactory(null);
            JsonReader reader = jr.createReader(new StringReader((String) session.getAttribute("solicitudes")));
            JsonArray json = reader.readArray();


        %>
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
                    <a class="boton" style=padding-left:3px;padding-right:3px; href="configCliente.jsp">Configuracion</a>
                    <a class="boton" style=padding-left:3px;padding-right:10px; href="CerrarSesion">Cerrar sesión</a>
                </div>
            </div>

        </div>
    </header>
    <!-- Fin del header -->
    <!-- Comienza el contenido de la página -->
    <div class="solicitudes" style="text-align: center;">
        <h1 style="font-family: 'Montserrat' ,sans-serif; text-align: center; padding-left: 10%; padding-top: 5%;">Solicitudes Pendientes</h1>


        <table style="table-layout: fixed; width: 75%" class="tablaSolicitudes">
            <tr>
                <th>Id. Evento</th>
                <th>Categoría</th>
                <th>Dirección</th>                
                <th>Fecha</th>
                <th>Hora</th>
                <th>Usuario</th>
                <th>Acciones</th>
            </tr>

            <% for (int i = 0; i < json.size(); i++) {
                    JsonObject objeto = json.getJsonObject(i);
                    String id = objeto.getString("id");
            %>
            <tr>
                <td class="celdaSolic"><%= objeto.getString("id")%></td>
                <td class="celdaSolic"><%= objeto.getString("categoriaEvento")%></td>
                <td class="celdaSolic"><%= objeto.getString("direccion")%></td>
                <td class="celdaSolic"><%= objeto.getString("fecha")%></td>
                <td class="celdaSolic"><%= objeto.getString("hora")%></td>
                <td class="celdaSolic"><%= objeto.getString("nombre") + " " + objeto.getString("apellidos")%></td>
                <td>
                    <form style="width: 70%" class="oculto" type="hidden" method="post" action="GestionSolicitud">
                        <input type="hidden" name="idEvento" value="<%= id%>"/>
                        <input type="hidden" name="accion" value="true"/>
                        <button style="width: 120%; text-align: center" action="submit" class="botonSolicitudAceptar">Aceptar</button>

                    </form>
                    <form style="width: 70%"class="oculto" type="hidden" method="post" action="GestionSolicitud">
                        <input type="hidden" name="idEvento" value="<%= id%>"/>
                        <input type="hidden" name="accion" value="false"/>
                        <button style="width: 120%; text-align: center"action="submit" class="botonSolicitudRechazar">Rechazar</button>
                    </form>
                </td>
            </tr>
            <%}%>
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