/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

/**
 *
 * @author istivan
 * @author marrolb
 * @author miggarr
 * @author silmont
 */
public class ProveedorDB {

    public static int insert(Proveedor proveedor) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO PROVEEDOR (USUARIO,PASSWD,NOMBRE,"
                + "EMAIL , TELEFONO , CATEGORIAPROVEEDOR , DESCRIPCION) VALUES (?,?,?,?,?,?,?)";

        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, proveedor.getUsuario());
            ps.setString(2, proveedor.getPasswd());
            ps.setString(3, proveedor.getNombre());
            ps.setString(4, proveedor.getEmail());
            ps.setString(5, proveedor.getTelefono());
            ps.setString(6, proveedor.getCategoria().toString());
            ps.setString(7, proveedor.getDescripcion());

            int res = 0;

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                res = rs.getInt(1);
            }
            ps.close();

            pool.freeConnection(connection);
            return res;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String consultarDatos(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "SELECT * FROM PROVEEDOR P WHERE P.email=?";

        JsonObjectBuilder jb = Json.createObjectBuilder();

        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                jb.add("usuario", rs.getString("usuario"));
                jb.add("passwd", rs.getString("passwd"));
                jb.add("nombre", rs.getString("nombre"));
                jb.add("descripcion", rs.getString("descripcion"));
                jb.add("categoria", rs.getString("categoriaProveedor"));
                jb.add("email", rs.getString("email"));
            }

            rs.close();
            ps.close();

            pool.freeConnection(connection);

            StringWriter sw = new StringWriter();
            JsonWriter writer = Json.createWriter(sw);

            writer.writeObject(jb.build());
            writer.close();
            sw.close();
            return sw.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    public static boolean existe(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT usuario FROM proveedor WHERE email=?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);

            rs = ps.executeQuery();

            boolean res = rs.next();

            rs.close();
            ps.close();
            pool.freeConnection(connection);
            return res;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String consultar(String categoriaProveedor) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT P.USUARIO,P.NOMBRE,P.EMAIL,P.TELEFONO,P.categoriaProveedor,P.descripcion "
                + "FROM PROVEEDOR P "
                + "WHERE P.categoriaProveedor=?";

        JsonObjectBuilder jb = Json.createObjectBuilder();

        JsonArrayBuilder lista = Json.createArrayBuilder();

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, categoriaProveedor);

            rs = ps.executeQuery();

            while (rs.next()) {
                jb.add("usuario", rs.getString("usuario"));
                jb.add("nombre", rs.getString("nombre"));
                jb.add("email", rs.getString("email"));
                jb.add("telefono", rs.getString("telefono"));
                jb.add("categoriaProveedor", rs.getString("categoriaProveedor"));
                jb.add("descripcion", rs.getString("descripcion"));
                lista.add(jb.build());
            }

            pool.freeConnection(connection);
            rs.close();
            ps.close();

            pool.freeConnection(connection);

            StringWriter sw = new StringWriter();
            JsonWriter writer = Json.createWriter(sw);

            writer.writeArray(lista.build());
            writer.close();
            sw.close();
            return sw.toString();

        } catch (SQLException e) {
            e.printStackTrace();
            return "error";

        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
    
    public static String consultarPorNombre(String nombreProveedor,String categoriaProveedor) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT P.USUARIO,P.NOMBRE,P.EMAIL,P.TELEFONO,P.categoriaProveedor,P.descripcion "
                + "FROM PROVEEDOR P "
                + "WHERE P.categoriaProveedor=? and P.nombre LIKE '%" + nombreProveedor + "%'" ;
        
        System.out.println(query);

        JsonObjectBuilder jb = Json.createObjectBuilder();

        JsonArrayBuilder lista = Json.createArrayBuilder();

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, categoriaProveedor);

            rs = ps.executeQuery();

            while (rs.next()) {
                jb.add("usuario", rs.getString("usuario"));
                jb.add("nombre", rs.getString("nombre"));
                jb.add("email", rs.getString("email"));
                jb.add("telefono", rs.getString("telefono"));
                jb.add("categoriaProveedor", rs.getString("categoriaProveedor"));
                jb.add("descripcion", rs.getString("descripcion"));
                lista.add(jb.build());
            }

            pool.freeConnection(connection);
            rs.close();
            ps.close();

            pool.freeConnection(connection);

            StringWriter sw = new StringWriter();
            JsonWriter writer = Json.createWriter(sw);

            writer.writeArray(lista.build());
            writer.close();
            sw.close();
            return sw.toString();

        } catch (SQLException e) {
            e.printStackTrace();
            return "error";

        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public static void update(Proveedor proveedor, String usuario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = null;

        int res = 0;

        try {

            if (proveedor.getNombre() != null && proveedor.getNombre().length() > 0) {
                query = "UPDATE PROVEEDOR P SET P.nombre=? WHERE P.usuario=?";
                ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, proveedor.getNombre());
                ps.setString(2, usuario);
                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    res = res & rs.getInt(1);
                }

                ps.close();
            }

            if (proveedor.getCategoria() != null && proveedor.getCategoria().toString().length() > 0) {
                query = "UPDATE PROVEEDOR P SET P.categoriaProveedor=? WHERE P.usuario=?";
                ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, proveedor.getCategoria().toString());
                ps.setString(2, usuario);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    res = res & rs.getInt(1);
                }
                ps.close();
            }

            if (proveedor.getTelefono() != null && proveedor.getTelefono().length() > 0) {
                query = "UPDATE PROVEEDOR P SET P.telefono=? WHERE P.usuario=?";
                ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, proveedor.getTelefono());
                ps.setString(2, usuario);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    res = res & rs.getInt(1);
                }

                ps.close();
            }

            if (proveedor.getEmail() != null && proveedor.getEmail().length() > 0) {
                query = "UPDATE PROVEEDOR P SET P.email=? WHERE P.usuario=?";
                ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, proveedor.getEmail());
                ps.setString(2, usuario);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    res = res & rs.getInt(1);
                }

                ps.close();
            }

            if (proveedor.getPasswd() != null && proveedor.getPasswd().length() > 0) {
                query = "UPDATE PROVEEDOR P SET P.passwd=? WHERE P.usuario=?";
                ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, proveedor.getPasswd());
                ps.setString(2, usuario);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    res = res & rs.getInt(1);
                }

                ps.close();
            }

            if (proveedor.getUsuario() != null && proveedor.getUsuario().length() > 0) {
                query = "UPDATE PROVEEDOR P SET P.usuario=? WHERE P.usuario=?";
                ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, proveedor.getUsuario());
                ps.setString(2, usuario);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    res = res & rs.getInt(1);
                }

                ps.close();
            }

            pool.freeConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getId(String categoriaProveedor, String idEvento) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String idProveedor = null;

        String query = "SELECT P.usuario\n"
                + "FROM PROVEEDOR P, ELEMENTOCHECKLIST E\n"
                + "WHERE P.categoriaProveedor=? AND E.categoriaProveedor=P.categoriaProveedor\n"
                + "AND E.evento=?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, categoriaProveedor);
            ps.setString(2, idEvento);

            rs = ps.executeQuery();

            while (rs.next()) {
                idProveedor = rs.getString("usuario");
            }

            pool.freeConnection(connection);
            rs.close();
            ps.close();

            pool.freeConnection(connection);

            return idProveedor;

        } catch (SQLException e) {
            e.printStackTrace();
            return "No se encontraron proveedores";
        }
    }

}
