/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

/**
 *
 * @author istivan
 * @author marrobl
 * @author miggarr
 * @author silmont
 */
public class Solicitud {

    public Solicitud(String id_evento, String id_proveedor) {
        setId_evento(id_evento);
        setId_proveedor(id_proveedor);
    }
    
    private String id_evento;
    private String id_proveedor;
    private boolean aceptado = false;

    public String getId_evento() {
        return id_evento;
    }

    public void setId_evento(String id_evento) {
        this.id_evento = id_evento;
    }

    public String getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public void setAceptado(boolean aceptado) {
        this.aceptado = aceptado;
    }
}
