/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author Jhon
 */
public class Socio extends Cliente{
    private String direccion;
    
    public Socio () {
    }
    
    public Socio(int identificacion, String nombre, String apellido, String telefono, String direccion, boolean estado) {
        super(identificacion, nombre, apellido, telefono, estado);
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
