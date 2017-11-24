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
public class Barco {
    private int numeroMatricula;
    private String identificacionDueño;
    private String numeroAmarre;
    private String nombre;
    private boolean estadoEliminar;

    public Barco() {
    }

    public Barco(int numeroMatricula, String numeroAmarre, String identificacionDueño, String nombre, boolean estadoEliminar) {
        this.numeroMatricula = numeroMatricula;
        this.identificacionDueño = identificacionDueño;
        this.numeroAmarre = numeroAmarre;
        this.nombre = nombre;
        this.estadoEliminar = estadoEliminar;
    }

    public int getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(int numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getIdentificacionDueño() {
        return identificacionDueño;
    }

    public void setIdentificacionDueño(String identificacionDueño) {
        this.identificacionDueño = identificacionDueño;
    }

    public String getNumeroAmarre() {
        return numeroAmarre;
    }

    public void setNumeroAmarre(String numeroAmarre) {
        this.numeroAmarre = numeroAmarre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstadoEliminar() {
        return estadoEliminar;
    }

    public void setEstadoEliminar(boolean estadoEliminar) {
        this.estadoEliminar = estadoEliminar;
    }
}
