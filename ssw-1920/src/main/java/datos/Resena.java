/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Timestamp;

/**
 *
 * @author istivan
 * @author marrobl
 * @author miggarr
 * @author silmont
 */
public class Resena {

    private String id_proveedor;
    private Timestamp fechahora;
    private int valor;
    private String idEvento;

    public Resena(String id_proveedor, Timestamp fechahora, int valor, String idEvento) {
        setId(id_proveedor);
        setFechahora(fechahora);
        setValor(valor);
        setIdEvento(idEvento);
    }

    private void setId(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    private void setFechahora(Timestamp fechahora) {
        this.fechahora = fechahora;
    }

    private void setValor(int valor) {
        this.valor = valor;
    }
    
    private void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }
    
    public String getId(){
        return id_proveedor;
    }
    
    public Timestamp getFechahora(){
        return fechahora;
    }
    
    public int getValor(){
        return valor;
    }
    
    public String getIdEvento() {
        return idEvento;
    }
}
