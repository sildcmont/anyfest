/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
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
/* Implementación de un Evento. */
public class Evento implements java.io.Serializable {
    private String id;
    private String titulo;
    private String direccion;
    private LocalDate fecha;
    private LocalTime hora;
    private CategoriaEvento categoria;
    private String cliente;
    private double presupuesto;

    
    public Evento() {
        UUID uuid = UUID.randomUUID();
        id = uuid.toString().substring(uuid.toString().length()-7,uuid.toString().length()); // Recortar el string a 8 char
        titulo = null;
        direccion = null;
        fecha = null;
        hora = null;
        categoria = null;
        cliente = null;
        presupuesto = 0;
    }
    /**
     * Inicializa un Evento con los valores pasados como parámetro. 
     * @param id
     * @param titulo
     * @param direccion
     * @param fecha
     * @param hora
     * @param categoria
     * @param cliente 
     */
    public Evento(String id, String titulo, String direccion, LocalDate fecha, LocalTime hora,
            CategoriaEvento categoria, String cliente, double presupuesto){
        setId(id);
        setTitulo(titulo);
        setDireccion(direccion);
        setFecha(fecha);
        setHora(hora);
        setCategoria(categoria);
        setCliente(cliente);  
        setPresupuesto(presupuesto);
    }
    
    public void setId(String id){
        if (id.length()>8) {throw new IllegalArgumentException("El id no puede tener más de 8 caracteres");}
        this.id=id;
    }
    
    public void setTitulo(String titulo){
        if (titulo.length()>20) {throw new IllegalArgumentException("El título no puede tener más de 20 caracteres");}
        this.titulo=titulo;
    }
    
    public void setDireccion(String direccion){
        if (direccion.length()>50){throw new IllegalArgumentException("La dirección no puede tener más de 50 caracteres");}
        this.direccion=direccion;
    }
    
    public void setFecha(LocalDate fecha) {
        this.fecha=fecha;
    }
    
    public void setHora(LocalTime hora) {
        this.hora=hora;
    }
    
    public void setCategoria(CategoriaEvento categoria) {
        this.categoria=categoria;
    }
    
    public void setCategoria(String categoria) {
        setCategoria(CategoriaEvento.valueOf(categoria));
    }
    
    public void setCliente(String cliente){
        if (cliente.length()>8) {throw new IllegalArgumentException("El usuario del cliente no puede tener más de 8 caracteres");}
        this.cliente = cliente;
    }
    
    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }
    
    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDireccion() {
        return direccion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public CategoriaEvento getCategoria() {
        return categoria;
    }

    public String getCliente() {
        return cliente;
    }
    
    public double getPresupuesto(){
        return presupuesto;
    }
    
    public String toJson() {
        
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("id",id);
        builder.add("titulo",titulo);
        builder.add("direccion",direccion);
        builder.add("fecha",fecha.toString());
        builder.add("hora",hora.toString());
        builder.add("categoria",categoria.toString());
        builder.add("cliente",cliente);
        builder.add("presupuesto",presupuesto);
        
        StringWriter sw = new StringWriter();
        JsonWriter writer = Json.createWriter(sw);
        writer.writeObject(builder.build());
        writer.close();
        
        return sw.toString();
    }
}
