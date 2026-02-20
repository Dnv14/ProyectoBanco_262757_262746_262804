
package com.mycompany.proyectobanco.dtos;

import com.mycompany.proyectobanco.entidades.Cuenta;
import com.mycompany.proyectobanco.entidades.Retiro.Estado;
import java.time.LocalDateTime;

/**
 *
 * @author Julian
 */
public class NuevoRetiroFormDTO {
    private Long monto;
    private LocalDateTime fechaHora;
    private Cuenta cuentaOrigen;
    private Estado estado;
    
    public NuevoRetiroFormDTO(){}
    
    public NuevoRetiroFormDTO(Long monto, LocalDateTime fechaHora, Cuenta cuentaOrigen, Estado estado) {
        this.monto = monto;
        this.fechaHora = fechaHora;
        this.cuentaOrigen = cuentaOrigen;
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }

    
    
    public Long getMonto() {
        return monto;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }
    
    
}
