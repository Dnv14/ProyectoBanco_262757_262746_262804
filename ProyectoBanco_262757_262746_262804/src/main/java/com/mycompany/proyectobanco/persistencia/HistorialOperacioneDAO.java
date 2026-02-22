/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.dtos.HistorialOperacionesDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author BALAMRUSH
 */
public class HistorialOperacioneDAO implements IHistorialOperacionesDAO{

    private static final Logger LOGGER = Logger.getLogger(HistorialOperacioneDAO.class.getName());

    @Override
    public List<HistorialOperacionesDTO> consultarOperacionesCuenta(String numeroCuenta, String tipos) throws PersistenciaException {
        String comandoSQL;
            if(tipos.equalsIgnoreCase("Retiro")){
                comandoSQL = """
                             SELECT o.idOperacion, o.monto, o.fechaHora
                             FROM Operacion o
                             INNER JOIN RetiroSinCuenta r  ON o.idOperacion = r.idOperacion
                             WHERE o.numeroCuenta =?
                             ORDER BY o.fechaHora DESC;
                             """;
            }else if(tipos.equalsIgnoreCase("Transferencia")){
                comandoSQL = """
                            SELECT o.idOperacion, o.monto, o.fechaHora
                            FROM Operacion o
                            INNER JOIN Transferencia t
                            ON o.idOperacion = t.idOperacion
                            WHERE o.numeroCuenta = ?
                            ORDER BY o.fechaHora DESC;
                             """;
            }else{
                throw new PersistenciaException("Este tipo de Operacion no existe", null);
            }
        try{
            List<HistorialOperacionesDTO> listaOperaciones = new ArrayList<>();           
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(comandoSQL);
            comando.setString(1, numeroCuenta);
            ResultSet rs = comando.executeQuery();
            
            while(rs.next()){
                HistorialOperacionesDTO operacion = new HistorialOperacionesDTO(
                        rs.getInt("idOperacion"),
                        rs.getLong("monto"),
                        rs.getTimestamp("fechaHora").toLocalDateTime(),
                        tipos
                );
                listaOperaciones.add(operacion);            
                
                                  
            }
            conexion.close();
            return listaOperaciones;
                                         
        }catch(SQLException ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Existe un error al querer consultar operaciones", ex);
        }
    }


    @Override
    public List<String> obtenerNumerosCuenta() throws PersistenciaException {
        try{
            List<String> listaCuentas = new ArrayList<>();
        
            String comandoSQL = """
                                SELECT DISTINCT numeroCuenta
                                FROM Operacion;
                                """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(comandoSQL);
            ResultSet rs = comando.executeQuery();
            
            while(rs.next()){
                listaCuentas.add(rs.getString("numeroCuenta"));                       
            }
            conexion.close();
            return listaCuentas;
        }catch(SQLException ex){
            throw new PersistenciaException("No se pudo obtener el numero de cuenta", ex);
        }
        
        
    }
    
}
