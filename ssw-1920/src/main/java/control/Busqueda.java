/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import datos.ProveedorDB;
import datos.ResenaDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.json.JsonWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author miguelgarrido
 */
@WebServlet(name = "Busqueda", urlPatterns = {"/Busqueda"})
public class Busqueda extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Busqueda</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Busqueda at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        if (request.getParameter("textoBusqueda") == null ){
            HttpSession session = request.getSession();
        String categoriaProveedor = request.getParameter("categoriaProveedor");

        /* Obtener proveedores que dan servicio a esa categoria */
        String resultados = ProveedorDB.consultar(categoriaProveedor);

        /* Abrir conjunto de proveedores y obtener reseñas de la bd */
        JsonReaderFactory jr = Json.createReaderFactory(null);
        JsonReader reader = jr.createReader(new StringReader(resultados));
        JsonArray json = reader.readArray();
        JsonObjectBuilder salidaObj = Json.createObjectBuilder();
        JsonArrayBuilder salidaArr = Json.createArrayBuilder();

        for (int i = 0; i < json.size(); i++) {
            /* sacas objeto y obtienes reseñas */
            JsonObject objProv = json.getJsonObject(i);
            String usuario = objProv.getString("usuario");
            String nombre = objProv.getString("nombre");
            String email = objProv.getString("email");
            String telefono = objProv.getString("telefono");
            String categoria = objProv.getString("categoriaProveedor");
            String descripcion = objProv.getString("descripcion");

            /* Obtener media de las reseñas */
            String resenasJson = ResenaDB.consultar(usuario);
            JsonReader reader2 = jr.createReader(new StringReader(resenasJson));
            JsonObject resenasObj = reader2.readObject();
            String resenas = resenasObj.getString("valor");

            salidaObj.add("usuario", usuario);
            salidaObj.add("nombre", nombre);
            salidaObj.add("email", email);
            salidaObj.add("telefono", telefono);
            salidaObj.add("categoria", categoria);
            salidaObj.add("descripcion", descripcion);
            salidaObj.add("resenas", resenas);

            salidaArr.add(salidaObj.build());
        }

        StringWriter sw = new StringWriter();
        JsonWriter writer = Json.createWriter(sw);

        writer.writeArray(salidaArr.build());
        writer.close();
        sw.close();
        resultados = sw.toString();
        session.setAttribute("resultados", resultados);
        /* Reenviar */
        String url = "/ResultBusqueda.jsp";

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
        
        
        }else {
            
        HttpSession session = request.getSession();
        String categoriaProveedor = request.getParameter("categoriaProveedor");
        String campoTexto = request.getParameter("textoBusqueda");
        /* Obtener proveedores que dan servicio a esa categoria */
        String resultados = ProveedorDB.consultarPorNombre(campoTexto,categoriaProveedor);

        /* Abrir conjunto de proveedores y obtener reseñas de la bd */
        JsonReaderFactory jr = Json.createReaderFactory(null);
        JsonReader reader = jr.createReader(new StringReader(resultados));
        JsonArray json = reader.readArray();
        JsonObjectBuilder salidaObj = Json.createObjectBuilder();
        JsonArrayBuilder salidaArr = Json.createArrayBuilder();

        for (int i = 0; i < json.size(); i++) {
            /* sacas objeto y obtienes reseñas */
            JsonObject objProv = json.getJsonObject(i);
            String usuario = objProv.getString("usuario");
            String nombre = objProv.getString("nombre");
            String email = objProv.getString("email");
            String telefono = objProv.getString("telefono");
            String categoria = objProv.getString("categoriaProveedor");
            String descripcion = objProv.getString("descripcion");

            /* Obtener media de las reseñas */
            String resenasJson = ResenaDB.consultar(usuario);
            JsonReader reader2 = jr.createReader(new StringReader(resenasJson));
            JsonObject resenasObj = reader2.readObject();
            String resenas = resenasObj.getString("valor");

            salidaObj.add("usuario", usuario);
            salidaObj.add("nombre", nombre);
            salidaObj.add("email", email);
            salidaObj.add("telefono", telefono);
            salidaObj.add("categoria", categoria);
            salidaObj.add("descripcion", descripcion);
            salidaObj.add("resenas", resenas);

            salidaArr.add(salidaObj.build());
        }

        StringWriter sw = new StringWriter();
        JsonWriter writer = Json.createWriter(sw);

        writer.writeArray(salidaArr.build());
        writer.close();
        sw.close();
        resultados = sw.toString();
        session.setAttribute("resultados", resultados);
        /* Reenviar */
        String url = "/ResultBusqueda.jsp";

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
        
        
        }
       

        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String idChecklist = request.getParameter("idChecklist");
        String categoriaProveedor = request.getParameter("categoriaProveedor");
        String idEvento = request.getParameter("idEvento");
        session.setAttribute("idChecklist", idChecklist);
        session.setAttribute("categoriaProveedor", categoriaProveedor);
        session.setAttribute("idEvento", idEvento);

        String url = "/VistaBusqueda.jsp";
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}