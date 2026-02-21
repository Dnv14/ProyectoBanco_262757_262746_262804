
package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.entidades.Cuenta;
import com.mycompany.proyectobanco.entidades.Cuenta.Estado;
import java.util.List;

/**
 *
 * @author Julian
 */
public interface ICuentasDAO {
    public abstract List<Cuenta> consultarCuentasCliente(Long idCliente) throws PersistenciaException;
    public abstract List<Cuenta> consultarCuentasActivas() throws PersistenciaException;
    public abstract void actualizarEstadoCuenta(String estado,  String numeroCuenta) throws PersistenciaException;
    public abstract String consultarEstadoCuenta(String numeroCuenta)throws PersistenciaException;
}
