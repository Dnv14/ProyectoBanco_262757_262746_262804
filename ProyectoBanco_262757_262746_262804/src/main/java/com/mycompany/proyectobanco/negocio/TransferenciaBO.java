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

    public TransferenciaBO(ITransferenciaDAO transferenciaDAO, IOperacionBO operacionBO) {
        this.transferenciaDAO = transferenciaDAO;
        this.operacionBO = operacionBO;
    }

    @Override
    public Transferencia crearTransferencia(NuevaTransferenciaFormDTO nuevaTransferencia) throws NegocioException {
//        if (nuevaTransferencia.getCuentaDestino() == null) {
//            throw new NegocioException("La cuenta destino no puede estar vacia!", null);
//        }
//
//        if (nuevaTransferencia.getIdOperacion() == null || nuevaTransferencia.getIdOperacion() < 0) {
//            throw new NegocioException("Id de operacion invalido!!", null);
//        }
//
//        if (nuevaTransferencia.getCuentaDestino().length() != 16) {
//            throw new NegocioException("La cuenta destino tiene que tener 16 digitos!", null);
//        }

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

    @Override
    public void actualizarSaldoCuentaDestino(NuevaTransferenciaDTO transferenciaDTO) throws NegocioException {
        try {
//            IOperacionDAO cuentasDAO = new OperacionDAO();
//            Operacion cuentaDestino = null;
//            List<Operacion> cuentasDestino = cuentasDAO.consultarOperaciones();
//            
//            for(Operacion operaciones: cuentasDestino){
//                if(operaciones.getNumeroCuenta().equalsIgnoreCase(transferenciaDTO.getCuentaDestino())){
//                    cuentaDestino = operaciones;
//                }
//            }
//            if(cuentaDestino == null){
//                throw new NegocioException("No se encontro ninguna cuenta destinada a la operacion.",null);
//            }

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
