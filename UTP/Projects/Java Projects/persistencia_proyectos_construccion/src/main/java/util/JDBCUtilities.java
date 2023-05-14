package util;

//Librerias para conexion 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Librerias para archivos
import java.io.File;

public class JDBCUtilities {
    
    private static final String UBICACION_BD = "resources/ProyectosConstruccion.db";//Ruta relativa

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:" + JDBCUtilities.UBICACION_BD;
        return DriverManager.getConnection(url);
    }

    public static boolean estaVacia(){
        File archivo = new File(JDBCUtilities.UBICACION_BD);
        return archivo.length() == 0;
    }

}
