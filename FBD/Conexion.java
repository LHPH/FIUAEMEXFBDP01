package FBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import oracle.jdbc.OracleDriver;

public class Conexion {
    private final String url = "jdbc:oracle:thin:@localhost:1521:XE";   //URL de la base de datos
    private String user;  // nombre del usuario de la base de datos
    private String password; // contraseña de la base de datos
    private static Connection connection;
    private ResultSet consulta;
    private Statement sentencia;
    
    
    public Conexion(String user, String password) {
        super();
        this.user = user;
        this.password = password;
    }

    public boolean initConexion() {
           boolean acc=false;
        if (connection == null) {
            try {
                DriverManager.registerDriver(new OracleDriver());
                connection = DriverManager.getConnection(url, user, password);
                acc=true;
            } catch (SQLException sqle) {
                JOptionPane.showMessageDialog(null, "Usuario no Encontrado", "Error!!!",
                                              JOptionPane.WARNING_MESSAGE);
                acc=false;
            }
        }
        return acc;
    }


    public void Ingresarsentencia(String sql) {
        try {
            sentencia = connection.createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cleanUp() {
        try {
            if (sentencia != null)
                sentencia.close();
            if (consulta != null)
                consulta.close();
            if (connection != null){
                connection.close();
                connection=null;
            }
                
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getConsulta(String sql) {
        try {
            sentencia = connection.createStatement();
            consulta = sentencia.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consulta;
    }
}


