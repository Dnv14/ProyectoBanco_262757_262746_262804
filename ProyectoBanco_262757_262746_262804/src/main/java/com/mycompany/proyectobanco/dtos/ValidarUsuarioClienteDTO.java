
package com.mycompany.proyectobanco.dtos;

/**
 *
 * @author Julian
 */
public class ValidarUsuarioClienteDTO {
    private String usuario;
    private String contrasenia;

    public ValidarUsuarioClienteDTO() {
    }

    public ValidarUsuarioClienteDTO(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }
    
    
}
