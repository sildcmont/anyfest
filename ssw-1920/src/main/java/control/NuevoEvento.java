/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import datos.CategoriaEvento;
import datos.CategoriaProveedor;
import datos.ElementoChecklist;
import datos.ElementoChecklistDB;
import datos.Evento;
import datos.EventoDB;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "NuevoEvento", urlPatterns = {"/NuevoEvento"})
public class NuevoEvento extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        

        String usuario = (String) session.getAttribute("usuario");

        String titulo = request.getParameter("titulo");
        String fecha = request.getParameter("fecha");
        String hora = request.getParameter("hora");
        String categoria = request.getParameter("tablaCategoria");
        String direccion = request.getParameter("direccion");
        String presupuesto = request.getParameter("presupuesto");

        LocalDate fecha2 = LocalDate.parse(fecha);
        LocalTime hora2 = LocalTime.parse(hora);

        Evento evento = new Evento();
        evento.setTitulo(titulo);
        evento.setFecha(fecha2);
        evento.setHora(hora2);
        evento.setCategoria(CategoriaEvento.valueOf(categoria));
        evento.setDireccion(direccion);
        evento.setPresupuesto(Integer.parseInt(presupuesto));

        evento.setCliente(usuario);

        EventoDB.insert(evento);

        /* Crear checklist en función de la categoría del evento */
        switch (CategoriaEvento.valueOf(categoria)) {
            case reunion_formal:
                ElementoChecklist e1 = new ElementoChecklist(CategoriaProveedor.valueOf("catering"),evento.getId());
                ElementoChecklist e2 = new ElementoChecklist(CategoriaProveedor.valueOf("floristeria"),evento.getId());
                ElementoChecklistDB.insert(e1);
                ElementoChecklistDB.insert(e2);
                break;
            case cumpleanos_infantil:
                ElementoChecklistDB.insert(new ElementoChecklist(CategoriaProveedor.valueOf("dj"),evento.getId()));
                ElementoChecklistDB.insert(new ElementoChecklist(CategoriaProveedor.valueOf("catering"),evento.getId()));
                ElementoChecklistDB.insert(new ElementoChecklist(CategoriaProveedor.valueOf("animacion"),evento.getId()));
                break;
            case cumpleanos:
                ElementoChecklistDB.insert(new ElementoChecklist(CategoriaProveedor.valueOf("dj"),evento.getId()));
                ElementoChecklistDB.insert(new ElementoChecklist(CategoriaProveedor.valueOf("catering"),evento.getId()));
                ElementoChecklistDB.insert(new ElementoChecklist(CategoriaProveedor.valueOf("floristeria"),evento.getId()));
                ElementoChecklistDB.insert(new ElementoChecklist(CategoriaProveedor.valueOf("animacion"),evento.getId()));
               break;
            case boda:
                ElementoChecklistDB.insert(new ElementoChecklist(CategoriaProveedor.valueOf("dj"),evento.getId()));
                ElementoChecklistDB.insert(new ElementoChecklist(CategoriaProveedor.valueOf("catering"),evento.getId()));
                ElementoChecklistDB.insert(new ElementoChecklist(CategoriaProveedor.valueOf("floristeria"),evento.getId()));
                ElementoChecklistDB.insert(new ElementoChecklist(CategoriaProveedor.valueOf("animacion"),evento.getId()));
                ElementoChecklistDB.insert(new ElementoChecklist(CategoriaProveedor.valueOf("transporte"),evento.getId()));
                break;
            case ceremonia_religiosa:
                ElementoChecklistDB.insert(new ElementoChecklist(CategoriaProveedor.valueOf("floristeria"),evento.getId()));
                break;
            case graduacion:
                ElementoChecklistDB.insert(new ElementoChecklist(CategoriaProveedor.valueOf("dj"),evento.getId()));
                ElementoChecklistDB.insert(new ElementoChecklist(CategoriaProveedor.valueOf("catering"),evento.getId()));
                break;
            case despedida_soltero:
                ElementoChecklistDB.insert(new ElementoChecklist(CategoriaProveedor.valueOf("dj"),evento.getId()));
                ElementoChecklistDB.insert(new ElementoChecklist(CategoriaProveedor.valueOf("catering"),evento.getId()));
                ElementoChecklistDB.insert(new ElementoChecklist(CategoriaProveedor.valueOf("animacion"),evento.getId()));
        }

        String url = "/tusEventos.jsp";
        /* Seleccionar eventos de la BD */
        String eventos = EventoDB.consultar(usuario);
        session.setAttribute("eventos", eventos);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
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
