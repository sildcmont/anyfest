/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import datos.ProveedorDB;
import datos.Resena;
import datos.ResenaDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
@WebServlet(name = "DejarResena", urlPatterns = {"/DejarResena"})
public class DejarResena extends HttpServlet {

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
            out.println("<title>Servlet DejarResena</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DejarResena at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        HttpSession session = request.getSession();

        /* Obtengo los datos del request */
        String categoriaProveedor = request.getParameter("categoriaProveedor");
        String idEvento = request.getParameter("idEvento");
        int valoracion = Integer.valueOf(request.getParameter("valoracion"));

        /* Pido el id proveedor a ProveedorDB */
        String idProveedor = ProveedorDB.getId(categoriaProveedor, idEvento);

        LocalDateTime ldt = LocalDateTime.now();
        Timestamp ts = Timestamp.valueOf(ldt);

        Resena resena = new Resena(idProveedor, ts, valoracion, idEvento);
        
        ResenaDB.insert(resena);
        
        /* Redirigir a TusEventos */
        String url = "TusEventos";

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
    }// </editor-fold>

}
