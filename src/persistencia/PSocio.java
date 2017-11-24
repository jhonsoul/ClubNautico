/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import control.Socio;
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
public class PSocio {
    
    private Connection con;

    public PSocio() {
        con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/club_nautico","root","");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PSocio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //Crea un nuevo socio desde un objeto socio a la base de datos
    public void crearSocio (Socio s) {
        String query = "INSERT INTO cliente(identificacion, nombre, apellido, telefono) VALUES (" + s.getIdentificacion() + ", \"" 
                + s.getNombre() + "\", \"" + s.getApellido() + "\", \"" + s.getTelefono()+ "\")";
        try {
            Statement st = con.createStatement();
            st.executeUpdate(query);
            query = "INSERT INTO socio (identificacion, direccion, eliminar) VALUES (" + s.getIdentificacion()+ ", \"" + s.getDireccion()+ "\", " + s.isEstado() + ")";
            st.executeUpdate(query);
            st.close();
            JOptionPane.showMessageDialog(null, "Socio Creado", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(PSocio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Carga una lista de identificaciones de los socios
    public LinkedList identificaciones () {
        LinkedList lista = new LinkedList();
        String query = "SELECT identificacion FROM socio WHERE eliminar = false";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
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
    
    //Carga un socio de la base de datos
    public Socio cargarDatos (String identificacion) {
        Socio s = new Socio();
        String query = "SELECT * FROM cliente WHERE identificacion = \"" + identificacion + "\"";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                s.setNombre(rs.getString("nombre"));
                s.setApellido(rs.getString("apellido"));
                s.setTelefono(rs.getString("telefono"));
            }
            query = "SELECT * FROM socio WHERE identificacion = \"" + identificacion + "\"";
            rs = st.executeQuery(query);
            if (rs.next()) {
                s.setDireccion(rs.getString("direccion"));
                s.setEstado(rs.getBoolean("eliminar"));
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PSocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    //Carga un modelo de tabla con todos los socios que esten en la base de datos
    public DefaultTableModel cargarDatos () {
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"IDENTIFICACIÓN", "NOMBRES", "APELLIDOS", "TELEFONO", "DIRECCIÓN"}, 0); //Creo un objeto del modelo de la tabla con los titulos cargados
        String[] info = new String[5];
        String query = "SELECT * FROM socio WHERE eliminar = false";
        try {
            Statement StUno = con.createStatement();
            Statement StDos = con.createStatement();
            ResultSet resultadoUno = StUno.executeQuery(query);
            ResultSet resultadoDos = null;
            while (resultadoUno.next()) {                
                info[0] = resultadoUno.getString("identificacion");
                info[4] = resultadoUno.getString("direccion");
                query = "SELECT * FROM cliente WHERE identificacion = \"" + info[0] + "\"";
                resultadoDos = StDos.executeQuery(query);
                if (resultadoDos.next()) {
                    info[1] = resultadoDos.getString("nombre");
                    info[2] = resultadoDos.getString("apellido");
                    info[3] = resultadoDos.getString("telefono");
                }
                Object[] fila = new Object[]{info[0], info[1], info[2], info[3], info[4]};
                modelo.addRow(fila);
            }
            StUno.close();
            StDos.close();
            resultadoUno.close();
            if (resultadoDos != null) {
                resultadoDos.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PSocio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modelo;
    }
    
    //Actualiza un socio de la base de datos
    public void actualizarSocio (Socio s) {
        String query = "UPDATE cliente SET nombre = \"" + s.getNombre() + "\", apellido = \"" + s.getApellido() + "\", telefono = \"" + s.getTelefono()+ "\" WHERE identificacion = \"" + s.getIdentificacion() + "\"";
        try {
            Statement st = con.createStatement();
            st.executeUpdate(query);
            query = "UPDATE socio SET direccion = \"" + s.getDireccion()+ "\" WHERE identificacion = \"" + s.getIdentificacion() + "\"";
            st.executeUpdate(query);
            st.close();
            JOptionPane.showMessageDialog(null, "Socio actualizado", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(PSocio.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    //elimina de forma logica un socio
    public void eliminar (String identificacion) {
        String query = "UPDATE socio SET eliminar =  true  WHERE identificacion = \"" + identificacion + "\"";
        try {
            Statement st = con.createStatement();
            st.executeUpdate(query);
            st.close();
            JOptionPane.showMessageDialog(null, "Socio Eliminado", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(PSocio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Cierra la conexion de socio    
    public void cerrarConexion() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PCapitan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
