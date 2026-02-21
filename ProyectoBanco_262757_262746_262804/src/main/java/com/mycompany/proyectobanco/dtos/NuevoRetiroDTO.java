
package com.mycompany.proyectobanco.dtos;

import com.mycompany.proyectobanco.entidades.Retiro;


/**
 *
 * @author Julian
 */
public class NuevoRetiroDTO {
    private Integer idOperacion;
    private String contrasenia;
    private Retiro.Estado estado;
    
    public NuevoRetiroDTO(){}
    
    public NuevoRetiroDTO(Integer idOperacion,String contrasenia, Retiro.Estado estado) {
        this.idOperacion = idOperacion;
        this.contrasenia = contrasenia;
        this.estado = estado;
    }

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public Retiro.Estado getEstado() {
        return estado;
    }
    
}
