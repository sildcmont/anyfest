/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.util.UUID;

/**
 *
 * @author istivan
 * @author marrobl
 * @author miggarr
 * @author silmont
 */
public class ElementoChecklist {
    private String id;
    private boolean aceptado = false;
    private CategoriaProveedor categoria;
    private String id_evento;
    
    public ElementoChecklist(CategoriaProveedor categoria, String id_evento) {
        UUID uuid = UUID.randomUUID();
        id = uuid.toString().substring(uuid.toString().length()-7,uuid.toString().length()); // Recortar el string a 8 char
        this.categoria = categoria;
        this.id_evento = id_evento;
    }
    
    public ElementoChecklist(String id, CategoriaProveedor categoria, String id_evento) {
        UUID uuid = UUID.randomUUID();
        id = uuid.toString().substring(uuid.toString().length()-7,uuid.toString().length()); // Recortar el string a 8 char
        setCategoria(categoria);
        setId_evento(id_evento);
    }
    
    public void setId(String id) {
        if (id.length()>8) {throw new IllegalArgumentException("El id no puede tener más de 8 caracteres");}
        this.id = id;
    }

    public void setAceptado(boolean aceptado) {
        this.aceptado = aceptado;
    }

    public void setCategoria(CategoriaProveedor categoria) {
        this.categoria = categoria;
    }

    public void setId_evento(String id_evento) {
        if (id_evento.length()>8) {throw new IllegalArgumentException("El id_evento no puede tener más de 8 caracteres");}
        this.id_evento = id_evento;
    }

    public String getId() {
        return id;
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public CategoriaProveedor getCategoria() {
        return categoria;
    }

    public String getId_evento() {
        return id_evento;
    }
    
}
