
package com.mycompany.proyectobanco.negocio;

import com.mycompany.proyectobanco.dtos.CobrarRetiroDTO;
import com.mycompany.proyectobanco.dtos.NuevaOperacionDTO;
import com.mycompany.proyectobanco.dtos.NuevoRetiroDTO;
import com.mycompany.proyectobanco.dtos.NuevoRetiroFormDTO;
import com.mycompany.proyectobanco.entidades.Operacion;
import com.mycompany.proyectobanco.entidades.Retiro;
import com.mycompany.proyectobanco.entidades.Retiro.Estado;
import com.mycompany.proyectobanco.persistencia.IRetiroDAO;
import com.mycompany.proyectobanco.persistencia.PersistenciaException;
import java.security.SecureRandom;

/**
 *
 * @author Julian
 */
public class RetiroBO implements IRetiroBO{
    private IOperacionBO operacionBO;
    private IRetiroDAO retiroDAO;
    
    public RetiroBO(IOperacionBO operacionBO,IRetiroDAO retiroDAO ){
        this.retiroDAO = retiroDAO;
        this.operacionBO = operacionBO;
    }
    @Override
    public Retiro generarRetiroSinCuenta(NuevoRetiroFormDTO nuevoRetiro) throws NegocioException {
        if(nuevoRetiro.getCuentaOrigen() == null){
            throw new NegocioException("La cuenta de origen no puede estar vacia.",null);
        }
        if(nuevoRetiro.getMonto() <= 0){
            throw new NegocioException("El monto debe ser mayor a 0.",null);
        }
        NuevaOperacionDTO operacionDTO = new NuevaOperacionDTO(nuevoRetiro.getMonto(),nuevoRetiro.getFechaHora(),nuevoRetiro.getCuentaOrigen().getNumeroCuenta());
        Operacion operacion = operacionBO.realizarOperacion(operacionDTO);
        
        NuevoRetiroDTO retiroDTO = new NuevoRetiroDTO(operacion.getIdOperacion(),generarContraseniaRetiro(),nuevoRetiro.getEstado());
        try {
            Retiro retiro = retiroDAO.generarRetiroSinCuenta(retiroDTO);
            operacionBO.actualizarSaldoCuentaOrigen(operacionDTO);
            return retiro;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al generar el retiro sin cuenta.",ex);
        }
    }
    
    @Override
    public Retiro cobrarRetiroSinCuenta(CobrarRetiroDTO cobroRetiro) throws NegocioException {
        if(cobroRetiro.getFolioOperacion() == null){
            throw new NegocioException("El folio de la operacion no debe estar vacio.",null);
        }
        if(cobroRetiro.getContrasenia() == null){
            throw new NegocioException("La contrasenia no debe estar vacia.",null);
        }
        if(cobroRetiro.getContrasenia().length()>8){
            throw new NegocioException("La contrasenia debe tener maximo 8 caracteres.",null);
        }
        try {
            Retiro retiro = retiroDAO.cobrarRetiroSinCuenta(cobroRetiro);
            if(retiro == null){
                throw new NegocioException("No se encontro ningun retiro sin cuenta con ese folio.",null);
            }
            return retiro;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo cobrar el retiro.",ex);
        }
    }
    
    private static final String CARACTERES =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz" +
            "0123456789";

    private static final SecureRandom random = new SecureRandom();
    
    private String generarContraseniaRetiro(){
        StringBuilder contrasenia = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int indice = random.nextInt(CARACTERES.length());
            contrasenia.append(CARACTERES.charAt(indice));
        }
        return contrasenia.toString();
    }
  
}
