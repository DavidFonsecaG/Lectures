package model.dao;

import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.JDBCUtilities;
import model.vo.MaterialContruccion;

public class MaterialConstruccionDao {

    //Consultar todos (Read)
    public ArrayList<MaterialContruccion> consultarTodos() throws SQLException {

        ArrayList<MaterialContruccion> resultado = new ArrayList<MaterialContruccion>();
        Connection conexion = null;

        try {
            conexion = JDBCUtilities.getConnection();
            String consulta =   "SELECT  * FROM MaterialConstruccion";

            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                MaterialContruccion material = new MaterialContruccion();
                material.setIdMaterialConstruccion(resultSet.getInt(1));
                material.setNombreMaterial(resultSet.getString(2));
                material.setImportado(resultSet.getString(3));
                material.setPrecioUnidad(resultSet.getInt(4));
                
                resultado.add(material);
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

    //Insertar Material (Create)
    public MaterialContruccion adicionarMaterial(MaterialContruccion nuevoMaterial) throws SQLException {

        MaterialContruccion materialAdicionado = null;
        Connection conexion = null;

        try {
            conexion = JDBCUtilities.getConnection();
            String consulta = "INSERT INTO MaterialConstruccion (Nombre_Material, Importado, Precio_Unidad) VALUES (?, ?, ?)";

            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, nuevoMaterial.getNombreMaterial());
            statement.setString(2, nuevoMaterial.getImportado());
            statement.setInt(3, nuevoMaterial.getPrecioUnidad());

            statement.executeUpdate();

            statement.close();

            materialAdicionado = nuevoMaterial;
            
        } catch (SQLException e) {
            System.out.println("Error registrando material: " + e);

        }finally{
            if(conexion != null){
                conexion.close();
            }
        }

        return materialAdicionado;
    } 

    //Actualiza Material (Update)
    public MaterialContruccion actualizarMaterial(MaterialContruccion materialActualizar) throws SQLException {

        MaterialContruccion materialActualizado = null;
        Connection conexion = null;

        try {
            conexion = JDBCUtilities.getConnection();
            //Construir el DML de actualizacion
            String consulta = "UPDATE MaterialConstruccion SET Nombre_Material = ?, Importado = ?, Precio_Unidad = ? WHERE ID_MaterialConstruccion = ?";

            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, materialActualizar.getNombreMaterial());
            statement.setString(2, materialActualizar.getImportado());
            statement.setInt(3, materialActualizar.getPrecioUnidad());
            statement.setInt(4, materialActualizar.getIdMaterialConstruccion());

            //Realizar actualizacion
            statement.executeUpdate();
            
            //Cerrar interacciones con BD
            statement.close();

            materialActualizado = materialActualizar;
            
        } catch (SQLException e) {
            System.out.println("Error registrando material: " + e);

        }finally{
            if(conexion != null){
                conexion.close();
            }
        }

        return materialActualizado;
    } 

    //Eliminar Material (Delete)
    public MaterialContruccion eliminarMaterial(MaterialContruccion materialEliminar) throws SQLException {

        MaterialContruccion materialEliminado = null;
        Connection conexion = null;

        try {
            conexion = JDBCUtilities.getConnection();
            //Construir el DML de actualizacion
            String consulta = "DELETE FROM MaterialConstruccion WHERE ID_MaterialConstruccion = ?";

            PreparedStatement statement = conexion.prepareStatement(consulta);

            statement.setInt(1, materialEliminar.getIdMaterialConstruccion());

            //Realizar actualizacion
            statement.executeUpdate();
            
            //Cerrar interacciones con BD
            statement.close();

            materialEliminado = materialEliminar;
            
        } catch (SQLException e) {
            System.out.println("Error registrando material: " + e);

        }finally{
            if(conexion != null){
                conexion.close();
            }
        }

        return materialEliminado;
    } 
    
}
