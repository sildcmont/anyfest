/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import datos.Cliente;
import datos.ClienteDB;
import javax.servlet.http.HttpSession;

/**
 *
 * @author istivan
 * @author marrolb
 * @author miggarr
 * @author silmont
 */
@WebServlet(name = "configCliente", urlPatterns = {"/configCliente"})
public class configCliente extends HttpServlet {

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
        HttpSession session = request.getSession();
        String old_usuario = (String) session.getAttribute("usuario"); /* Obtenemos usuario de la sesion */
        
        String usuario = request.getParameter("usuario");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String passwd = request.getParameter("password");
        Cliente cliente = new Cliente();
        
        if (usuario!=null) {
            cliente.setUsuario(usuario);
        }
        
        if (nombre!=null) {
            cliente.setNombre(nombre);
        }
        
        if (apellidos!=null) {
            cliente.setApellidos(apellidos);
        }
        
        if (email!=null) {
            cliente.setEmail(email);
        }
        
        if (passwd!=null) {
            cliente.setPasswd(passwd);
        }
        
        ClienteDB.update(cliente,old_usuario);
        
        /* Redireccionamiento */
        String url ="";
        url= "index.html";
        response.setContentType("text/html;charset=UTF-8");
        response.sendRedirect(url);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
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
        processRequest(request, response);
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
