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
public class SolicitudDB {

    /**
     * Consulta la base de datos para obtener las solicitudes del proveedor
     *
     * @param proveedor
     * @return
     */
    public static String consultar(String proveedor) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT E.id, E.categoriaEvento, E.titulo, E.direccion, E.fecha, E.hora, C.nombre, C.apellidos "
                + "FROM Solicitud S natural join Evento E natural join Cliente C "
                + "WHERE (S.proveedor=? AND S.pendiente=TRUE AND S.evento=E.id "
                + "AND E.cliente=C.usuario);";

        JsonObjectBuilder jb = Json.createObjectBuilder();
        JsonObjectBuilder grande = Json.createObjectBuilder();
        JsonArrayBuilder lista = Json.createArrayBuilder();

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, proveedor);

            rs = ps.executeQuery();
            String devolver = null;

            while (rs.next()) {
                jb.add("id", rs.getString("id"));
                jb.add("categoriaEvento", rs.getString("categoriaEvento"));
                jb.add("titulo", rs.getString("titulo"));
                jb.add("direccion", rs.getString("direccion"));
                jb.add("fecha", rs.getString("fecha"));
                jb.add("hora", rs.getString("hora"));
                jb.add("nombre", rs.getString("nombre"));
                jb.add("apellidos", rs.getString("apellidos"));

                /* Añadir al array */
                lista.add(jb.build());
            }

            /* Guarda la lista en el json array grande */
            rs.close();
            ps.close();

            pool.freeConnection(connection);

            StringWriter sw = new StringWriter();
            JsonWriter writer = Json.createWriter(sw);

            writer.writeArray(lista.build());

            writer.close();
            sw.close();
            devolver = sw.toString();

            return devolver;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void insertar(Solicitud solicitud) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO SOLICITUD VALUES(?,?,?,?)";

        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, solicitud.getId_evento());
            ps.setString(2, solicitud.getId_proveedor());
            ps.setString(3, null);
            ps.setBoolean(4, true);

            int res = 0;
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                res = rs.getInt(1);
            }
            ps.close();

            pool.freeConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(String idEvento, String proveedor, boolean accion) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = null;

        int res = 0;

        try {

            query = "UPDATE SOLICITUD S SET S.pendiente=FALSE, S.aceptado=? WHERE S.evento=? AND S.proveedor=?";
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, accion);
            ps.setString(2, idEvento);
            ps.setString(3, proveedor);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                res = res & rs.getInt(1);
            }

            ps.close();

            pool.freeConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
