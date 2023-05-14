package model.dao;

import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.JDBCUtilities;
import model.vo.Compra;

public class CompraDao {

    //Consultar todos (Read)
    public ArrayList<Compra> consultarTodos() throws SQLException {

        ArrayList<Compra> resultado = new ArrayList<Compra>();
        Connection conexion = null;

        try {
            conexion = JDBCUtilities.getConnection();
            String consulta =   "SELECT * FROM Compra";

            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Compra compra = new Compra();
                compra.setIdCompra(resultSet.getInt(1));
                compra.setCantidad(resultSet.getInt(2));
                compra.setProveedor(resultSet.getString(3));
                compra.setPagado(resultSet.getString(4));
                compra.setFecha(resultSet.getString(5));
                compra.setIdProyecto(resultSet.getInt(6));
                compra.setIdMaterialConstruccion(resultSet.getInt(7));
                
                resultado.add(compra);
            }

            resultSet.close();
            statement.close();
            
        } catch (SQLException e) {
            System.out.println("Error consultando Compras: " + e);

        }finally{
            if(conexion != null){
                conexion.close();
            }
        }

        return resultado;
    } 

    //Insertar Material (Create)
    public Compra adicionarCompra(Compra nuevaCompra) throws SQLException {

        Compra compraAdicionada = null;
        Connection conexion = null;

        try {
            conexion = JDBCUtilities.getConnection();
            String consulta = "INSERT INTO Compra (Cantidad, Proveedor, Pagado, Fecha, ID_Proyecto, ID_MateriaalConstruccion) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, nuevaCompra.getCantidad());
            statement.setString(2, nuevaCompra.getProveedor());
            statement.setString(3, nuevaCompra.getPagado());
            statement.setString(4, nuevaCompra.getFecha());
            statement.setInt(5, nuevaCompra.getIdProyecto());
            statement.setInt(6, nuevaCompra.getIdMaterialConstruccion());

            statement.executeUpdate();

            statement.close();

            compraAdicionada = nuevaCompra;
            
        } catch (SQLException e) {
            System.out.println("Error registrando material: " + e);

        }finally{
            if(conexion != null){
                conexion.close();
            }
        }

        return compraAdicionada;
    } 

    //Actualiza Material (Update)
    public Compra actualizarMaterial(Compra compraActualizar) throws SQLException {

        Compra compraActualizada = null;
        Connection conexion = null;

        try {
            conexion = JDBCUtilities.getConnection();
            //Construir el DML de actualizacion
            String consulta = "UPDATE Compra SET Cantidad=?, Proveedor=?, Pagado=?, Fecha=?, ID_Proyecto=?, ID_MateriaalConstruccion=? WHERE ID_Compra = ?";

            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, compraActualizar.getCantidad());
            statement.setString(2, compraActualizar.getProveedor());
            statement.setString(3, compraActualizar.getPagado());
            statement.setString(4, compraActualizar.getFecha());
            statement.setInt(5, compraActualizar.getIdProyecto());
            statement.setInt(6, compraActualizar.getIdMaterialConstruccion());
            statement.setInt(7, compraActualizar.getIdCompra());

            //Realizar actualizacion
            statement.executeUpdate();
            
            //Cerrar interacciones con BD
            statement.close();

            compraActualizada = compraActualizar;
            
        } catch (SQLException e) {
            System.out.println("Error actualizando material: " + e);

        }finally{
            if(conexion != null){
                conexion.close();
            }
        }

        return compraActualizada;
    } 

    //Eliminar Material (Delete)
    public Compra eliminarCompra(Compra compraEliminar) throws SQLException {

        Compra compraEliminada = null;
        Connection conexion = null;

        try {
            conexion = JDBCUtilities.getConnection();
            //Construir el DML de actualizacion
            String consulta = "DELETE FROM Compra WHERE ID_Compra = ?";

            PreparedStatement statement = conexion.prepareStatement(consulta);

            statement.setInt(1, compraEliminar.getIdCompra());

            //Realizar actualizacion
            statement.executeUpdate();
            
            //Cerrar interacciones con BD
            statement.close();

            compraEliminada = compraEliminar;
            
        } catch (SQLException e) {
            System.out.println("Error eliminando compra: " + e);

        }finally{
            if(conexion != null){
                conexion.close();
            }
        }

        return compraEliminada;
    } 
    
}
