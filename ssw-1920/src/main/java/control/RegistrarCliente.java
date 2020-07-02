/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import datos.Cliente;
import datos.ClienteDB;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

/**
 *
 * @author istivan
 * @author marrobl
 * @author miggarr
 * @author silmont
 */
@WebServlet(name = "RegistrarCliente", urlPatterns = {"/RegistrarCliente"})
@MultipartConfig
public class RegistrarCliente extends HttpServlet {

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
        String usuario = request.getParameter("usuario");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String passwd = request.getParameter("passwd");
        Part imagen = request.getPart("imagen");
 
        
        
        Cliente cliente = new Cliente();
        
        cliente.setUsuario(usuario);
        cliente.setNombre(nombre);
        cliente.setApellidos(apellidos);
        cliente.setEmail(email);
        cliente.setPasswd(passwd);
        cliente.setImagen(imagen);
        
        String url ="";
        
        url= "/login.html";
        
        
        ClienteDB.insert(cliente);
        
        RequestDispatcher dispatcher =   getServletContext().getRequestDispatcher(url);
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
    }// </editor-fold>

}
