/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectobanco_262757_262746_262804;

import com.mycompany.proyectobanco.dtos.NuevaOperacionDTO;
import com.mycompany.proyectobanco.entidades.Operacion;
import com.mycompany.proyectobanco.persistencia.IOperacionDAO;
import com.mycompany.proyectobanco.persistencia.OperacionDAO;
import com.mycompany.proyectobanco.persistencia.PersistenciaException;
import java.time.LocalDateTime;

/**
 *
 * @author 
 */
public class ProyectoBanco_262757_262746_262804 {

    public static void main(String[] args) {
        LocalDateTime fechaHora = LocalDateTime.now();
        NuevaOperacionDTO nuevaOperacionDTO = new NuevaOperacionDTO(500, fechaHora ,"1234567890123456");
        IOperacionDAO operacion = new OperacionDAO();
        try{
            operacion.realizarOperacion(nuevaOperacionDTO);
        }catch(PersistenciaException ex){
            System.out.println("Error");
        }   
    }
}
