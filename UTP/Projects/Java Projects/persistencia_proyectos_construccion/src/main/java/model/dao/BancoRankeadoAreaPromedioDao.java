package model.dao;

import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.JDBCUtilities;
import model.vo.BancoRankeadoAreaPromedio;

public class BancoRankeadoAreaPromedioDao {

    //Obtener los 10 proyectos rankeados según las compras
    public ArrayList<BancoRankeadoAreaPromedio> rankingBancosAreaPromedioDescendente() throws SQLException {

        ArrayList<BancoRankeadoAreaPromedio> resultado = new ArrayList<BancoRankeadoAreaPromedio>();
        Connection conexion = null;

        try {
            conexion = JDBCUtilities.getConnection();
            String consulta =   "SELECT  p.Banco_Vinculado, "+
                                        "AVG(t.Area_Max) as Area_Promedio "+
                                "FROM Proyecto p "+
                                "JOIN Tipo t ON "+
                                "p.ID_Tipo = t.ID_Tipo "+
                                "GROUP BY p.Banco_Vinculado "+
                                "ORDER BY Area_Promedio DESC ";

            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                BancoRankeadoAreaPromedio banco = new BancoRankeadoAreaPromedio();
                banco.setBancoVinculado(resultSet.getString("Banco_Vinculado"));
                banco.setAreaPromedio(resultSet.getDouble("Area_Promedio"));

                resultado.add(banco);
            }

            resultSet.close();
            statement.close();
            
        } catch (SQLException e) {
            System.out.println("Error consultando Ranking Descendente Bancos: " + e);

        }finally{
            if(conexion != null){
                conexion.close();
            }
        }

        return resultado;
    } 
    
}
