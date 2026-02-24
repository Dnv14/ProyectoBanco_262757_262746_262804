/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobanco.negocio;

import com.mycompany.proyectobanco.dtos.NuevaOperacionDTO;
import com.mycompany.proyectobanco.entidades.Cuenta;
import com.mycompany.proyectobanco.entidades.Operacion;
import com.mycompany.proyectobanco.persistencia.CuentasDAO;
import com.mycompany.proyectobanco.persistencia.ICuentasDAO;
import com.mycompany.proyectobanco.persistencia.IOperacionDAO;
import com.mycompany.proyectobanco.persistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author BALAMRUSH
 */
public class OperacionBO implements IOperacionBO {
    
    private final IOperacionDAO operacionDAO;
    /**
     * constructor la cual solo requiere operacionDAO por que al ser la padre
     * no requiere de otras DAOS
     * @param operacionDAO 
     */
    public OperacionBO(IOperacionDAO operacionDAO) {
        this.operacionDAO = operacionDAO ;
    }
    
    /**
     * Realiza la operacion haciendo multiples validaciones, como si 
     * el numero de cuenta no puede ser nullo, un monto maximo, el numero de 
     * cuenta sea 16. Con esto asegurarnos que la operacion sea satisfactoria
     * para cumplir los requerimientos del proyecto, asi usando la DAO
     * realziar ooperacion el cual espera una DTO de esta misma entidad.
     * @param nuevaOperacionDTO
     * @return
     * @throws NegocioException 
     */
    @Override
    public Operacion realizarOperacion(NuevaOperacionDTO nuevaOperacionDTO) throws NegocioException {
        if (nuevaOperacionDTO.getNumeroCuenta()== null) {
            throw new NegocioException("El numero de cuenta es obligatorio", null);
        }
        
        if(nuevaOperacionDTO.getNumeroCuenta().length() != 16){
            throw new NegocioException("El número de cuenta es inválido", null);
        }
        if (nuevaOperacionDTO.getMonto() <= 0) {
            throw new NegocioException("parametros de monto invalidos ", null);
        }
        
        if(nuevaOperacionDTO.getMonto()>50000){
            throw new NegocioException("El limite maximo por transferencia es de: $50,000.00 ", null);
        }
             
        try {
            Operacion operacion = this.operacionDAO.realizarOperacion(nuevaOperacionDTO);
            return operacion;
            
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al crear la operacion", ex);
        }
    }
    
    /**
     * Actualiza el saldo de la cuenta verificando que la cuenta este activa para
     * poder asignarle el saldo, usando la validacion que la cuenta de origen
     * no puede ser null, asi mismo el nuevo saldo no puede ser igual a 0
     * evitando que existan parametros invalidos
     * como el 0 o valores negativos
     * @param operacionDTO
     * @throws NegocioException 
     */
    @Override
    public void actualizarSaldoCuentaOrigen(NuevaOperacionDTO operacionDTO) throws NegocioException {
        try {
            ICuentasDAO cuentasDAO = new CuentasDAO();
            Cuenta cuentaOrigen = null;
            List<Cuenta> cuentasBanco = cuentasDAO.consultarCuentasActivas();
            for(Cuenta cuenta: cuentasBanco){
                if(cuenta.getNumeroCuenta().equalsIgnoreCase(operacionDTO.getNumeroCuenta())){
                    cuentaOrigen = cuenta;
                }
            }
            if(cuentaOrigen == null){
                throw new NegocioException("No se encontro ninguna cuenta asociada a la operacion.",null);
            }
            Long nuevoSaldo = cuentaOrigen.getSaldo() - operacionDTO.getMonto();
            if(nuevoSaldo < 0){
                throw new NegocioException("No puede transferir mas de su saldo disponible.",null);
            }
            this.operacionDAO.actualizarSaldoCuentaOrigen(operacionDTO);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible actualizar el saldo de la cuenta origen.",ex);
        }
    }
    
    /**
     * Consultamos la operacion por id, util para apoyo de los metodos anteriores, 
     * ayudando a validar que el id no pueda estar vacio.
     * @param idOperacion
     * @return
     * @throws NegocioException 
     */
    @Override
    public Operacion consultarOperacionPorId(Integer idOperacion) throws NegocioException {
        if(idOperacion == null){
            throw new NegocioException("No debe estar  vacio el ID de la operacion.",null);
        }
        if(idOperacion <= 0 ){
            throw new NegocioException("No debe ser un ID negativo.",null);
        }
        try {
            Operacion operacion = operacionDAO.consultarOperacionPorId(idOperacion);
            return operacion;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible consultar la operacion con ese ID.",ex);
        }
    }

    
    
}
