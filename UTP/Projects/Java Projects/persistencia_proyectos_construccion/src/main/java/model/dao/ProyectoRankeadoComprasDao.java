package model.dao;

import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.JDBCUtilities;
import model.vo.ProyectoRankeadoCompras;

public class ProyectoRankeadoComprasDao {

    //Obtener los 10 proyectos rankeados según las compras
    public ArrayList<ProyectoRankeadoCompras> rankingProyectosComprasDescendente10() throws SQLException {

        ArrayList<ProyectoRankeadoCompras> resultado = new ArrayList<ProyectoRankeadoCompras>();
        Connection conexion = null;

        try {
            conexion = JDBCUtilities.getConnection();
            String consulta =   "SELECT  p.ID_Proyecto, "+
                                        "p.Clasificacion, "+
                                        "SUM(c.Cantidad * mc.Precio_Unidad) as Gasto_Compras, "+
                                        "p.Serial "+
                                "FROM Proyecto p "+
                                "JOIN Compra c ON "+
                                "p.ID_Proyecto = c.ID_Proyecto "+
                                "JOIN MaterialConstruccion mc ON "+
                                "c.ID_MaterialConstruccion = mc.ID_MaterialConstruccion "+
                                "GROUP BY p.ID_Proyecto "+
                                "ORDER BY Gasto_Compras DESC "+
                                "LIMIT 10 ";

            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                ProyectoRankeadoCompras proyectoRankeado = new ProyectoRankeadoCompras();
                proyectoRankeado.setIdProyecto(resultSet.getInt("ID_Proyecto"));
                proyectoRankeado.setClasificacion(resultSet.getString("Clasificacion"));
                proyectoRankeado.setGastoCompras(resultSet.getInt("Gasto_Compras"));
                proyectoRankeado.setSerial(resultSet.getString("Serial"));

                resultado.add(proyectoRankeado);
            }

            resultSet.close();
            statement.close();
            
        } catch (SQLException e) {
            System.out.println("Error consultando Proyectos Rankeados: " + e);

        }finally{
            if(conexion != null){
                conexion.close();
            }
        }

        return resultado;
    } 
    
}
