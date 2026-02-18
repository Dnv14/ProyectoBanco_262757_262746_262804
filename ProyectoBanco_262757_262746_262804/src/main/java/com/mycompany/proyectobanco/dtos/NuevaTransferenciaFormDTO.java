
package com.mycompany.proyectobanco.dtos;

import com.mycompany.proyectobanco.entidades.Cuenta;

/**
 *
 * @author Julian
 */
public class NuevaTransferenciaFormDTO {
    private String numeroCuentaDestino;
    private Cuenta cuentaOrigen;
    private Integer monto;
    
    public NuevaTransferenciaFormDTO(){}
    
    public NuevaTransferenciaFormDTO(String numeroCuentaDestino, Cuenta cuentaOrigen, Integer monto) {
        this.numeroCuentaDestino = numeroCuentaDestino;
        this.cuentaOrigen = cuentaOrigen;
        this.monto = monto;
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
    
    
}
