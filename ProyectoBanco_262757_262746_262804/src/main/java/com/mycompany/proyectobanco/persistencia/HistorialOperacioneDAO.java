/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.entidades.Operacion;
import com.mycompany.proyectobanco.entidades.Retiro;
import com.mycompany.proyectobanco.entidades.Retiro.Estado;
import com.mycompany.proyectobanco.entidades.Transferencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
    public List<Operacion> consultarOperacionesCuenta(String numeroCuenta) throws PersistenciaException {
        try{
            List<Operacion> listaOperaciones = new ArrayList<>();
            String comandoSQL = """
                                SELECT *
                                FROM Operaciones o
                                WHERE numeroCuenta =?;
                                """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(comandoSQL);
            comando.setString(1, numeroCuenta);
            ResultSet rs = comando.executeQuery();
            
            while(rs.next()){
                Operacion operacion = new Operacion(
                        rs.getInt("idOperacion"),
                        rs.getLong("monto"),
                        rs.getTimestamp("fechaHora").toLocalDateTime(),
                        rs.getString("numeroCuenta")
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
    public List<Retiro> consultarRetirosCuenta(String numeroCuenta) throws PersistenciaException {
        try{
            List<Retiro> listaRetiros = new ArrayList<>();
            String comandoSQL = """
                                SELECT o.idOperacion, o.monto, o.fechaHora, o.numeroCuenta, r.contraseña, r.folio, r.estado
                                FROM Operacion o
                                INNER JOIN RetiroSinCuenta r ON o.idOperacion = r.idOperacion
                                WHERE o.numeroCuenta = ?;
                            """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(comandoSQL);
            comando.setString(1, numeroCuenta);
            
            ResultSet rs = comando.executeQuery();
            
            while(rs.next()){
                LocalDateTime fechaHora = rs.getTimestamp("fechaHora").toLocalDateTime();
                Retiro retiro = new Retiro(
                        rs.getInt("idOperacion"),
                        rs.getString("contraseña"),
                        rs.getInt("folio"),
                        Retiro.Estado.valueOf(rs.getString("estado"))
                );
                
                listaRetiros.add(retiro);       
            }
            conexion.close();
            return listaRetiros;
        }catch(SQLException ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No se pudo encontrar el retiro", ex);          
        }
        
        
    }

    @Override
    public List<Transferencia> consultarTransferenciaCuenta(String numeroCuenta) throws PersistenciaException {
        
        try{
            List<Transferencia> listaTransferencia = new ArrayList<>();
            String comandoSQL = """
                                SELECT o.idOperacion, o.monto, o.fechaHora, o.numeroCuenta, t.cuentaDestino
                                FROM Operacion o
                                INNER JOIN Transferencia t ON o.idOperacion = t.idOperacion
                                WHERE o.numeroCuenta = ?;
                                
                                """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(comandoSQL);
            comando.setString(1, numeroCuenta);
            ResultSet rs = comando.executeQuery();
            
            while(rs.next()){
                Transferencia transferencia = new Transferencia(
                        rs.getInt("idOperacion"),
                        rs.getString("cuentaDestino")
                );
                
                listaTransferencia.add(transferencia);
            }
            conexion.close();
            return listaTransferencia;
        }catch(SQLException ex){
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Existió un error al querer acceder a las transacciones", ex);
            
        }
        
        
        
    }

  
  

    
    
    
}
