
package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.dtos.CobrarRetiroDTO;
import com.mycompany.proyectobanco.dtos.NuevoRetiroDTO;
import com.mycompany.proyectobanco.entidades.Retiro;
import com.mycompany.proyectobanco.entidades.Retiro.Estado;

/**
 *
 * @author Julian
 */
public interface IRetiroDAO {
    public abstract Retiro generarRetiroSinCuenta(NuevoRetiroDTO nuevoRetiro) throws PersistenciaException;
    public abstract boolean cobrarRetiroSinCuenta(Retiro cobroRetiro) throws PersistenciaException;
    public abstract Retiro verificarRetiroSinCuenta(CobrarRetiroDTO cobroRetiro) throws PersistenciaException;
}
