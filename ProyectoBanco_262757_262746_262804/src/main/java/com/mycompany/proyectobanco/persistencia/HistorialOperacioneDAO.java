/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.dtos.HistorialOperacionesDTO;
import com.mycompany.proyectobanco.entidades.Operacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author BALAMRUSH
 */
public class HistorialOperacioneDAO implements IHistorialOperacionesDAO{

    private static final Logger LOGGER = Logger.getLogger(HistorialOperacioneDAO.class.getName());

    @Override
    public List<Operacion> consultarOperacionesCuenta(String numeroCuenta) throws PersistenciaException {
        try{
            List<Operacion> operaciones = new LinkedList<>();
            String comandoSQL = """
                                SELECT idOperacion, monto, fechaHora, numeroCuenta
                                FROM operaciones
                                WHERE numeroCuenta = ?;
                                """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(comandoSQL);
            comando.setString(1, numeroCuenta);
            ResultSet rs = comando.executeQuery();
            
            while(rs.next()){
                LocalDateTime fechaHora = LocalDateTime.parse(rs.getString("fechaHora"), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                Operacion op = new Operacion(
                        rs.getInt("idOperacion"),
                        rs.getLong("monto"),
                        fechaHora,
                        rs.getString("numeroCuenta")
                );
                operaciones.add(op);
            }
            comando.close();
            conexion.close();
            return operaciones;
        }catch(SQLException ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No es posible consultar las operacines de la cuenta", ex);
        }
    }

    @Override
    public List<Operacion> consultarTrasnferenciaCuenta(String numeroCuenta) throws PersistenciaException {
        try{
            List<Operacion> operaciones = new LinkedList<>();
            String comandoSQL = """
                                SELECT o.idOperacion, o.monto, o.fechaHora, o.numeroCuenta
                                FROM operaciones o
                                INNER JOIN Transferencias t ON o.idOperacion = t.idOperacion
                                WHERE o.numeroCuenta = ?;
                                """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(comandoSQL);
            comando.setString(1, numeroCuenta);
            ResultSet rs = comando.executeQuery();
            
            while(rs.next()){
                LocalDateTime fechaHora = LocalDateTime.parse(rs.getString("fechaHora"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                Operacion op = new Operacion(
                        rs.getInt("idOperacion"),
                        rs.getLong("monto"),
                        fechaHora,
                        rs.getString("numeroCuenta")
                );
                operaciones.add(op);
            }
            comando.close();
            conexion.close();
            return operaciones;
        }catch(SQLException ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No es posible consultar las operacines de la cuenta", ex);
        }
    }

    @Override
    public List<Operacion> consultarRetirosCuenta(String numeroCuenta) throws PersistenciaException {
        try{
            List<Operacion> operaciones = new LinkedList<>();
            String comandoSQL = """
                                SELECT o.idOperacion, o.monto, o.fechaHora, o.numeroCuenta
                                FROM operaciones o
                                INNER JOIN RetiroSinCuentas r ON o.idOperacion = r.idOperacion
                                WHERE o.numeroCuenta = ?;
                                """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(comandoSQL);
            comando.setString(1, numeroCuenta);
            ResultSet rs = comando.executeQuery();
            
            while(rs.next()){
                LocalDateTime fechaHora = LocalDateTime.parse(rs.getString("fechaHora"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                Operacion op = new Operacion(
                        rs.getInt("idOperacion"),
                        rs.getLong("monto"),
                        fechaHora,
                        rs.getString("numeroCuenta")
                );
                operaciones.add(op);
            }
            comando.close();
            conexion.close();
            return operaciones;
        }catch(SQLException ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No es posible consultar las operacines de la cuenta", ex);
        }
    }


    
    
}
