
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
import java.time.LocalDateTime;

/**
 *
 * @author Julian
 */
public class RetiroBO implements IRetiroBO{
    private IOperacionBO operacionBO;
    private IRetiroDAO retiroDAO;
    
    /**
     * al extendert de operacion retiro ocupa tanto su misma DAO como 
     * la DAO de operacion
     * @param operacionBO
     * @param retiroDAO 
     */
    public RetiroBO(IOperacionBO operacionBO,IRetiroDAO retiroDAO ){
        this.retiroDAO = retiroDAO;
        this.operacionBO = operacionBO;
    }
    
    /**
     * genera el retiro de la cuenta, validando que no sea null, y el saldo
     * no tenga paraemtros invalidos, creando la nueva operacion
     * y asi llamando a la BO para realizarla
     * @param nuevoRetiro
     * @return
     * @throws NegocioException 
     */
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
    
    /**
     * Se cobra el retiro el cual se realizo, utilizando validadores
     * para corroborar que el folio no este vacio, la constraseña sea valida
     * y con esto pasamos verificar el retiro sin cuenta, validando que 
     * el retiro no haya sido cobrado anteriormente o directamente no exista ningun
     * retiro sin cuenta.
     * @param cobroRetiro
     * @return
     * @throws NegocioException 
     */
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
            Retiro retiro = retiroDAO.verificarRetiroSinCuenta(cobroRetiro);
            if(retiro == null){
                throw new NegocioException("No se encontro ningun retiro sin cuenta con ese folio.",null);
            }
            if(retiro.getEstado() == Estado.COBRADO){
                throw new NegocioException("El retiro ya ha sido cobrado.",null);
            }
            Operacion operacion = operacionBO.consultarOperacionPorId(retiro.getIdOperacion());
            LocalDateTime ahora = LocalDateTime.now();
            if (ahora.isAfter(operacion.getFechaHora().plusMinutes(10))) {
                retiro.setEstado(Estado.NO_COBRADO);
                retiroDAO.cobrarRetiroSinCuenta(retiro);
                throw new NegocioException("El retiro ha expirado.", null);
            }
            retiro.setEstado(Estado.COBRADO);
            
            boolean retiroCobrado = retiroDAO.cobrarRetiroSinCuenta(retiro);
            
            if(!retiroCobrado){
                throw new NegocioException("El retiro no se pudo cobrar correctamente.",null);
            }
            return retiro;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo cobrar el retiro.",ex);
        }
    }
    
    /**
     * Caracteres los cuales son los posibles candidatos
     * para la contraseña generada
     */
    private static final String CARACTERES =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz" +
            "0123456789";

    private static final SecureRandom random = new SecureRandom();
    
    /**
     * se genera la contraseña con los caracteres posibles
     * mencionados anteriormente
     * @return 
     */
    private String generarContraseniaRetiro(){
        StringBuilder contrasenia = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int indice = random.nextInt(CARACTERES.length());
            contrasenia.append(CARACTERES.charAt(indice));
        }
        return contrasenia.toString();
    }
  
}
