import java.sql.Connection;
import java.sql.SQLException;

import util.JDBCUtilities;

import controller.ControladorRequerimientoReto4;

public final class App {

    public static void main(String[] args) {

        ControladorRequerimientoReto4 controlador = new ControladorRequerimientoReto4();
        controlador.iniciarAplicacion();
        
        try(Connection conexion = JDBCUtilities.getConnection();){
            
            if(JDBCUtilities.estaVacia()){
                System.out.println("Se encuentra vacia!");
            }else{
                System.out.println("No esta vacia BD encontrada");
            }
            
        } catch (SQLException e) {
            System.out.println("Error iniciando conexion: " + e);
        }        
        
    }
}
