/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import control.Capitan;
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
public class PCapitan {
    
    private Connection con;

    public PCapitan() {
        con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/club_nautico","root","");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PCapitan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Crea una nuevo capitan
    public void crearCapitan (Capitan cap) {
        String query = "INSERT INTO cliente(identificacion, nombre, apellido, telefono) VALUES (" + cap.getIdentificacion() + ", \"" 
                + cap.getNombre() + "\", \"" + cap.getApellido() + "\", \"" + cap.getTelefono()+ "\")";
        try {
            Statement st = con.createStatement();
            st.executeUpdate(query);
            query = "INSERT INTO capitan (identificacion, licencia, eliminar) VALUES (" + cap.getIdentificacion()+ ", \"" + cap.getLicencia()+ "\", " + cap.isEstado() + ")";
            st.executeUpdate(query);
            st.close();
            JOptionPane.showMessageDialog(null, "Capitan Creado", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(PCapitan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Carga la lista de identificacions de los capitanes
    public LinkedList identificaciones () {
        LinkedList lista = new LinkedList();
        String query = "SELECT identificacion FROM capitan WHERE eliminar = false";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {                
                lista.add(rs.getString("identificacion"));
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PCapitan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    //Carga un capitan desde la base de datos
    public Capitan cargarDatos (String identificacion) {
        Capitan cap = new Capitan();
        String query = "SELECT * FROM cliente WHERE identificacion = \"" + identificacion + "\"";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                cap.setNombre(rs.getString("nombre"));
                cap.setApellido(rs.getString("apellido"));
                cap.setTelefono(rs.getString("telefono"));
            }
            query = "SELECT * FROM capitan WHERE identificacion = \"" + identificacion + "\"";
            rs = st.executeQuery(query);
            if (rs.next()) {
                cap.setLicencia(rs.getString("licencia"));
                cap.setEstado(rs.getBoolean("eliminar"));
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PCapitan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cap;
    }
    
    //carga el modelo de tabla con todos los capitanes que esten en la base de datos
    public DefaultTableModel cargarDatos () {
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"IDENTIFICACIÓN", "NOMBRES", "APELLIDOS", "TELEFONO", "LICENCIA"}, 0); //Creo un objeto del modelo de la tabla con los titulos cargados
        String[] info = new String[5];
        String query = "SELECT * FROM capitan WHERE eliminar = false";
        try {
            Statement StUno = con.createStatement();
            Statement StDos = con.createStatement();
            ResultSet resultadoUno = StUno.executeQuery(query);
            ResultSet resultadoDos = null;
            while (resultadoUno.next()) {                
                info[0] = resultadoUno.getString("identificacion");
                info[4] = resultadoUno.getString("licencia");
                query = "SELECT * FROM cliente WHERE identificacion = \"" + info[0] + "\"";
                resultadoDos = StDos.executeQuery(query);
                if (resultadoDos.next()) {
                    info[1] = resultadoDos.getString("nombre");
                    info[2] = resultadoDos.getString("apellido");
                    info[3] = resultadoDos.getString("telefono");
                }
                Object[] fila = new Object[]{info[0], info[1], info[2], info[3], info[4]};
                modelo.addRow(fila); //Agrego una tupla a la fila al modelo
            }
            StUno.close();
            StDos.close();
            resultadoUno.close();
            if (resultadoDos != null) {
                resultadoDos.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PCapitan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modelo;
    }
    
    //Actualiza el cliente con un objeto de capitan
    public void actualizarCapitan (Capitan cap) {
        String query = "UPDATE cliente SET nombre = \"" + cap.getNombre() 
                + "\", apellido = \"" + cap.getApellido() 
                + "\", telefono = \"" + cap.getTelefono()
                + "\" WHERE identificacion = \"" + cap.getIdentificacion() + "\"";
        try {
            Statement st = con.createStatement();
            st.executeUpdate(query);
            query = "UPDATE capitan SET licencia = \"" + cap.getLicencia()
                    + "\" WHERE identificacion = \"" + cap.getIdentificacion() + "\"";
            st.executeUpdate(query);
            st.close();
            JOptionPane.showMessageDialog(null, "Capitan actualizado", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(PCapitan.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    //Realiza un eliminado logico cambiando el estado de eliminar
    public void eliminar (String identificacion) {
        String query = "UPDATE capitan SET eliminar =  true  WHERE identificacion = \"" + identificacion + "\"";
        try {
            Statement st = con.createStatement();
            st.executeUpdate(query);
            st.close();
            JOptionPane.showMessageDialog(null, "Capitan Eliminado", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(PCapitan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Se cierra la conexion con la base de datos al ser llamado
    public void cerrarConexion() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PCapitan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
