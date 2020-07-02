/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;
import java.io.StringWriter;
import java.sql.*;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;


/**
 *
 * @author istivan
 * @author marrolb
 * @author miggarr
 * @author silmont
 */
public class ClienteDB {
    
    public static int insert(Cliente cliente){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query ="INSERT INTO CLIENTE (USUARIO,PASSWD,NOMBRE,APELLIDOS,"
                + "EMAIL, IMAGEN) VALUES (?,?,?,?,?,?)";
        
        try {
            ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cliente.getUsuario());
            ps.setString(2,cliente.getPasswd());
            ps.setString(3,cliente.getNombre());
            ps.setString(4,cliente.getApellidos());
            ps.setString(5,cliente.getEmail());
            ps.setString(6, null);
            
            int res = 0;
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
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
    
    public static boolean existeCliente (String usuario,String passwd){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT usuario FROM cliente WHERE email=? and passwd=?";
        
        
        try {
           ps = connection.prepareStatement(query);
           ps.setString(1, usuario);
           ps.setString(2, passwd);

           
           rs =ps.executeQuery();
            
           boolean res = rs.next();
           
           rs.close();
           ps.close();
           pool.freeConnection(connection);
           return res;
           
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("El codigo de error es :" + e.getErrorCode());
            return false;
        }
    }
    
    public static int update(Cliente cliente, String old_usuario) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = null;

        int res = 0;

        try {

            if (cliente.getNombre() != null) {
                query = "UPDATE CLIENTE C SET C.nombre=? WHERE C.usuario=?";
                ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, cliente.getNombre());
                ps.setString(2, old_usuario);
                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    res = res & rs.getInt(1);
                }

                ps.close();
            }

            if (cliente.getApellidos() != null) {
                query = "UPDATE CLIENTE C SET C.apellidos=? WHERE C.usuario=?";
                ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, cliente.getApellidos());
                ps.setString(2, old_usuario);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    res = res & rs.getInt(1);
                }

                ps.close();
            }

            if (cliente.getEmail() != null) {
                query = "UPDATE CLIENTE C SET C.email=? WHERE C.usuario=?";
                ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, cliente.getEmail());
                ps.setString(2, old_usuario);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    res = res & rs.getInt(1);
                }

                ps.close();
            }

            if (cliente.getPasswd() != null) {
                query = "UPDATE CLIENTE C SET C.passwd=? WHERE C.usuario=?";
                ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, cliente.getPasswd());
                ps.setString(2, old_usuario);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    res = res & rs.getInt(1);
                }

                ps.close();
            }
            
            

            if (cliente.getUsuario() != null) {
                query = "UPDATE CLIENTE C SET C.usuario=? WHERE C.usuario=?";
                ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, cliente.getUsuario());
                ps.setString(2, old_usuario);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    res = res & rs.getInt(1);
                }

                ps.close();
            }

            pool.freeConnection(connection);
            return res;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public static String consultar (String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "SELECT * FROM CLIENTE C WHERE C.email=?";
        
        JsonObjectBuilder jb = Json.createObjectBuilder();
        JsonObjectBuilder grande = Json.createObjectBuilder();
        
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,email);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                jb.add("usuario",rs.getString("usuario"));
                jb.add("passwd",rs.getString("passwd"));
                jb.add("nombre",rs.getString("nombre"));
                jb.add("apellidos",rs.getString("apellidos"));
                jb.add("email",rs.getString("email"));
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
