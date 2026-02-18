package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.dtos.NuevaTransferenciaDTO;
import com.mycompany.proyectobanco.entidades.Transferencia;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class TransferenciaDAO implements ITransferenciaDAO {
    
    private static final Logger LOGGER = Logger.getLogger(TransferenciaDAO.class.getName());
    
    @Override
    public Transferencia crearTransferencia(NuevaTransferenciaDTO nuevaTransferencia) throws PersistenciaException {
        try {
            String codigoSQL = """
                               
                               """; 
        } catch (Exception e) {
        }return null;
 
    }
    
}

//CREATE TABLE Transferencia (
//  idOperacion INT PRIMARY KEY NOT NULL,
//  cuentaDestino VARCHAR(16) NOT NULL,
//  FOREIGN KEY (idOperacion) REFERENCES Operacion(idOperacion),
//  FOREIGN KEY (cuentaDestino) REFERENCES Cuenta(numeroCuenta)
//);