
package com.mycompany.proyectobanco.persistencia;

import com.mycompany.proyectobanco.dtos.CobrarRetiroDTO;
import com.mycompany.proyectobanco.dtos.NuevoRetiroDTO;
import com.mycompany.proyectobanco.entidades.Retiro;
import com.mycompany.proyectobanco.entidades.Retiro.Estado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 *
 * @author Julian
 */
public class RetiroDAO implements IRetiroDAO{

    private static final Logger LOGGER = Logger.getLogger(RetiroDAO.class.getName());
    
    
    /**
     * genera un retiro sin cuenta el cual se llena con la dto de nuevo retiro
     * @param nuevoRetiro: DTO donde sacaremos los datos para rellenar la nueva transferencia
     * @return
     * @throws PersistenciaException 
     */
    @Override
    public Retiro generarRetiroSinCuenta(NuevoRetiroDTO nuevoRetiro) throws PersistenciaException {
        try {
            int idFolioRetiro = 0;
            String codigoSQL = """
                                           INSERT INTO RetiroSinCuentas(idOperacion, contrasenia, estado)
                                           VALUES (?, ?, ?);
                                           """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL,Statement.RETURN_GENERATED_KEYS);
            
            comando.setInt(1, nuevoRetiro.getIdOperacion());
            comando.setString(2, nuevoRetiro.getContrasenia());
            comando.setString(3, nuevoRetiro.getEstado().name());
            comando.execute();
            
            ResultSet idsFolioRetiro = comando.getGeneratedKeys();

            if (idsFolioRetiro.next() == true) {
                idFolioRetiro = idsFolioRetiro.getInt(1);
            }
            
            LOGGER.fine("Se registro correctamente el retiro sin cuenta.");
            
            return new Retiro(nuevoRetiro.getIdOperacion(),nuevoRetiro.getContrasenia(),idFolioRetiro,nuevoRetiro.getEstado());
            
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("Error al generar el retiro sin cuenta.",ex);
        }
    }

    /**
     * Devuelve un booleano para verificar que el cobro fue exitoso o no, utilizando
     * un update para poder actualizar la informacion hecha en el movimiento
     * pudiendo validar el tiempo
     * @param cobroRetiro
     * @return
     * @throws PersistenciaException 
     */
    @Override
    public boolean cobrarRetiroSinCuenta(Retiro cobroRetiro) throws PersistenciaException {
        try {
            String codigoSQL = """
                                           UPDATE retiroSinCuentas
                                           SET estado = ?
                                           WHERE idOperacion = ? AND estado = 'ACTIVO';
                                                  """;
            Connection conexion = ConexionBD.crearConexion();
            
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            
            comando.setString(1, cobroRetiro.getEstado().name());
            comando.setInt(2, cobroRetiro.getIdOperacion());
            
            int cambios = comando.executeUpdate();
            
            comando.close();
            conexion.close();
            return cambios > 0;
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fue posible cobrar el retiro.",ex);
        }
    }
    
    /**
     * Selecciona el retiro sin cuenta para poder verificar si fue exitoso, contando
     * con el folio y la contrasenia, parametros utilizados para validar
     * el estado del retiro y si realemtne se retiro el monto solicitado
     * @param cobroRetiro
     * @return
     * @throws PersistenciaException 
     */
    @Override
    public Retiro verificarRetiroSinCuenta(CobrarRetiroDTO cobroRetiro) throws PersistenciaException {
        try {
            Retiro retiro = null;
            String codigoSQL = """
                                        SELECT idOperacion, contrasenia, folio, estado
                                        FROM retiroSinCuentas
                                        WHERE folio = ? AND contrasenia = ?;
                                       """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            
            comando.setString(1, cobroRetiro.getFolioOperacion());
            comando.setString(2, cobroRetiro.getContrasenia());
            
            ResultSet rs = comando.executeQuery();
            
            while(rs.next()){
                String estadoString = rs.getString("estado");
                Estado estado = Estado.valueOf(estadoString);
                retiro = new Retiro(
                 rs.getInt("idOperacion"),
                 rs.getString("contrasenia"),
                 rs.getInt("folio"),
                 estado
                );
            }
            comando.close();
            conexion.close();
            
            return retiro;
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No se encontro ningun retiro sin cuenta con esas credenciales.",ex);
        }
    }
    
}
