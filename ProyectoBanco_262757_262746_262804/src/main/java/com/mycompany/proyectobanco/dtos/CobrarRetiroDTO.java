
package com.mycompany.proyectobanco.dtos;

import com.mycompany.proyectobanco.entidades.Retiro.Estado;

/**
 *
 * @author Julian
 */
public class CobrarRetiroDTO {
    private String folioOperacion;
    private String contrasenia;
    private Estado estado;

    public CobrarRetiroDTO() {
    }

    public CobrarRetiroDTO(String folioOperacion, String contrasenia,Estado estado) {
        this.folioOperacion = folioOperacion;
        this.contrasenia = contrasenia;
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }
    
    public String getFolioOperacion() {
        return folioOperacion;
    }

    public String getContrasenia() {
        return contrasenia;
    }
    
    
}
