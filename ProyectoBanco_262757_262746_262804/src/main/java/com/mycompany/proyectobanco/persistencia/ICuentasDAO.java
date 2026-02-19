
package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.entidades.Cuenta;
import java.util.List;

/**
 *
 * @author Julian
 */
public interface ICuentasDAO {
    public abstract List<Cuenta> consultarCuentasCliente(Integer idCliente) throws PersistenciaException;
}
