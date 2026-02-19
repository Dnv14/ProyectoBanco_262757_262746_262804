package com.mycompany.proyectobanco.negocio;

import com.mycompany.proyectobanco.dtos.NuevaTransferenciaDTO;
import com.mycompany.proyectobanco.entidades.Transferencia;
import com.mycompany.proyectobanco.persistencia.ITransferenciaDAO;
import com.mycompany.proyectobanco.persistencia.PersistenciaException;

/**
 *
 * @author Diego
 */
public class TransferenciaBO implements ITransferenciaBO {

    private final ITransferenciaDAO transferenciaDAO;

    public TransferenciaBO(ITransferenciaDAO transferenciaDAO) {
        this.transferenciaDAO = transferenciaDAO;
    }

    @Override
    public Transferencia crearTransferencia(NuevaTransferenciaDTO nuevaTransferencia) throws NegocioException {
        if(nuevaTransferencia.getCuentaDestino() == null){
            throw new NegocioException("La cuenta destino no puede estar vacia!", null);
        }
        
        if(nuevaTransferencia.getIdOperacion() == null || nuevaTransferencia.getIdOperacion() < 0){
            throw new NegocioException("Id de operacion invalido!!", null);
        }
        
        if(nuevaTransferencia.getCuentaDestino().length() != 16){
            throw new NegocioException("La cuenta destino tiene que tener 16 digitos!", null);
        }
        
        try {

            Transferencia transferencia = this.transferenciaDAO.crearTransferencia(nuevaTransferencia);
            return transferencia;
            
        } catch (PersistenciaException ex) {
            throw new NegocioException("error al crear el cliente", ex);

        }
    }

}
