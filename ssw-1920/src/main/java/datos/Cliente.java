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
import javax.servlet.http.Part;

/**
 *
 * @author istivan
 * @author marrobl
 * @author miggarr
 * @author silmont
 */
/**
 * Implementación de un Cliente. Tiene nombre de usuario, password, nombre,
 * apellidos e email.
 *
 */
public class Cliente {

    private String usuario;
    private String nombre;
    private String passwd;
    private String apellidos;
    private String email;
    private Part imagen;

    /**
     * Constructor
     *
     * @param usuario
     * @param nombre
     * @param passwd
     * @param apellidos
     * @param email
     * @throws IllegalArgumentException
     */
    public Cliente(String usuario, String nombre, String passwd, String apellidos, String email, Part imagen)
            throws IllegalArgumentException {
        setUsuario(usuario);
        setNombre(nombre);
        setPasswd(passwd);
        setApellidos(apellidos);
        setEmail(email);
        setImagen(imagen);
    }
    
    public Cliente(){
        
    }

    public Cliente(String datos) {
        JsonReaderFactory jr = Json.createReaderFactory(null);
        JsonReader reader = jr.createReader(new StringReader(datos));
        JsonObject json = reader.readObject();
        
        setUsuario(json.getString("usuario"));
        setNombre(json.getString("nombre"));
        setPasswd(json.getString("passwd"));
        setApellidos(json.getString("apellidos"));
        setEmail(json.getString("email"));
    }

    /* SETTERS */
    public void setUsuario(String usuario) throws IllegalArgumentException {
        if (usuario.length() > 20) {
            throw new IllegalArgumentException("Usuario demasiado largo: límite 20 caracteres.");
        }
        this.usuario = usuario;
    }

    public void setNombre(String nombre) throws IllegalArgumentException {
        if (nombre.length() > 20) {
            throw new IllegalArgumentException("Nombre demasiado largo: límite 20 caracteres");
        }
        this.nombre = nombre;
    }

    public void setPasswd(String passwd) throws IllegalArgumentException {
        if (passwd.length() > 30) {
            throw new IllegalArgumentException("Password demasiado larga: límite 30 caracteres");
        }
        this.passwd = passwd;
    }

    public void setApellidos(String apellidos) {
        if (apellidos.length() > 50) {
            throw new IllegalArgumentException("Apellidos demasiado largos: límite 50 caracteres");
        }
        this.apellidos = apellidos;
    }

    public void setEmail(String email) {
        if (email.length() > 50) {
            throw new IllegalArgumentException("Email demasiado largo: límite 50 caracteres");
        }
        this.email = email;
    }

    public void setImagen(Part imagen) {
        this.imagen = imagen;
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

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public Part getImagen() {
        return imagen;
    }
}
