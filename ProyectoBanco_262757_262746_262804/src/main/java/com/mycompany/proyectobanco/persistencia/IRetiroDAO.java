
package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.dtos.NuevoRetiroDTO;
import com.mycompany.proyectobanco.entidades.Retiro;

/**
 *
 * @author Julian
 */
public interface IRetiroDAO {
    public abstract Retiro generarRetiroSinCuenta(NuevoRetiroDTO nuevoRetiro) throws PersistenciaException;
}
