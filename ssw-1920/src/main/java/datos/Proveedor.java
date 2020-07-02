/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;

/**
 *
 * @author istivan
 * @author marrobl
 * @author miggarr
 * @author silmont
 */
/**
 * Implementación de un Proveedor. Tiene nombre de usuario, password, nombre,
 * apellidos e email.
 *
 */
public class Proveedor {
    private String usuario;
    private String nombre;
    private String passwd;
    private String descripcion;
    private CategoriaProveedor categoria;
    private String email;
    private String telefono;
    
    public Proveedor(String usuario, String nombre, String passwd, 
            String descripcion, CategoriaProveedor categoria,String email ,String telefono){
        setUsuario(usuario);
        setNombre(nombre);
        setPasswd(passwd);
        setDescripcion(descripcion);
        setCategoria(categoria);
        setEmail(email);
        
        
    }
    public Proveedor(){
    
    }
    
    public Proveedor(String datos){
        JsonReaderFactory jr = Json.createReaderFactory(null);
        JsonReader reader = jr.createReader(new StringReader(datos));
        JsonObject json = reader.readObject();
        
        setUsuario(json.getString("usuario"));
        setNombre(json.getString("nombre"));
        setPasswd(json.getString("passwd"));
        setDescripcion(json.getString("descripcion"));
        setCategoria(CategoriaProveedor.valueOf(json.getString("categoria")));
        setEmail(json.getString("email"));
    }
    
    /* SETTERS */
    public void setUsuario(String usuario) throws IllegalArgumentException {
        if (usuario.length() > 20) {
            throw new IllegalArgumentException("Usuario demasiado largo: límite "
                    + "20 caracteres.");
        }
        this.usuario = usuario;
    }

    public void setNombre(String nombre) throws IllegalArgumentException {
        if (nombre.length() > 50) {
            throw new IllegalArgumentException("Nombre demasiado largo: límite "
                    + "50 caracteres");
        }
        this.nombre = nombre;
    }

    public void setPasswd(String passwd) throws IllegalArgumentException {
        if (passwd.length() > 30) {
            throw new IllegalArgumentException("Password demasiado larga: límite"
                    + " 30 caracteres");
        }
        this.passwd = passwd;
    }

    public void setDescripcion(String descripcion) throws IllegalArgumentException {
        if (descripcion.length() > 100) {
            throw new IllegalArgumentException("Apellidos demasiado largos: límite"
                    + " 50 caracteres");
        }
        this.descripcion = descripcion;
    }
    
    public void setCategoria(CategoriaProveedor categoria){
        this.categoria=categoria;
    }

    public void setEmail(String email) throws IllegalArgumentException {
        if (email.length() > 50) {
            throw new IllegalArgumentException("Email demasiado largo: límite 50"
                    + " caracteres");
        }
        this.email = email;
    }
    
    public void setTelefono(String telefono) {
        if(telefono.length() > 10){
            throw new IllegalArgumentException("Telefono demasiado largo: límite 10"
                    + " caracteres");
        }
        this.telefono = telefono;
    }


    /* GETTERS */
    public String getUsuario() {
        return usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public CategoriaProveedor getCategoria(){
        return categoria;
    }

    public String getEmail() {
        return email;
    }

    /**
     * @return telefono
     */
    public String getTelefono() {
        return telefono;
    }
    
}


