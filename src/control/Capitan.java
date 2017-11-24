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
public class Capitan extends Cliente{
    private String licencia;

    public Capitan () {
    }

    public Capitan(int identificacion, String nombre, String apellido, String telefono, String licencia, boolean estado) {
        super(identificacion, nombre, apellido, telefono, estado);
        this.licencia = licencia;
    }
    
    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }
}
