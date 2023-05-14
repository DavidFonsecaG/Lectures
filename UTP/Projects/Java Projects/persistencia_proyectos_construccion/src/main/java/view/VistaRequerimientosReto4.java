package view;

import controller.ControladorRequerimientoReto4;
import model.vo.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class VistaRequerimientosReto4 {

    public static final ControladorRequerimientoReto4 controlador = new ControladorRequerimientoReto4();

    public static void requerimiento1(){

        System.out.println("-----10 Proyectos Mayor Gasto-------");

        try {
            ArrayList<ProyectoRankeadoCompras> rankingProyectosCompras = controlador.consultarProyectosCompras10();
            //Encabezado del resultado
            System.out.println("ID_Proyecto Clasificación Gasto_Compras Serial");

            for(ProyectoRankeadoCompras proyecto : rankingProyectosCompras){

                System.out.printf("%d %s %d %s %n",
                proyecto.getIdProyecto(),
                proyecto.getClasificacion(),
                proyecto.getGastoCompras(),
                proyecto.getSerial()
                );

            }

        } catch (SQLException e) {
            System.err.println("Ha ocurrido un error!"+e.getMessage());
        }
    }

    public static void requerimiento3(){

        System.out.println("-----Ranking Descendente Bancos (Área Proyectos)-------");       

        try{

            ArrayList<BancoRankeadoAreaPromedio> rankingBancosArea = controlador.consultarBancosRankeadosAreaPromedio();
            //Encabezado del resultado
            System.out.println("Banco_Vinculado Area_Promedio");

            for (BancoRankeadoAreaPromedio banco : rankingBancosArea) {
                
                System.out.printf("%s %f %n",
                banco.getBancoVinculado(),
                banco.getAreaPromedio()
                );

            }

        }catch(SQLException e){
            System.err.println("Ha ocurrido un error!"+e.getMessage());
        }

    }

    public static void requerimiento5(){

        System.out.println("-----Ranking Descendente Materiales Importados (Compras)-------");       

        try{

            ArrayList<MaterialRankeadoCompras> rankingMaterialesImportados = controlador.consultarMaterialesRankeadosCompras();
            //Encabezado del resultado
            System.out.println("Nombre_Material Importado No_Compras");

            for (MaterialRankeadoCompras material : rankingMaterialesImportados) {
                
                System.out.printf("%s %s %d %n",
                material.getNombreMaterial(),
                material.getImportado(),
                material.getNoCompras()
                );

            }

        }catch(SQLException e){
            System.err.println("Ha ocurrido un error!"+e.getMessage());
        }

    }
    
}
