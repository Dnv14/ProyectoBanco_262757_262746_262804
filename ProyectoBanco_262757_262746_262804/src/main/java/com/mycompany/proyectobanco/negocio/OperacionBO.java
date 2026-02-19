/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobanco.negocio;

import com.mycompany.proyectobanco.dtos.NuevaOperacionDTO;
import com.mycompany.proyectobanco.entidades.Cuenta;
import com.mycompany.proyectobanco.entidades.Operacion;
import com.mycompany.proyectobanco.persistencia.CuentasDAO;
import com.mycompany.proyectobanco.persistencia.ICuentasDAO;
import com.mycompany.proyectobanco.persistencia.IOperacionDAO;
import com.mycompany.proyectobanco.persistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author BALAMRUSH
 */
public class OperacionBO implements IOperacionBO {
    
    private final IOperacionDAO operacionDAO;

    public OperacionBO(IOperacionDAO operacionDAO) {
        this.operacionDAO = operacionDAO ;
    }
    
    @Override
    public Operacion realizarOperacion(NuevaOperacionDTO nuevaOperacionDTO) throws NegocioException {
        if (nuevaOperacionDTO.getNumeroCuenta()== null) {
            throw new NegocioException("El numero de cuenta es obligatorio", null);
        }
        
        if(nuevaOperacionDTO.getNumeroCuenta().length() != 16){
            throw new NegocioException("El número de cuenta es inválido", null);
        }
        if (nuevaOperacionDTO.getMonto() <= 0) {
            throw new NegocioException("parametros de monto invalidos ", null);
        }
        
        if(nuevaOperacionDTO.getMonto()>50000){
            throw new NegocioException("El limite maximo por transferencia es de: $50,000.00 ", null);
        }
             
        try {
            Operacion operacion = this.operacionDAO.realizarOperacion(nuevaOperacionDTO);
            return operacion;
            
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al crear la operacion", ex);
        }
    }

    @Override
    public void actualizarSaldoCuentaOrigen(NuevaOperacionDTO operacionDTO) throws NegocioException {
        try {
            ICuentasDAO cuentasDAO = new CuentasDAO();
            Cuenta cuentaOrigen = null;
            List<Cuenta> cuentasBanco = cuentasDAO.consultarCuentasActivas();
            for(Cuenta cuenta: cuentasBanco){
                if(cuenta.getNumeroCuenta().equalsIgnoreCase(operacionDTO.getNumeroCuenta())){
                    cuentaOrigen = cuenta;
                }
            }
            if(cuentaOrigen == null){
                throw new NegocioException("No se encontro ninguna cuenta asociada a la operacion.",null);
            }
            Long nuevoSaldo = cuentaOrigen.getSaldo() - operacionDTO.getMonto();
            if(nuevoSaldo < 0){
                throw new NegocioException("No puede transferir mas de su saldo disponible.",null);
            }
            this.operacionDAO.actualizarSaldoCuentaOrigen(operacionDTO);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible actualizar el saldo de la cuenta origen.",ex);
        }
    }

    
    
}
