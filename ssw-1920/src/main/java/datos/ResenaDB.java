/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

/**
 *
 * @author istivan
 * @author marrobl
 * @author miggarr
 * @author silmont
 */
public class ResenaDB {

    public static String consultar(String proveedor) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "SELECT AVG(R.valor) as valor FROM RESENA R WHERE R.proveedor=?";

        JsonObjectBuilder jb = Json.createObjectBuilder();

        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, proveedor);

            ResultSet rs = ps.executeQuery();

            if (rs.next() == false) {
                jb.add("valor", "No disponible");
                System.out.println("Salio false el rs.next");
            } else {
                if (rs.getString("valor") == null) {
                    System.out.println("rs. es null");
                    jb.add("valor", "No disponible");
                } else {
                    jb.add("valor", rs.getString("valor"));
                }
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

    public static void insert(Resena resena) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO RESENA VALUES(?,?,?,?)";

        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, resena.getId());
            ps.setString(2, resena.getFechahora().toString());
            ps.setString(3, String.valueOf(resena.getValor()));
            ps.setString(4, resena.getIdEvento());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            ps.close();
            pool.freeConnection(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
