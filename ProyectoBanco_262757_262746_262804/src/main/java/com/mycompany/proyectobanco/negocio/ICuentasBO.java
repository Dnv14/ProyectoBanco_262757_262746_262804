
package com.mycompany.proyectobanco.negocio;

import com.mycompany.proyectobanco.dtos.NuevaCuentaDTO;
import com.mycompany.proyectobanco.entidades.Cuenta;
import java.util.List;

/**
 *
 * @author Julian
 */
public interface ICuentasBO {
    public abstract List<Cuenta> consultarCuentasCliente(Long idCliente) throws NegocioException;
    public abstract void cambiarEstadoCuenta(String numeroCuentae) throws NegocioException;
    public abstract Cuenta crearCuenta(NuevaCuentaDTO cuentaDTO,String contrasenia)throws NegocioException;
    public String generarNumeroCuenta() throws NegocioException;
}
