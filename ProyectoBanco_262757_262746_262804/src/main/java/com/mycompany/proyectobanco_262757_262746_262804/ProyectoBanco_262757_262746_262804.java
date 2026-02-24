/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectobanco_262757_262746_262804;

import com.mycompany.proyectobanco.dtos.ObjetosBoDTO;
import com.mycompany.proyectobanco.negocio.ClientesBO;
import com.mycompany.proyectobanco.negocio.CuentasBO;
import com.mycompany.proyectobanco.negocio.HistorialOperacionesBO;
import com.mycompany.proyectobanco.negocio.IClientesBO;
import com.mycompany.proyectobanco.negocio.ICuentasBO;
import com.mycompany.proyectobanco.negocio.IHistorialOperacionesBO;
import com.mycompany.proyectobanco.negocio.IOperacionBO;
import com.mycompany.proyectobanco.negocio.IRetiroBO;
import com.mycompany.proyectobanco.negocio.ITransferenciaBO;
import com.mycompany.proyectobanco.negocio.NegocioException;
import com.mycompany.proyectobanco.negocio.OperacionBO;
import com.mycompany.proyectobanco.negocio.RetiroBO;
import com.mycompany.proyectobanco.negocio.TransferenciaBO;
import com.mycompany.proyectobanco.persistencia.ClientesDAO;
import com.mycompany.proyectobanco.persistencia.CuentasDAO;
import com.mycompany.proyectobanco.persistencia.HistorialOperacioneDAO;
import com.mycompany.proyectobanco.persistencia.IClientesDAO;
import com.mycompany.proyectobanco.persistencia.ICuentasDAO;
import com.mycompany.proyectobanco.persistencia.IHistorialOperacionesDAO;
import com.mycompany.proyectobanco.persistencia.IOperacionDAO;
import com.mycompany.proyectobanco.persistencia.IRetiroDAO;
import com.mycompany.proyectobanco.persistencia.ITransferenciaDAO;
import com.mycompany.proyectobanco.persistencia.OperacionDAO;
import com.mycompany.proyectobanco.persistencia.RetiroDAO;
import com.mycompany.proyectobanco.persistencia.TransferenciaDAO;
import com.mycompany.proyectobanco.presentacion.CambiarEstadoCuentaFORM;
import com.mycompany.proyectobanco.presentacion.CobrarRetiroSinCuentaFORM;
import com.mycompany.proyectobanco.presentacion.ConsultarHIstorialOPeracionFORM;
import com.mycompany.proyectobanco.presentacion.InicioSesionFORM;

/**
 *
 * @author 
 */
public class ProyectoBanco_262757_262746_262804 {

    public static void main(String[] args) throws NegocioException {
        IOperacionDAO operacionDAO = new OperacionDAO();
        IOperacionBO operacionBO = new OperacionBO(operacionDAO);
        
        IRetiroDAO retiroDAO = new RetiroDAO();
        IRetiroBO retiroBO = new RetiroBO(operacionBO,retiroDAO);
        
        ICuentasDAO cuentasDAO = new CuentasDAO();
        ICuentasBO cuentasBO = new CuentasBO(cuentasDAO);
        
        IClientesDAO clientesDAO = new ClientesDAO();
        IClientesBO clientesBO = new ClientesBO(clientesDAO);
        
        ITransferenciaDAO transferenciaDAO = new TransferenciaDAO();
        ITransferenciaBO transferenciaBO = new TransferenciaBO(transferenciaDAO,operacionBO);
        
        IHistorialOperacionesDAO historialDAO = new HistorialOperacioneDAO();
        
        IHistorialOperacionesBO historialBO = new HistorialOperacionesBO(historialDAO);
        
        ObjetosBoDTO objetosBO = new ObjetosBoDTO(clientesBO,cuentasBO,operacionBO,retiroBO,transferenciaBO,historialBO);
        
        InicioSesionFORM iniciarSesion = new InicioSesionFORM(objetosBO);
        iniciarSesion.setVisible(true);
    }
        
               

        
    
}
