package model.dao;

import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.JDBCUtilities;
import model.vo.MaterialRankeadoCompras;

public class MaterialRankeadoComprasDao {

    //Obtener los 10 proyectos rankeados según las compras
    public ArrayList<MaterialRankeadoCompras> rankingMaterialesComprasDescendente() throws SQLException {

        ArrayList<MaterialRankeadoCompras> resultado = new ArrayList<MaterialRankeadoCompras>();
        Connection conexion = null;

        try {
            conexion = JDBCUtilities.getConnection();
            String consulta =   "SELECT  mc.Nombre_Material, "+
                                        "mc.Importado, "+
                                        "COUNT(*) as No_Compras "+
                                "FROM Compra c "+
                                "JOIN MaterialConstruccion mc ON "+
                                "c.ID_MaterialConstruccion = mc.ID_MaterialConstruccion "+
                                "WHERE mc.Importado NOT IN ('No') "+
                                "GROUP BY mc.Nombre_Material "+
                                "ORDER BY    No_Compras DESC, "+
                                            "Nombre_Material ";

            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                MaterialRankeadoCompras materialImportado = new MaterialRankeadoCompras();
                materialImportado.setNombreMaterial(resultSet.getString("Nombre_Material"));
                materialImportado.setImportado(resultSet.getString("Importado"));
                materialImportado.setNoCompras(resultSet.getInt("No_Compras"));

                resultado.add(materialImportado);
            }

            resultSet.close();
            statement.close();
            
        } catch (SQLException e) {
            System.out.println("Error consultando Ranking Descendiente Materiales Importados: " + e);

        }finally{
            if(conexion != null){
                conexion.close();
            }
        }

        return resultado;
    } 
    
}
