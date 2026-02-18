
package com.mycompany.proyectobanco.entidades;

/**
 *
 * @author Julian
 */
public class Transferencia {
    
    private Integer idOperacion;
    private String cuentaDestino;
    
    public Transferencia(){}
    
    public Transferencia(Integer idOperacion, String cuentaDestino) {
        this.idOperacion = idOperacion;
        this.cuentaDestino = cuentaDestino;
    }

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }
    
    
}
