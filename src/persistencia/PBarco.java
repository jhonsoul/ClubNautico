/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import control.Barco;
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
public class PBarco {
    
    private Connection con;

    public PBarco() {
        con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/club_nautico","root","");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PBarco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Se crea un barco en la base de datos
    public void crearBarco (Barco b) {
        String query = "INSERT INTO barco(numero_matricula, numero_amarre, identificacion_propietario, nombre, eliminar) VALUES (" + b.getNumeroMatricula() + ", \"" 
                + b.getNumeroAmarre() + "\", \"" 
                + b.getIdentificacionDueño() + "\", \"" 
                + b.getNombre() + "\", false)";
        try {
            Statement st = con.createStatement();
            st.executeUpdate(query);
            st.close();
            JOptionPane.showMessageDialog(null, "Barco creado", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(PBarco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Entrega un objeto de barco cargado con informacion de la base de datos para ser utilizado
    public Barco cargarDatos (String matricula) {
        Barco b = new Barco();
        String query = "SELECT * FROM barco WHERE numero_matricula = \"" + matricula + "\"";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                b.setNumeroMatricula(Integer.parseInt(matricula));
                b.setNumeroAmarre(rs.getString("numero_amarre"));
                b.setIdentificacionDueño(rs.getString("identificacion_propietario"));
                b.setNombre(rs.getString("nombre"));
                b.setEstadoEliminar(rs.getBoolean("eliminar"));
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PBarco.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }
    
    //Carga una lista con las matriculas del barco que existan en la base de datos
    public LinkedList listaBarcos () {
        LinkedList lista = new LinkedList();
        String query = "SELECT numero_matricula FROM barco WHERE eliminar = false";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {                
                lista.add(rs.getString("numero_matricula"));
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PBarco.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    //Carga la lista con los numero de amarre utilizados por los barcos
    public LinkedList listaAmarres () {
        LinkedList lista = new LinkedList();
        String query = "SELECT numero_amarre FROM barco WHERE eliminar = false";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {                
                lista.add(rs.getString("numero_amarre"));
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PBarco.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    //carga el modelo de tabla con todos los barcos que esten en la base de datos
    public DefaultTableModel cargarDatos () {
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"NÚMERO MATRICULA", "NÚMERO AMARRE", "IDENTIFICACIÓN PROPIETARIO", "NOMBRE"}, 0); //Creo un objeto del modelo de la tabla con los titulos cargados
        String[] info = new String[4];
        String query = "SELECT * FROM barco WHERE eliminar = false";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {                
                info[0] = rs.getString("numero_matricula");
                info[1] = rs.getString("numero_amarre");
                info[2] = rs.getString("identificacion_propietario");
                info[3] = rs.getString("nombre");
                Object[] fila = new Object[]{info[0], info[1], info[2], info[3]};
                modelo.addRow(fila); //Agrego una tupla a la fila al modelo
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PBarco.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modelo;
    }
    
    //Actualiza la información de la base de datos con un objeto de barco
    public void actualizarBarco (Barco b) {
        String query = "UPDATE barco SET nombre = \"" + b.getNombre() + "\", identificacion_propietario = \"" + b.getIdentificacionDueño() + "\", numero_amarre  = \"" + b.getNumeroAmarre() + "\" WHERE numero_matricula = \"" + b.getNumeroMatricula() + "\"";
        try {
            Statement st = con.createStatement();
            st.executeUpdate(query);
            st.close();
            JOptionPane.showMessageDialog(null, "Barco actualizado", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(PBarco.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    //Realiza un eliminado logico cambiando el estado de eliminar
    public void eliminar (String matricula) {
        String query = "UPDATE barco SET eliminar =  true, numero_amarre = null  WHERE numero_matricula = \"" + matricula + "\"";
        try {
            Statement st = con.createStatement();
            st.executeUpdate(query);
            st.close();
            JOptionPane.showMessageDialog(null, "Barco eliminado", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(PBarco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    //Se cierra la conexion con la base de datos al ser llamado
    public void cerrarConexion () {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PBarco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
