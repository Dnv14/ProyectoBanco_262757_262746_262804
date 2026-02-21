
package com.mycompany.proyectobanco.negocio;

import com.mycompany.proyectobanco.dtos.CobrarRetiroDTO;
import com.mycompany.proyectobanco.dtos.NuevoRetiroDTO;
import com.mycompany.proyectobanco.dtos.NuevoRetiroFormDTO;
import com.mycompany.proyectobanco.entidades.Retiro;

/**
 *
 * @author Julian
 */
public interface IRetiroBO {
    public abstract Retiro generarRetiroSinCuenta(NuevoRetiroFormDTO nuevoRetiro) throws NegocioException;
    public abstract Retiro cobrarRetiroSinCuenta(CobrarRetiroDTO cobroRetiro) throws NegocioException;
}
