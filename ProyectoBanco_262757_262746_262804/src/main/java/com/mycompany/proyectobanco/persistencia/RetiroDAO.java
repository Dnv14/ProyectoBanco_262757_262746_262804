
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
    
    

    @Override
    public Retiro generarRetiroSinCuenta(NuevoRetiroDTO nuevoRetiro) throws PersistenciaException {
        try {
            int idFolioRetiro = 0;
            String codigoSQL = """
                                           INSERT INTO RetiroSinCuenta(idOperacion, contrasenia, estado)
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

    @Override
    public Retiro cobrarRetiroSinCuenta(CobrarRetiroDTO cobroRetiro) throws PersistenciaException {
        try {
            Retiro retiro = null;
            String codigoSQL = """
                                           SELECT idOperacion, contrasenia, folio, estado
                                           FROM retiroSinCuenta
                                           WHERE folio = ? AND contrasenia = ?;
                                                  """;
            
            String codigoActualizarEstadoSQL = """
                                           UPDATE retiroSinCuenta
                                           SET estado = ?
                                           WHERE folio = ? AND contrasenia = ? ;
                                                  """;
            Connection conexion = ConexionBD.crearConexion();
            PreparedStatement comando = conexion.prepareStatement(codigoSQL);
            PreparedStatement comando2 = conexion.prepareStatement(codigoActualizarEstadoSQL);
            
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
            
            comando2.setString(1, cobroRetiro.getEstado().name());
            comando2.setString(2, cobroRetiro.getFolioOperacion());
            comando2.setString(3, cobroRetiro.getContrasenia());
            
            comando2.executeUpdate();
            
            comando.close();
            comando2.close();
            
            conexion.close();
            
            return retiro;
        } catch (SQLException ex) {
            LOGGER.severe(ex.getMessage());
            throw new PersistenciaException("No fue posible cobrar el retiro.",ex);
        }
    }
    
}
