
package com.mycompany.proyectobanco.negocio;

import com.mycompany.proyectobanco.entidades.Cuenta;
import com.mycompany.proyectobanco.persistencia.ICuentasDAO;
import com.mycompany.proyectobanco.persistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author Julian
 */
public class CuentasBO implements ICuentasBO{
    private final ICuentasDAO cuentasDAO;

    public CuentasBO(ICuentasDAO cuentasDAO) {
        this.cuentasDAO = cuentasDAO;
    }
    
    @Override
    public List<Cuenta> consultarCuentasCliente(Integer idCliente) throws NegocioException {
        if(idCliente == null){
            throw new NegocioException("El id del cliente no puede ser nulo.",null);
        }
        try {
            List<Cuenta> cuentasCliente = cuentasDAO.consultarCuentasCliente(idCliente);
            return cuentasCliente;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar las cuentas del cliente.",ex);
        }
    }
    
}
