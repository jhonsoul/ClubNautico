/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import control.Actividad;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jhon
 */
public class PActividad {
    Connection con;

    public PActividad() {
        con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/club_nautico","root","");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PActividad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Crea una nueva actividad
    public void crearActividad (Actividad a) {
        String query = "INSERT INTO actividad (identificacion_navegate, numero_matricula, fecha, hora, destino, eliminar) VALUES (\"" 
                + a.getIdentificacionNavegante()+ "\", " 
                + a.getNumeroMatricula() + ", \"" 
                + a.getFecha() + "\", \"" 
                + a.getHora() + "\", \"" 
                + a.getDestino()+ "\" ,false)";
        try {
            Statement st = con.createStatement();
            st.executeUpdate(query);
            st.close();
            JOptionPane.showMessageDialog(null, "Actividad creada", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(PActividad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //carga un objeto de actividad para ser enviado cuando se busque
    public Actividad buscarSalida (String actividad) {
        Actividad act = new Actividad();
        String query = "SELECT * FROM actividad WHERE id_salida = \"" + actividad + "\"";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                act.setIdentificacionSalida(rs.getInt("id_salida"));
                act.setIdentificacionNavegante(rs.getString("identificacion_navegate"));
                act.setNumeroMatricula(rs.getInt("numero_matricula"));
                act.setFecha(rs.getDate("fecha").toString());
                act.setHora(rs.getTime("hora").toString());
                act.setDestino(rs.getString("destino"));
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PActividad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return act;
    }
    
    //Carga una lista personas que puede ser capitan y navegar el barco
    public LinkedList Navegantes () {
        LinkedList lista = new LinkedList();
        String query = "SELECT identificacion FROM socio WHERE eliminar = false";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {                
                lista.add(rs.getString("identificacion"));
            }
            query = "SELECT identificacion FROM capitan WHERE eliminar = false";
            rs = st.executeQuery(query);
            while (rs.next()) {                
                lista.add(rs.getString("identificacion"));
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PSocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    //Carga la lista con actividades creadas
    public LinkedList Actividades () {
        LinkedList lista = new LinkedList();
        String query = "SELECT id_salida FROM actividad WHERE eliminar = false";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {                
                lista.add(rs.getString("id_salida"));
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PSocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    //Arma el modelo para cargar en la tabla
    public DefaultTableModel cargarDatos () {
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"ID SALIDA", "IDENTIFICACIÓN CAPITÁN", "NÚMERO MATRICULA", "FECHA", "HORA", "DESTINO"}, 0); //Creo un objeto del modelo de la tabla con los titulos cargados
        String[] info = new String[6];
        String query = "SELECT * FROM actividad WHERE eliminar = false";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {                
                info[0] = rs.getString("id_salida");
                info[1] = rs.getString("identificacion_navegate");
                info[2] = rs.getString("numero_matricula");
                info[3] = rs.getString("fecha");
                info[4] = rs.getString("hora");
                info[5] = rs.getString("destino");
                Object[] fila = new Object[]{info[0], info[1], info[2], info[3], info[4], info[5]};
                modelo.addRow(fila);
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PActividad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modelo;
    }
    
    //Modifica la actividad selecionada 
    public void actualizarActividad (Actividad act) {
        String query = "UPDATE actividad SET identificacion_navegate = \"" + act.getIdentificacionNavegante() 
                + "\", numero_matricula  = \"" + act.getNumeroMatricula() 
                + "\", fecha  = \"" + act.getFecha() 
                + "\", hora  = \"" + act.getHora() 
                + "\", destino  = \"" + act.getDestino() 
                + "\" WHERE id_salida = \"" + act.getIdentificacionSalida() + "\"";
        try {
            Statement st = con.createStatement();
            st.executeUpdate(query);
            st.close();
            JOptionPane.showMessageDialog(null, "Actividad actualizada", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(PActividad.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    //Realiza un eliminado logico
    public void eliminar (String idSalida) {
        String query = "UPDATE actividad SET eliminar =  true  WHERE id_salida = \"" + idSalida + "\"";
        try {
            Statement st = con.createStatement();
            st.executeUpdate(query);
            st.close();
            JOptionPane.showMessageDialog(null, "Actividad eliminada", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(PActividad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Para cerrar la conexion con la base de datos
    public void cerrarConexion () {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PActividad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
