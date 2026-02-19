/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectobanco_262757_262746_262804;

import com.mycompany.proyectobanco.negocio.CuentasBO;
import com.mycompany.proyectobanco.negocio.ICuentasBO;
import com.mycompany.proyectobanco.negocio.IOperacionBO;
import com.mycompany.proyectobanco.negocio.ITransferenciaBO;
import com.mycompany.proyectobanco.negocio.OperacionBO;
import com.mycompany.proyectobanco.negocio.TransferenciaBO;
import com.mycompany.proyectobanco.persistencia.CuentasDAO;
import com.mycompany.proyectobanco.persistencia.ICuentasDAO;
import com.mycompany.proyectobanco.persistencia.IOperacionDAO;
import com.mycompany.proyectobanco.persistencia.ITransferenciaDAO;
import com.mycompany.proyectobanco.persistencia.OperacionDAO;
import com.mycompany.proyectobanco.persistencia.TransferenciaDAO;
import com.mycompany.proyectobanco.presentacion.SeleccionarCuentaOrigenTransferenciaFORM;

/**
 *
 * @author 
 */
public class ProyectoBanco_262757_262746_262804 {

    public static void main(String[] args) {
        IOperacionDAO operacionDAO = new OperacionDAO();
        IOperacionBO operacionBO = new OperacionBO(operacionDAO);
        
        ITransferenciaDAO nuevaTransferenciaDAO = new TransferenciaDAO();
        ITransferenciaBO nuevaTransferenciaBO = new TransferenciaBO(nuevaTransferenciaDAO,operacionBO);
        
        ICuentasDAO cuentasDAO = new CuentasDAO();
        ICuentasBO cuentasBO = new CuentasBO(cuentasDAO);
        
        SeleccionarCuentaOrigenTransferenciaFORM transferenciaFORM = new SeleccionarCuentaOrigenTransferenciaFORM(cuentasBO,nuevaTransferenciaBO,operacionBO);
        transferenciaFORM.setVisible(true);
    }
}
