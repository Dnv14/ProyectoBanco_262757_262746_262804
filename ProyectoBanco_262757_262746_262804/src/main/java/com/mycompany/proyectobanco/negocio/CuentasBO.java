package com.mycompany.proyectobanco.negocio;

import com.mycompany.proyectobanco.dtos.NuevaCuentaDTO;
import com.mycompany.proyectobanco.entidades.Cuenta;
import com.mycompany.proyectobanco.persistencia.IClientesDAO;

import com.mycompany.proyectobanco.persistencia.ICuentasDAO;
import com.mycompany.proyectobanco.persistencia.PersistenciaException;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Julian
 */
public class CuentasBO implements ICuentasBO {

    private final ICuentasDAO cuentasDAO;
    private final IClientesDAO clientesDAO;
    
    /**
     * constructor el cual requiere tanto de las cuentasDAO como la de los clientes
     * esto, por su union en la base de datos
     * @param cuentasDAO
     * @param clientesDAO 
     */
    public CuentasBO(ICuentasDAO cuentasDAO, IClientesDAO clientesDAO) {
        this.cuentasDAO = cuentasDAO;
        this.clientesDAO = clientesDAO;
    }
    
    /**
     * consultamos las cuentas, validando que el id exista en la base de datos usando
     * el metodo de consultar cuentas clientes para poder retornar las listas.
     * @param idCliente
     * @return
     * @throws NegocioException 
     */
    @Override
    public List<Cuenta> consultarCuentasCliente(Long idCliente) throws NegocioException {
        if (idCliente == null) {
            throw new NegocioException("El id del cliente no puede ser nulo.", null);
        }
        try {
            List<Cuenta> cuentasCliente = cuentasDAO.consultarCuentasCliente(idCliente);
            return cuentasCliente;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al consultar las cuentas del cliente.", ex);
        }
    }
    
    /**
     * vañodamos que la cuenta exista, usando este metodo para poder cambiar 
     * el estado de la cuenta, usando el metodo de la DAO consultando el estado 
     * actual de la cuenta, para poder tomar una decision si deberia ser
     * activo o inactivo
     * @param numeroCuenta
     * @throws NegocioException 
     */
    @Override
    public void cambiarEstadoCuenta(String numeroCuenta) throws NegocioException {
        if (numeroCuenta == null) {
            throw new NegocioException("La cuenta no puede estar vacia", null);
        }
        try {
            String estadoActual = cuentasDAO.consultarEstadoCuenta(numeroCuenta);
            String nuevoEstado = null;

            if (estadoActual.equals("ACTIVO")) {
                nuevoEstado = "INACTIVO";
            }
            if (estadoActual.equals("INACTIVO")) {
                nuevoEstado = "ACTIVO";
            }

            cuentasDAO.actualizarEstadoCuenta(nuevoEstado, numeroCuenta);

        } catch (PersistenciaException ex) {
            throw new NegocioException("no se pudo cambiar el estado de las cuentas", ex);
        }

    }
    
    /**
     * Se crea la cuenta usando if's que validan que la contraseña puesta en el 
     * form sea correcta y llamando el metodo DAO obtener las contrasenias
     * para poder hacer esta comparacion y que la creacion de la cuenta sea
     * exitosa
     * @param cuentaDTO
     * @param contrasenia
     * @return
     * @throws NegocioException 
     */
    @Override
    public Cuenta crearCuenta(NuevaCuentaDTO cuentaDTO, String contrasenia) throws NegocioException {
        try {
            List<String> contrasenias = clientesDAO.obtenerTodasLasContrasenias();

            if (!contrasenias.contains(contrasenia)) {
                throw new NegocioException("Contraseña incorrecta", null);
            }
        } catch (PersistenciaException ex) {
            throw new NegocioException("no fue posible verificar la contraseña", ex);
        }

        try {
            String numeroCuenta = generarNumeroCuenta();

            NuevaCuentaDTO nuevaCuentaDTO = new NuevaCuentaDTO(numeroCuenta, cuentaDTO.getEstado(), cuentaDTO.getFechaApertura(), cuentaDTO.getSaldo(), cuentaDTO.getIdCliente());

            Cuenta cuentaCreada = this.cuentasDAO.crearCuenta(nuevaCuentaDTO);
            return cuentaCreada;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo crear la cuenta ", ex);
        }

    }
    
    /**
     * Metodo el cual genera un numero aleatorio de 16 digitos para poder
     * asignarlo a la cuenta que el usuario quiera crear, retornando un
     * String el cual es el numero generado, para poder asignarse en el form
     * @return
     * @throws NegocioException 
     */
    @Override
    public String generarNumeroCuenta() throws NegocioException {
        Random random = new Random();
        String numeroCuenta = "";
        boolean repetido = true;

        try {
            while (repetido) {
                numeroCuenta = "";
                numeroCuenta += (random.nextInt(9) + 1);

                for (int i = 1; i < 16; i++) {
                    numeroCuenta += (random.nextInt(10));
                }

                repetido = false;

                List<String> cuentas = cuentasDAO.consultarCuentaNumero(numeroCuenta);
                for (String cuentasNumero : cuentas) {
                    if (cuentasNumero.equals(numeroCuenta)) {
                        repetido = true;
                        break;
                    }
                }
            }
            return numeroCuenta;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo crear la cuenta ", ex);
        }

    }

}
//Estado estadoInactivo = Estado.INACTIVO;
//            Estado estadoActivo = Estado.ACTIVO;
//            List<Cuenta> cuentasCliente = cuentasDAO.consultarCuentasCliente(idCliente);
//            
//            for (Cuenta cuentas : cuentasCliente) {
//                if (cuentas.getNumeroCuenta().equals(numeroCuenta)) {
//
//                    if (cuentas.getEstado() == Estado.ACTIVO) {
//                        cuentas.setEstado(estadoInactivo);
//                        return;
//                    }
//                    if (cuentas.getEstado() == Estado.INACTIVO) {
//                        cuentas.setEstado(estadoActivo);
//                        return;
//                    }
//                }
//            }
//            
