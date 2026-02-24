
package com.mycompany.proyectobanco.dtos;

import com.mycompany.proyectobanco.negocio.IClientesBO;
import com.mycompany.proyectobanco.negocio.ICuentasBO;
import com.mycompany.proyectobanco.negocio.IHistorialOperacionesBO;
import com.mycompany.proyectobanco.negocio.IOperacionBO;
import com.mycompany.proyectobanco.negocio.IRetiroBO;
import com.mycompany.proyectobanco.negocio.ITransferenciaBO;
import com.mycompany.proyectobanco.persistencia.IHistorialOperacionesDAO;

/**
 *
 * @author Julian
 */
public class ObjetosBoDTO {
    private IClientesBO clientesBO;
    private ICuentasBO cuentasBO;
    private IOperacionBO operacionBO;
    private IRetiroBO retiroBO;
    private ITransferenciaBO transferenciaBO;
    private IHistorialOperacionesBO historialBO;

    public ObjetosBoDTO() {
    }
    
    
    public ObjetosBoDTO(IClientesBO clientesBO, ICuentasBO cuentasBO, IOperacionBO operacionBO, IRetiroBO retiroBO, ITransferenciaBO transferenciaBO, IHistorialOperacionesBO historialBO) {
        this.clientesBO = clientesBO;
        this.cuentasBO = cuentasBO;
        this.operacionBO = operacionBO;
        this.retiroBO = retiroBO;
        this.transferenciaBO = transferenciaBO;
        this.historialBO = historialBO;
    }

    public IHistorialOperacionesBO getHistorialBO() {
        return historialBO;
    }

    public IClientesBO getClientesBO() {
        return clientesBO;
    }

    public ICuentasBO getCuentasBO() {
        return cuentasBO;
    }

    public IOperacionBO getOperacionBO() {
        return operacionBO;
    }

    public IRetiroBO getRetiroBO() {
        return retiroBO;
    }

    public ITransferenciaBO getTransferenciaBO() {
        return transferenciaBO;
    }
    
    
}
