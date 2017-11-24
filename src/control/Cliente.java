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
public class Cliente {
    private int identificacion;
    private String nombre;
    private String apellido;
    private String telefono;
    private boolean estadoEliminar;

    public Cliente() {
    }

    public Cliente(int identificacion, String nombre, String apellido, String telefono, boolean estado) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.estadoEliminar = estado;
    }
    
    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isEstado() {
        return estadoEliminar;
    }

    public void setEstado(boolean estado) {
        this.estadoEliminar = estado;
    }
}
