package com.mycompany.proyectobanco.negocio;

import com.mycompany.proyectobanco.dtos.NuevaOperacionDTO;
import com.mycompany.proyectobanco.dtos.NuevaTransferenciaDTO;
import com.mycompany.proyectobanco.dtos.NuevaTransferenciaFormDTO;
import com.mycompany.proyectobanco.entidades.Cuenta;
import com.mycompany.proyectobanco.entidades.Operacion;
import com.mycompany.proyectobanco.entidades.Transferencia;
import com.mycompany.proyectobanco.persistencia.CuentasDAO;
import com.mycompany.proyectobanco.persistencia.ICuentasDAO;
import com.mycompany.proyectobanco.persistencia.IOperacionDAO;
import com.mycompany.proyectobanco.persistencia.ITransferenciaDAO;
import com.mycompany.proyectobanco.persistencia.OperacionDAO;
import com.mycompany.proyectobanco.persistencia.PersistenciaException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Diego
 */
public class TransferenciaBO implements ITransferenciaBO {

    private final ITransferenciaDAO transferenciaDAO;
    private final IOperacionBO operacionBO;
    
    /**
     * Al ser hija de operacion ocupa tanto su misma DAO como la BO de operacion
     * @param transferenciaDAO
     * @param operacionBO 
     */
    public TransferenciaBO(ITransferenciaDAO transferenciaDAO, IOperacionBO operacionBO) {
        this.transferenciaDAO = transferenciaDAO;
        this.operacionBO = operacionBO;
    }
    
    /**
     * se crea la transferencia, validando que la cuenta de origen no sea nulla ni la de destino
     * que esta sea de 16 digitos y que el monto no tenga parametros invalidos, asi usando 
     * el DTO para poder crear la nueva operacion, usando la BO para realizar la operacion, y cuando 
     * se haga poder crear y realizar la transferencia
     * @param nuevaTransferencia
     * @return
     * @throws NegocioException 
     */
    @Override
    public Transferencia crearTransferencia(NuevaTransferenciaFormDTO nuevaTransferencia) throws NegocioException {
        
        if (nuevaTransferencia.getCuentaOrigen() == null) {
            throw new NegocioException("la cuenta de origen no puede estar vacia", null);
        }
        if (nuevaTransferencia.getNumeroCuentaDestino() == null) {
            throw new NegocioException("La cuenta destino no puede estar vacia!", null);
        }
        
        if (nuevaTransferencia.getNumeroCuentaDestino().length() != 16) {
            throw new NegocioException("La cuenta destino tiene que tener 16 digitos!", null);
        }

        if(nuevaTransferencia.getMonto() <= 0) {
            throw new NegocioException("monto invalido", null);
        }

        try {
            NuevaOperacionDTO operacionDTO = new NuevaOperacionDTO(nuevaTransferencia.getMonto(), nuevaTransferencia.getFechaHora(), nuevaTransferencia.getCuentaOrigen().getNumeroCuenta());
            Operacion operacion = operacionBO.realizarOperacion(operacionDTO);
            NuevaTransferenciaDTO transferenciaDTO = new NuevaTransferenciaDTO(operacion.getIdOperacion(), nuevaTransferencia.getNumeroCuentaDestino());

            operacionBO.actualizarSaldoCuentaOrigen(operacionDTO);
            this.actualizarSaldoCuentaDestino(transferenciaDTO);

            Transferencia transferencia = this.transferenciaDAO.crearTransferencia(transferenciaDTO);
            return transferencia;

        } catch (PersistenciaException ex) {
            throw new NegocioException("error al crear el cliente", ex);

        }
    }
    
    /**
     * Actualizamos el saldo de la cuenta destino, usando las cuentas destinos
     * para poder recorrer y comparar cual tiene el mismo numero de cuenta
     * @param transferenciaDTO
     * @throws NegocioException 
     */
    @Override
    public void actualizarSaldoCuentaDestino(NuevaTransferenciaDTO transferenciaDTO) throws NegocioException {
        try {
            ICuentasDAO cuentasDAO = new CuentasDAO();
            Cuenta cuentaDestino = null;
            List<Cuenta> cuentasDestino = cuentasDAO.consultarCuentasActivas();

            for (Cuenta cuenta : cuentasDestino) {
                if (cuenta.getNumeroCuenta().equalsIgnoreCase(transferenciaDTO.getCuentaDestino())) {
                    cuentaDestino = cuenta;
                    break;
                }
            }

            if (cuentaDestino == null) {
                throw new NegocioException("No existe la cuenta destino ", null);
            }
            this.transferenciaDAO.actualizarSaldoCuentaDestino(transferenciaDTO);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible actualizar el saldo de la cuenta destino.", ex);
        }
    }

}
