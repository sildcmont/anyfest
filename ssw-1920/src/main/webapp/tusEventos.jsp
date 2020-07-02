<%-- 
    Document   : tusEventos
    Created on : 13-abr-2020, 13:16:56
    Author     : istivan,marrobl,miggarr,silmont
--%>

<%@page import="javax.json.JsonObject"%>
<%@page import="javax.json.JsonArray"%>
<%@page import="java.io.StringReader"%>
<%@page import="javax.json.JsonReader"%>
<%@page import="javax.json.JsonReaderFactory"%>
<%@page import="javax.json.Json"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-ES">
    <head>
        <meta charset="utf-8">
        <title>Tus eventos</title>
        <!-- Importar css -->
        <link rel="stylesheet" type="text/css" href="tusEventos.css">
        <!-- Tipografia Montserrat -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    </head>

    <body>
        <%
            session = request.getSession();
            String usuario = (String) session.getAttribute("usuario");


            /* Desempaquetar eventos */
            JsonReaderFactory jr = Json.createReaderFactory(null);
            JsonReader reader = jr.createReader(new StringReader((String) session.getAttribute("eventos")));
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

        <div name="contenidoGrande" class="container" style="padding-top: 70px; padding-left: 15px;background-color: #fff0">
            <h1 style="font-family: 'Montserrat' ,sans-serif; text-align: center; padding-left: 25%;">Tus Eventos</h1>
            <div style="padding-left: 55%; height:100px">
                <button id="botonCrearEvento" onclick="document.getElementById('id01').style.display='block'"style="width:auto;">Crear Evento</button>
            </div>
            <div name="contenidoPeq" style="padding-top:3%; padding-left: 20%;;">
                <table class="tablaSolicitudes" style="table-layout: fixed; width: 400%">
                    <tr>
                        <th>Id. Evento</th>
                        <th>Título</th>
                        <th>Fecha</th>
                        <th>Hora</th>
                        <th>Dirección</th>
                        <th>Acciones</th>
                    </tr>


                    <tr>
                        <% for (int i = 0; i < json.size(); i++) {
                                JsonObject objeto = json.getJsonObject(i);
                                String id = objeto.getString("id");
                                String titulo = objeto.getString("titulo");
                        %>
                        <td class="celdaSolic"><%=objeto.getString("id")%></td>
                        <td class="celdaSolic"><%=objeto.getString("titulo")%></td>
                        <td class="celdaSolic"><%=objeto.getString("fecha")%></td>
                        <td class="celdaSolic"><%=objeto.getString("hora")%></td>
                        <td class="celdaSolic"><%=objeto.getString("direccion")%></td>
                        <td class="celdaSolic">
                            <form class="oculto" method="post" type="hidden" action="DetalleEvento">
                                <input name="idEvento" type="hidden" value="<%=id%>"/>
                                <input style="padding: 5px;" type="submit" class="botonDetalles" value="Detalles"/>
                            </form>
                        </td>
                    </tr>

                    <%}%>
                </table>

            </div>
            <div id="id01" class="modal" style="width: 100%;height: 100%; display: none;">
                <form class="modal-content animate" method="post" action="NuevoEvento" style="width: max-content">
                    <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
                    <p class="align"><label for="titulo">Título:</label>
                        <input type="text" id='titulo' name='titulo' placeholder="Título del evento..."><br>
                        <label>Fecha:</label>
                        <input type="date" id="fechaEvento" name="fecha">
                        <label>Hora</label>
                        <input type="time" id="horaEvento" name="hora">
                    <p>Categoría:
                        <select id="selectorCategoria" name="tablaCategoria">
                            <option selected></option>
                            <option value="reunion_formal">Reunión formal</option>
                            <option value="cumpleanos_infantil">Cumpleaños infantil</option>
                            <option value="cumpleanos">Cumpleaños</option>
                            <option value="boda">Boda</option>
                            <option value="ceremonia_religiosa">Ceremonia religiosa</option>
                            <option value="graduacion">Graduación</option>
                            <option value="despedida_soltero">Despedida de soltero</option>
                        </select>
                    </p>
                    <p class="align"><label for="direccion">Dirección:</label>
                        <input type="text" id='direccion' name='direccion' placeholder="Dirección..."><br>
                    </p>

                    <p class="align"><label for="presupuesto">Presupuesto:</label>
                        <input type="text" id='presupuesto' name='presupuesto' placeholder="Presupuesto..."><br>
                    </p>


                    <button class=" crearEventoAceptar " type="submit">Aceptar</button>
                    <!--<p><input class="submit" type="submit" value="Aceptar"></p>-->
                    <button onclick="document.getElementById('id01').style.display='none'" class=" crearEventoCancelar " type="button">Cancelar</button>

                </form>
            </div>

        </div>



        <script type="text/javascript">
            // Get the modal element
            var modal = document.getElementById('id01');
            // Get the button that opens the modal
            var btn = document.getElementById("botonCrearEvento");

            // When the user clicks the button, open the modal 
            btn.onclick = function () {
                if (modal.style.display === "block") {
                    modal.style.display = "none";
                } else {
                    modal.style.display = "block";
                }
            }

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target === modal) {
                    modal.style.display = "none";
                }
            }


        </script>


        <!-- Comienzo del footer -->
        <footer class="footer">

            <a class="boton" href="anyfest.com">¿Quiénes somos?</a>

            <a class="boton" href="anyfest.com/jobs">Trabaja con nosotros</a>

            <a class="boton" href="anyfest.com/help">¿Necesitas ayuda?</a>

            <a class="boton" href="anyfest.com/tos">Condiciones de uso</a>

        </footer>        
    </body>
</html>