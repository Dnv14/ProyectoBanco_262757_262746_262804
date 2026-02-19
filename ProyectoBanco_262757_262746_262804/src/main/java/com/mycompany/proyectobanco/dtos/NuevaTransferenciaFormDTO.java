
package com.mycompany.proyectobanco.dtos;

import com.mycompany.proyectobanco.entidades.Cuenta;
import java.time.LocalDateTime;

/**
 *
 * @author Julian
 */
public class NuevaTransferenciaFormDTO {
    private String numeroCuentaDestino;
    private Cuenta cuentaOrigen;
    private Integer monto;
    private LocalDateTime fechaHora;
    
    public NuevaTransferenciaFormDTO(){}
    
    public NuevaTransferenciaFormDTO(String numeroCuentaDestino, Cuenta cuentaOrigen, Integer monto, LocalDateTime fechaHora) {
        this.numeroCuentaDestino = numeroCuentaDestino;
        this.cuentaOrigen = cuentaOrigen;
        this.monto = monto;
        this.fechaHora = fechaHora;
    }

    public String getNumeroCuentaDestino() {
        return numeroCuentaDestino;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public Integer getMonto() {
        return monto;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    
    
    
}
