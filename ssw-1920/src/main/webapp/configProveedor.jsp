<!DOCTYPE html>
<html lang="es-ES">

    <head>
        <!-- Aqui van todos los metadatos de la pagina -->
        <meta charset="utf-8">
        <title>Configuración</title>
        <!-- Importar css -->
        <link rel="stylesheet" type="text/css" href="home.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    </head>


    <body>
        <%
            session = request.getSession();
            
            String usuario = (String) session.getAttribute("usuario");
        %>
        <!-- Aqui van todos los datos de la pagina -->
        <!-- Comienzo del header -->
        <header style="display: inline-block; background-color: #ffffff;">

            <!-- <head>Anyfest - Aquí tiene que ir el logo en forma de imagen</head> -->
            <div class="cabecera">
                <div style="float:left">
                    <a class="boton" href="index.html"><img style="margin: 20px 20px; max-width: 15%; width: auto;" src="img/anyfest-logo-transp.png" alt "anyfest-logo">
                    </a>
                </div>
                <div style="float: right; padding-top: 30px;">
                    <a class="botonCabecera" href="">Hola, <%=usuario%></a>
                    <a class="boton" href="CerrarSesion">Cerrar sesión<a>

                            </div>
                            </div>

                            </div>
                            </header>
                            <!-- Fin del header -->

                            <!-- Comienzo del formulario -->

                            <div class="signupprovider">
                                <h1 style="font-family: 'Montserrat' ,sans-serif; text-align: center;">Actualización de datos</h1>
                                <form method="post" action="configProveedor" >
                                    <p class="center"><label class="Montserrat">Usuario</label></p>
                                    <input name="usuario" type="text" size ="25" maxlength="30" placeholder="Usuario...">
                                    <p class="center"><label style="font-family: 'Montserrat',sans-serif">Nombre</label></p>
                                    <input name="nombre" type="text" size ="25" maxlength="30" placeholder="Nombre...">
                                    <p class="center" class="Montserrat">Categoría
                                        <select name="categoria" id="categoria">
                                            <option selected></option>
                                            <option  name="dj" value="dj">DJ</option>
                                            <option name="catering" value="catering">Catering</option>
                                            <option name="floristeria" value="floristeria">Floristeria</option>
                                            <option name="transporte" value="transporte">Transporte</option>
                                            <option name="animacion" value="animacion">Animacion</option>
                                        </select>
                                    </p>
                                    <p class="center"><label class="Montserrat">Correo electrónico</label></p>
                                    <input style="font-family: 'Montserrat',sans-serif" type="email" id="mail" name="email" placeholder="Nuevo correo electrónico...">
                                    <p class="center"><label class="Montserrat">Teléfono de contacto</label></p>
                                    <input name="telefono" type="tel" size ="25" maxlength="30" placeholder="Nuevo teléfono...">
                                    <p class="center"><label class="Montserrat">Descripción</label></p>
                                    <input name="descripcion" type="text" size="25" maxlength="500" placeholder="Nueva descripción...">
                                    <p class="center"><label class="Montserrat" for="pwd">Password</label></p>
                                    <input name="passwd" type="password" size="25" maxlength="25" placeholder="Nueva password...">
                                    <button class='registroProveedor' type="submit" class="sans-serif" type="button">Actualizar</button>
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
