package controller;

import java.sql.SQLException;
import java.util.ArrayList;
//Requerimiento 1
import model.vo.ProyectoRankeadoCompras;
import model.dao.ProyectoRankeadoComprasDao;
//Requerimiento 3
import model.vo.BancoRankeadoAreaPromedio;
import model.vo.Compra;
import model.vo.MaterialContruccion;
import model.dao.BancoRankeadoAreaPromedioDao;
//Requerimiento 5
import model.vo.MaterialRankeadoCompras;
import model.dao.MaterialRankeadoComprasDao;
//CRUD
import model.vo.MaterialContruccion;
import model.dao.MaterialConstruccionDao;
import model.dao.CompraDao;
//view
import view.MenuPrincipalGUI;
import view.Requerimiento1_GUI;
import view.Requerimiento2_GUI;
import view.Requerimiento3_GUI;
import view.CRUD_Materiales_GUI;
import view.CRUD_Compras_GUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;


public class ControladorRequerimientoReto4 implements ActionListener{

    //Instaciamos los Dao
    private final ProyectoRankeadoComprasDao proyectoRankeadoComprasDao;
    private final BancoRankeadoAreaPromedioDao bancoRankeadoAreaPromedioDao;
    private final MaterialRankeadoComprasDao materialRankeadoComprasDao;
    private final MaterialConstruccionDao materialConstruccionDao;
    private final CompraDao compraDao;
    //Alojar objetos de la vista
    private final MenuPrincipalGUI menuPrincipalGUI;
    private Requerimiento1_GUI requerimiento1_GUI;
    private Requerimiento2_GUI requerimiento2_GUI;
    private Requerimiento3_GUI requerimiento3_GUI;
    private CRUD_Materiales_GUI crud_Materiales_GUI;
    private CRUD_Compras_GUI crud_Compras_GUI;

    public ControladorRequerimientoReto4() {
        this.proyectoRankeadoComprasDao = new ProyectoRankeadoComprasDao();
        this.bancoRankeadoAreaPromedioDao = new BancoRankeadoAreaPromedioDao();
        this.materialRankeadoComprasDao = new MaterialRankeadoComprasDao();
        this.materialConstruccionDao = new MaterialConstruccionDao();
        this.compraDao = new CompraDao();
        //Inicializar la vista menu
        this.menuPrincipalGUI = new MenuPrincipalGUI();
    }
    
    //Requerimiento 1 -> inicia app por consola
    public ArrayList<ProyectoRankeadoCompras> consultarProyectosCompras10() throws SQLException {
        return this.proyectoRankeadoComprasDao.rankingProyectosComprasDescendente10();
    }
    
    //Requerimiento 3 -> inicia app por consola
    public ArrayList<BancoRankeadoAreaPromedio> consultarBancosRankeadosAreaPromedio() throws SQLException {
        return this.bancoRankeadoAreaPromedioDao.rankingBancosAreaPromedioDescendente();
    }

    //Requerimiento 5 -> inicia app por consola
    public ArrayList<MaterialRankeadoCompras> consultarMaterialesRankeadosCompras() throws SQLException {
        return this.materialRankeadoComprasDao.rankingMaterialesComprasDescendente();
    }
    
    //Controlador iniciando app -> En caso de que la app inicie en modo grafico
    public void iniciarAplicacion(){
        this.menuPrincipalGUI.iniciarGUI(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
        //Obtener el evento asociado al boton
        String actionCommand = ((JButton)e.getSource()).getActionCommand();

        switch(actionCommand){

            case("Ranking Proyectos"):
                try {
                    ArrayList<ProyectoRankeadoCompras> proyectos = new ArrayList<ProyectoRankeadoCompras>();
                    proyectos = this.proyectoRankeadoComprasDao.rankingProyectosComprasDescendente10();
                    this.requerimiento1_GUI = new Requerimiento1_GUI(proyectos);
                } catch (SQLException eProyectosCompras) {
                    System.err.println("Error cargando rq1 en la ventana!!: " + eProyectosCompras);
                }
            break;

            case("Ranking Bancos"):
                try {
                    ArrayList<BancoRankeadoAreaPromedio> bancos = new ArrayList<BancoRankeadoAreaPromedio>();
                    bancos = this.bancoRankeadoAreaPromedioDao.rankingBancosAreaPromedioDescendente();
                    this.requerimiento2_GUI = new Requerimiento2_GUI(bancos);
                } catch (SQLException eBancos) {
                    System.err.println("Error cargando rq1 en la ventana!!: " + eBancos);
                }
            break;

            case("Ranking Materiales Importados"):
                try {
                    ArrayList<MaterialRankeadoCompras> materiales = new ArrayList<MaterialRankeadoCompras>();
                    materiales = this.materialRankeadoComprasDao.rankingMaterialesComprasDescendente();
                    this.requerimiento3_GUI = new Requerimiento3_GUI(materiales);
                } catch (SQLException eMateriales) {
                    System.err.println("Error cargando rq1 en la ventana!!: " + eMateriales);
                }
            break;

            case("Gestion Materiales"):
                try {
                    ArrayList<MaterialContruccion> materiales = new ArrayList<MaterialContruccion>();
                    materiales = this.materialConstruccionDao.consultarTodos();
                    this.crud_Materiales_GUI = new CRUD_Materiales_GUI(materiales, this);
                } catch (SQLException eMateriales) {
                    System.err.println("Error cargando materiales en la ventana!!: " + eMateriales);
                }
            break;

            case("Gestion Compras"):
                try {
                    ArrayList<Compra> compras = new ArrayList<Compra>();
                    compras = this.compraDao.consultarTodos();
                    this.crud_Compras_GUI = new CRUD_Compras_GUI(compras, this);
                } catch (SQLException eCompras) {
                    System.err.println("Error cargando materiales en la ventana!!: " + eCompras);
                }
            break;

            case("Adicionar"):
                MaterialContruccion nuevoMaterial = new MaterialContruccion();
                nuevoMaterial.setNombreMaterial(crud_Materiales_GUI.getTxtNombreMaterial().getText());
                nuevoMaterial.setImportado(crud_Materiales_GUI.getTxtImportado().getText());
                int precioUnidad = 0;
                try {
                    precioUnidad = Integer.valueOf(crud_Materiales_GUI.getTxtPrecioUnidad().getText());                    
                } catch (NumberFormatException eFormatoPrecioUnidad) {

                    try {
                        precioUnidad = Integer.valueOf(JOptionPane.showInputDialog(
                            crud_Materiales_GUI, 
                            "Ingrese un precio unitario valido (numerico): ",
                            "Error de Formulario",
                            JOptionPane.ERROR_MESSAGE)
                        );
                    } catch (NumberFormatException errorSegundoIntento) {
                        JOptionPane.showMessageDialog(
                            crud_Materiales_GUI, 
                            "Precio unitario invalido, valor por defecto establecido!!", 
                            "Error Formato", 
                            JOptionPane.ERROR_MESSAGE
                        );
                    } 
                }
                nuevoMaterial.setPrecioUnidad(precioUnidad);

                //Solicitar al modelo la adicion del nuevo material
                MaterialContruccion materialRegistrado = null;
                try {
                    materialRegistrado = materialConstruccionDao.adicionarMaterial(nuevoMaterial);
                } catch (SQLException eNuevoMaterial) {
                    JOptionPane.showMessageDialog(
                        crud_Materiales_GUI, 
                        "Error insertando nuevo material!!", 
                        "Error BD", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }
                //Reportar exito y proceder a actualizar por el registro exitoso
                if (materialRegistrado != null){
                    JOptionPane.showMessageDialog(
                            crud_Materiales_GUI, 
                            "Registros exitoso!!", 
                            "Transaccion completa BD", 
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    //Limpiar los campos del formulario
                    crud_Materiales_GUI.getTxtNombreMaterial().setText("");
                    crud_Materiales_GUI.getTxtImportado().setText("");
                    crud_Materiales_GUI.getTxtPrecioUnidad().setText("");

                    //Actulizar vista con nuevo material
                    //Limpiar todos los materiales
                    while(crud_Materiales_GUI.getModeloTablaMateriales().getRowCount() > 0){
                        crud_Materiales_GUI.getModeloTablaMateriales().removeRow(0);
                    }
                    //Cargar los materiales de la base de datos (version actualizada)
                    try {
                        for(MaterialContruccion filaMaterial : materialConstruccionDao.consultarTodos()){

                            crud_Materiales_GUI.getModeloTablaMateriales().addRow(
                                new Object[]{
                                        String.valueOf(filaMaterial.getIdMaterialConstruccion()),
                                        filaMaterial.getNombreMaterial(),
                                        filaMaterial.getImportado(),
                                        String.valueOf(filaMaterial.getPrecioUnidad())
                                    }
                            );
                        }                      
                    } catch (SQLException eReconsultandoMateriales) {
                        JOptionPane.showMessageDialog(
                            crud_Materiales_GUI, 
                            "Error cargando todos los materiales para actuzlizar!!", 
                            "Error BD", 
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            break;

            case("Actualizar"):

                //Cargar la informacion de la fila que ha sido seleccionada en un VO
                int[] filasSeleccionadas = crud_Materiales_GUI.getJtMateriales().getSelectedRows();
                MaterialContruccion materialActualizar = new MaterialContruccion();
                ArrayList<String> filaIntermedia = new ArrayList<String>();
                int numeroColumnas = crud_Materiales_GUI.getModeloTablaMateriales().getColumnCount();
                for (int j = 0; j < numeroColumnas; j++) {
                    filaIntermedia.add((String)crud_Materiales_GUI.getJtMateriales().getValueAt(filasSeleccionadas[0], j));
                }
                materialActualizar.setIdMaterialConstruccion(Integer.valueOf(filaIntermedia.get(0)));
                materialActualizar.setNombreMaterial(filaIntermedia.get(1));
                materialActualizar.setImportado(filaIntermedia.get(2));
                materialActualizar.setPrecioUnidad(Integer.valueOf(filaIntermedia.get(3)));

                //Actualizar utilizando VO correspondiente
                //Solicitar al modelo la adicion del nuevo material
                MaterialContruccion materialActualizado = null;
                try {
                    materialActualizado = materialConstruccionDao.actualizarMaterial(materialActualizar);
                } catch (SQLException eMaterialActualizado) {
                    JOptionPane.showMessageDialog(
                        crud_Materiales_GUI, 
                        "Error actualizando material!!", 
                        "Error BD", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }
                //Reportar exito
                if (materialActualizado != null){
                    JOptionPane.showMessageDialog(
                            crud_Materiales_GUI, 
                            "Actualizacion exitoso!!", 
                            "Transaccion completa BD", 
                            JOptionPane.INFORMATION_MESSAGE
                        );
                }
            break;

            case("Borrar"):

                //Cargar la informacion de la fila que ha sido seleccionada en un VO
                int[] filasSeleccionadasEliminar = crud_Materiales_GUI.getJtMateriales().getSelectedRows();
                MaterialContruccion materialEliminar = new MaterialContruccion();
                ArrayList<String> filaIntermediaEliminar = new ArrayList<String>();
                for (int j = 0; j < crud_Materiales_GUI.getModeloTablaMateriales().getColumnCount(); j++) {
                    filaIntermediaEliminar.add((String)crud_Materiales_GUI.getJtMateriales().getValueAt(filasSeleccionadasEliminar[0], j));
                }
                materialEliminar.setIdMaterialConstruccion(Integer.valueOf(filaIntermediaEliminar.get(0)));
                materialEliminar.setNombreMaterial(filaIntermediaEliminar.get(1));
                materialEliminar.setImportado(filaIntermediaEliminar.get(2));
                materialEliminar.setPrecioUnidad(Integer.valueOf(filaIntermediaEliminar.get(3)));

                //Eliminar utilizando VO correspondiente
                //Solicitar al modelo la adicion del nuevo material
                MaterialContruccion materialEliminado = null;
                try {
                    materialEliminado = materialConstruccionDao.eliminarMaterial(materialEliminar);
                } catch (SQLException eMaterialActualizado) {
                    JOptionPane.showMessageDialog(
                        crud_Materiales_GUI, 
                        "Error actualizando material!!", 
                        "Error BD", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }
                //Reportar exito
                if (materialEliminado != null){
                    JOptionPane.showMessageDialog(
                            crud_Materiales_GUI, 
                            "Eliminacion exitoso!!", 
                            "Transaccion completa BD", 
                            JOptionPane.INFORMATION_MESSAGE
                        );
                }
                //Actulizar vista con nuevo material
                //Limpiar todos los materiales
                while(crud_Materiales_GUI.getModeloTablaMateriales().getRowCount() > 0){
                    crud_Materiales_GUI.getModeloTablaMateriales().removeRow(0);
                }
                //Cargar los materiales de la base de datos (version actualizada)
                try {
                    for(MaterialContruccion filaMaterial : materialConstruccionDao.consultarTodos()){

                        crud_Materiales_GUI.getModeloTablaMateriales().addRow(
                            new Object[]{
                                    String.valueOf(filaMaterial.getIdMaterialConstruccion()),
                                    filaMaterial.getNombreMaterial(),
                                    filaMaterial.getImportado(),
                                    String.valueOf(filaMaterial.getPrecioUnidad())
                                }
                        );
                    }                      
                } catch (SQLException eReconsultandoMateriales) {
                    JOptionPane.showMessageDialog(
                        crud_Materiales_GUI, 
                        "Error cargando todos los materiales para actuzlizar!!", 
                        "Error BD", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            break;
        }
		
	}

}
