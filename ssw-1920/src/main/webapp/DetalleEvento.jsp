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
        <title>Detalles de evento</title>
        <!-- Importar css -->
        <link rel="stylesheet" type="text/css" href="detalleEvento.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    </head>

    <body>
        <!-- Aqui van todos los datos de la pagina -->
        <%
            session = request.getSession();
            String usuario = (String) session.getAttribute("usuario");

            /* Desempaquetar solicitudes */
            String datosEvento = (String) session.getAttribute("datosEvento");
            String datosChecklist = (String) session.getAttribute("datosChecklist");

            JsonReaderFactory jr = Json.createReaderFactory(null);
            JsonReader lector = jr.createReader(new StringReader(datosEvento));
            JsonObject json = lector.readObject();
            String idEvento = json.getString("id");
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
        <h1 style="font-family: 'Montserrat' ,sans-serif; text-align: center; ">Detalles de <b><%= json.getString("titulo")%></b></h1>
        <p>Direccion: <%= json.getString("direccion")%></p>
        <p>Fecha: <%= json.getString("fecha")%></p>
        <p>Hora: <%= json.getString("hora")%></p>
        <p>Checklist</p>

        <table class="tablaSolicitudes">
            <tr>
                <th>Categoría</th>
                <th>Estado</th>
                <th>Acción</th>
            </tr>
            <% lector = jr.createReader(new StringReader(datosChecklist));
                JsonArray jsonArray = lector.readArray();
                JsonObject objeto;
                String idChecklist = "";
                String aceptado = "";
                for (int i = 0; i < jsonArray.size(); i++) {
                    objeto = jsonArray.getJsonObject(i);
                    idChecklist = objeto.getString("id");
                    aceptado = objeto.getString("aceptado");
                    String estado = "";
                    String color;
                    if (aceptado.equals(String.valueOf(0))) {
                        estado = "pendiente";
                        color = "red";
                    } else {
                        estado = "aceptado";
                        color = "green";
                    }
            %>
            <tr>
                <td class="celdaSolic"><%= objeto.getString("categoriaProveedor")%></td>
                <td class="celdaSolic" style="color: <%=color%>"><%= estado%></td>
                <td>
                    <% String valor = String.valueOf('1');
                        if (aceptado.equals(valor)) {
                            /* Dejo una reseña */%>
                    <form class="oculto" method="post" action="DejarResena"> 
                        <input name="idChecklist" type="hidden" value="<%=idChecklist%>"/>
                        <input name="categoriaProveedor" type="hidden" value="<%=objeto.getString("categoriaProveedor")%>"/>
                        <input name="idEvento" type="hidden" value="<%=idEvento%>"/>
                        <input name="valoracion" type="number" min="1" max="5" value="5" placeholder="1..5"/>
                        <input type="submit" class="botonDetalles" value="Dejar reseña"/>
                    </form>
                    <% } else {%>
                    <form class="oculto" method="post" type="hidden" action="Busqueda">
                        <input name="idChecklist" type="hidden" value="<%=idChecklist%>"/>
                        <input name="categoriaProveedor" type="hidden" value="<%=objeto.getString("categoriaProveedor")%>"/>
                        <input name="idEvento" type="hidden" value="<%=idEvento%>"/>
                        <input type="submit" class="botonDetalles" value="Añadir proveedor"/>
                    </form>
                        <%}%>
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