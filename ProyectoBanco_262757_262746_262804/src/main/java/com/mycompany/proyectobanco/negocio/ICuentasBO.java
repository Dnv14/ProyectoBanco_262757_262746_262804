
package com.mycompany.proyectobanco.negocio;

import com.mycompany.proyectobanco.entidades.Cuenta;
import java.util.List;

/**
 *
 * @author Julian
 */
public interface ICuentasBO {
    public abstract List<Cuenta> consultarCuentasCliente(Integer idCliente) throws NegocioException;
}
