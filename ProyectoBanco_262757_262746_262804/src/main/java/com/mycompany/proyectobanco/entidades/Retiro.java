
package com.mycompany.proyectobanco.entidades;

/**
 *
 * @author Julian
 */
public class Retiro {
    
    public enum Estado{
        ACTIVO,COBRADO, NO_COBRADO;
        
        @Override
        public String toString() {
            return name();
        }
    }
    
    private Integer idOperacion;
    private String contrasenia;
    private Integer folioRetiro;
    private Estado estado;
    
    public Retiro(){}
    
    public Retiro(Integer idOperacion, String contrasenia, Integer folioRetiro, Estado estado) {
        this.idOperacion = idOperacion;
        this.contrasenia = contrasenia;
        this.folioRetiro = folioRetiro;
        this.estado = estado;
    }

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Integer getFolioRetiro() {
        return folioRetiro;
    }

    public void setFolioRetiro(Integer folioRetiro) {
        this.folioRetiro = folioRetiro;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    
    
}
