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
public class Actividad {
    
    private int identificacionSalida;
    private String identificacionNavegante;
    private int numeroMatricula;
    private String fecha;
    private String hora;
    private String destino;
    private boolean estadoEliminar;

    public Actividad() {
    }

    public Actividad(int identificacionSalida, String identificacionNavegante, int numeroMatricula, String fecha, String hora, String destino, boolean estadoEliminar) {
        this.identificacionSalida = identificacionSalida;
        this.identificacionNavegante = identificacionNavegante;
        this.numeroMatricula = numeroMatricula;
        this.fecha = fecha;
        this.hora = hora;
        this.destino = destino;
        this.estadoEliminar = estadoEliminar;
    }

    public Actividad(String identificacionNavegante, int numeroMatricula, String fecha, String hora, String destino, boolean estadoEliminar) {
        this.identificacionNavegante = identificacionNavegante;
        this.numeroMatricula = numeroMatricula;
        this.fecha = fecha;
        this.hora = hora;
        this.destino = destino;
        this.estadoEliminar = estadoEliminar;
    }
    
    public int getIdentificacionSalida() {
        return identificacionSalida;
    }

    public void setIdentificacionSalida(int identificacionSalida) {
        this.identificacionSalida = identificacionSalida;
    }

    public String getIdentificacionNavegante() {
        return identificacionNavegante;
    }

    public void setIdentificacionNavegante(String identificacionNavegante) {
        this.identificacionNavegante = identificacionNavegante;
    }

    public int getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(int numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public boolean isEstadoEliminar() {
        return estadoEliminar;
    }

    public void setEstadoEliminar(boolean estadoEliminar) {
        this.estadoEliminar = estadoEliminar;
    }
}
