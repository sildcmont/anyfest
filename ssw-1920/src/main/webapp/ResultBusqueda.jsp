<%@page import="javax.json.JsonReader"%>
<%@page import="javax.json.JsonArray"%>
<%@page import="java.io.StringReader"%>
<%@page import="java.io.StringReader"%>
<%@page import="javax.json.JsonReaderFactory"%>
<%@page import="javax.json.Json"%>
<%@page import="javax.json.JsonObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Resultados de búsqueda</title>
        <!-- Importar css -->
        <link rel="stylesheet" type="text/css" href="resultBusqueda.css">
        <!-- Tipografia Montserrat -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    </head>

    <body>
        <%
            session = request.getSession();
            String usuario = (String) session.getAttribute("usuario");


            /* Desempaquetar resultados */
            JsonReaderFactory jr = Json.createReaderFactory(null);
            JsonReader reader = jr.createReader(new StringReader((String) session.getAttribute("resultados")));
            JsonArray json = reader.readArray();
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
                    <a class="boton" style=padding-left:3px;padding-right:3px; href="configCliente.jsp">Configuracion</a>
                    <a class="boton" style=padding-left:3px;padding-right:10px; href="CerrarSesion">Cerrar sesión</a>
                </div>
            </div>
        </header>
        <!-- Fin del header -->
        <!-- Comienza el contenido de la página -->

        <div class="container" style="padding-top: 70px; padding-left: 15px;background-color: #fff0">
            <h1 style="font-family: 'Montserrat', sans-serif; text-align: center; padding-left: 30%">Resultados de búsqueda</h1>
            <div style="padding-top:3%; padding-left: 25%; padding-right: 70%;">
                
                
                <table class="tablaSolicitudes">
                    <tr>
                        <th>Nombre</th>
                        <th>Valoraciones medias</th>
                        <th>Email</th>
                        <th>Teléfono</th>
                        <th>Descripción</th>
                        <th>Acciones</th>
                    </tr>


                    <tr>
                        <% for (int i = 0; i < json.size(); i++) {
                                JsonObject objeto = json.getJsonObject(i);
                                String id = objeto.getString("usuario");
                        %>
                        <td class="celdaSolic"><%=objeto.getString("nombre")%></td>
                        <td class="celdaSolic"><%=objeto.getString("resenas")%></td>
                        <td class="celdaSolic"><%=objeto.getString("email")%></td>
                        <td class="celdaSolic"><%=objeto.getString("telefono")%></td>
                        <td class="celdaSolic"><%=objeto.getString("descripcion")%></td>
                        
                        <td>
                            <form class="oculto" method="get" type="hidden" action="EnviarSolicitud">
                                <input name="idProveedor" type="hidden" value="<%=id%>"/>
                                <input type="submit" class="botonDetalles" value="Enviar solicitud">
                            </form>
                        </td>
                    </tr>

                    <%}%>
                </table>
            </div>
        </div>
    </body>
      <!-- Comienzo del footer -->
    <footer class="footer">

        <a class="boton" href="anyfest.com">¿Quiénes somos?</a>

        <a class="boton" href="anyfest.com/jobs">Trabaja con nosotros</a>

        <a class="boton" href="anyfest.com/help">¿Necesitas ayuda?</a>

        <a class="boton" href="anyfest.com/tos">Condiciones de uso</a>

    </footer>
</html>