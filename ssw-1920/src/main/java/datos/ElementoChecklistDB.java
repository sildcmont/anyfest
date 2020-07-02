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
public class ElementoChecklistDB {
    public static int insert(ElementoChecklist elemento) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO ELEMENTOCHECKLIST VALUES(?,?,?,?)";

        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, elemento.getId().toString());
            ps.setBoolean(2, false);
            ps.setString(3, elemento.getCategoria().toString());
            ps.setString(4, elemento.getId_evento());
            
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
    
    public static String consultar(String idEvento){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "SELECT * FROM ELEMENTOCHECKLIST E WHERE E.evento=?";
        
        JsonObjectBuilder jb = Json.createObjectBuilder();
        
        JsonArrayBuilder lista = Json.createArrayBuilder();
        
        
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,idEvento);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                jb.add("id",rs.getString("id"));
                jb.add("aceptado",rs.getString("aceptado"));
                
                jb.add("categoriaProveedor",rs.getString("categoriaProveedor"));
                jb.add("idEvento", rs.getString("evento"));
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
    
    
    public static void update(String idEvento, String proveedor, boolean accion) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE ELEMENTOCHECKLIST E SET E.aceptado=? WHERE E.evento=? AND E.categoriaProveedor = (SELECT P.categoriaProveedor FROM Proveedor P where P.usuario=?)";

        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, accion);
            ps.setString(2, idEvento);
            ps.setString(3, proveedor);
           
            
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
    
}
