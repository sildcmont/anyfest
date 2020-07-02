/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import datos.Cliente;
import datos.ClienteDB;
import datos.Proveedor;
import datos.ProveedorDB;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author istivan
 * @author marrolb
 * @author miggarr
 * @author silmont
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
        String usuario = request.getParameter("usuario");
        String passwd = request.getParameter("passwd");

        String url ="";

        if (ClienteDB.existeCliente(usuario, passwd)) {
            Cliente cliente = new Cliente(ClienteDB.consultar(usuario));
            if (cliente.getPasswd().equals(passwd)) {
                url = "TusEventos";
                HttpSession session = request.getSession();
                usuario = cliente.getUsuario();
                session.setAttribute("usuario", usuario);

            } else {
                url = "ErrorLogin.jsp";
            }
        } else if (ProveedorDB.existe(usuario)) {
            Proveedor proveedor = new Proveedor(ProveedorDB.consultarDatos(usuario));
            if (proveedor.getPasswd().equals(passwd)) {
                url = "Solicitudes";
                HttpSession session = request.getSession();
                usuario = proveedor.getUsuario();
                session.setAttribute("usuario", usuario);

            } else {
                url = "ErrorLogin.jsp";
            }

        } else {
            url = "ErrorLogin.jsp";
        }
        response.sendRedirect(url);
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
