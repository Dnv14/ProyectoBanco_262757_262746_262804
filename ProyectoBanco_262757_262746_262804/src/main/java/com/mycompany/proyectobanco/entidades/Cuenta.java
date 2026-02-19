
package com.mycompany.proyectobanco.entidades;

import java.util.GregorianCalendar;

/**
 *
 * @author Julian
 */
public class Cuenta {
    
    public enum Estado{
        ACTIVO,INACTIVO
    }
    
    private String numeroCuenta;
    private Estado estado;
    private GregorianCalendar fechaApertura;
    private Long saldo;
    private String idCliente;

    public Cuenta() {}
    
       
    public Cuenta(String numeroCuenta, Estado estado, GregorianCalendar fechaApertura, Long saldo, String idCliente) {
        this.numeroCuenta = numeroCuenta;
        this.estado = estado;
        this.fechaApertura = fechaApertura;
        this.saldo = saldo;
        this.idCliente = idCliente;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public Estado getEstado() {
        return estado;
    }

    public GregorianCalendar getFechaApertura() {
        return fechaApertura;
    }

    public Long getSaldo() {
        return saldo;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setFechaApertura(GregorianCalendar fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public void setSaldo(Long saldo) {
        this.saldo = saldo;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
    
    
}
