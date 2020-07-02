package control;

import datos.CategoriaProveedor;
import datos.Proveedor;
import datos.ProveedorDB;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "configProveedor", urlPatterns = {"/configProveedor"})
public class configProveedor extends HttpServlet {

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
            out.println("<title>Servlet configProveedor</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet configProveedor at " + request.getContextPath() + "</h1>");
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
        String old_usuario = (String) session.getAttribute("usuario");

        String usuario = request.getParameter("usuario");
        String passwd = request.getParameter("passwd");
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String categoria = request.getParameter("categoria");
        String descripcion = request.getParameter("descripcion");

        Proveedor proveedor = new Proveedor();
        if (usuario != null) {
            proveedor.setUsuario(usuario);
        }

        if (passwd != null) {
            proveedor.setPasswd(passwd);
        }

        if (nombre != null) {
            proveedor.setNombre(nombre);
        }

        if (email != null) {
            proveedor.setEmail(email);
        }

        if (telefono != null) {
            proveedor.setTelefono(telefono);
        }

        if (categoria != null && categoria.length() > 0) {
            proveedor.setCategoria(CategoriaProveedor.valueOf(categoria));
        }

        if (descripcion != null) {
            proveedor.setPasswd(descripcion);
        }

        ProveedorDB.update(proveedor, old_usuario);

        String url = "/CerrarSesion";
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
    }// </editor-fold>

}
