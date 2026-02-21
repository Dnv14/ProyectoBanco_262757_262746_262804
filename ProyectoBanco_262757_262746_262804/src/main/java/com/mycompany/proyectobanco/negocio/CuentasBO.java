package com.mycompany.proyectobanco.negocio;

import com.mycompany.proyectobanco.entidades.Cuenta;
import com.mycompany.proyectobanco.entidades.Cuenta.Estado;
import com.mycompany.proyectobanco.persistencia.ICuentasDAO;
import com.mycompany.proyectobanco.persistencia.PersistenciaException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Julian
 */
public class CuentasBO implements ICuentasBO {

    private final ICuentasDAO cuentasDAO;

    public CuentasBO(ICuentasDAO cuentasDAO) {
        this.cuentasDAO = cuentasDAO;
    }

    @Override
    public List<Cuenta> consultarCuentasCliente(Long idCliente) throws NegocioException {
        if (idCliente == null) {
            throw new NegocioException("El id del cliente no puede ser nulo.", null);
        }
        try {
            List<Cuenta> cuentasCliente = cuentasDAO.consultarCuentasCliente(idCliente);
            return cuentasCliente;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar las cuentas del cliente.", ex);
        }
    }

    @Override
    public void cambiarEstadoCuenta(String numeroCuenta) throws NegocioException {
        if(numeroCuenta == null){
            throw new NegocioException("La cuenta no puede estar vacia", null);
        }
        try {
            String estadoActual = cuentasDAO.consultarEstadoCuenta(numeroCuenta);
            String nuevoEstado = null;
            
            if(estadoActual.equals("ACTIVO")){
                nuevoEstado = "INACTIVO";
            }
            if(estadoActual.equals("INACTIVO")){
                nuevoEstado = "ACTIVO";
            }
            
            cuentasDAO.actualizarEstadoCuenta(nuevoEstado, numeroCuenta);
            
        } catch (PersistenciaException ex) {
            throw new NegocioException("no se pudo cambiar el estado de las cuentas", ex);
        }

    }

}
//Estado estadoInactivo = Estado.INACTIVO;
//            Estado estadoActivo = Estado.ACTIVO;
//            List<Cuenta> cuentasCliente = cuentasDAO.consultarCuentasCliente(idCliente);
//            
//            for (Cuenta cuentas : cuentasCliente) {
//                if (cuentas.getNumeroCuenta().equals(numeroCuenta)) {
//
//                    if (cuentas.getEstado() == Estado.ACTIVO) {
//                        cuentas.setEstado(estadoInactivo);
//                        return;
//                    }
//                    if (cuentas.getEstado() == Estado.INACTIVO) {
//                        cuentas.setEstado(estadoActivo);
//                        return;
//                    }
//                }
//            }
//            