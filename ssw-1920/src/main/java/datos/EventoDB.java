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
public class EventoDB {

    public static int insert(Evento evento) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO EVENTO VALUES(?,?,?,?,?,?,?,?)";

        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, evento.getId());
            ps.setString(2, evento.getTitulo());
            ps.setString(3, evento.getDireccion());
            ps.setString(4, evento.getFecha().toString());
            ps.setString(5, evento.getHora().toString());
            ps.setString(6, evento.getCategoria().toString());
            ps.setString(7, Double.toString(evento.getPresupuesto()));
            ps.setString(8, evento.getCliente());

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

    public static String consultar(String usuario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "SELECT E.id, E.titulo, E.direccion, E.fecha, E.hora FROM EVENTO E WHERE E.cliente=?";
        
        JsonObjectBuilder jb = Json.createObjectBuilder();
        JsonArrayBuilder lista = Json.createArrayBuilder();
        
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,usuario);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                jb.add("id",rs.getString("id"));
                jb.add("titulo",rs.getString("titulo"));
                jb.add("direccion",rs.getString("direccion"));
                jb.add("fecha",rs.getString("fecha"));
                jb.add("hora",rs.getString("hora"));
                lista.add(jb.build());
            }
            
            rs.close();
            ps.close();
            
            pool.freeConnection(connection);

            StringWriter sw = new StringWriter();
            JsonWriter writer = Json.createWriter(sw);
            
            writer.writeArray(lista.build());
            writer.close();
            sw.close();
            return sw.toString();
            
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        
    }
    
    public static String detalles(String id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "SELECT E.id, E.titulo, E.direccion, E.fecha, E.hora FROM EVENTO E WHERE E.id=?";
        
        JsonObjectBuilder jb = Json.createObjectBuilder();
        
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                jb.add("id",rs.getString("id"));
                jb.add("titulo",rs.getString("titulo"));
                jb.add("direccion",rs.getString("direccion"));
                jb.add("fecha",rs.getString("fecha"));
                jb.add("hora",rs.getString("hora"));
                
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
    
    
}
