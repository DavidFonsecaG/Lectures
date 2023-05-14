package view;

import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import util.SpringUtilities;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.ControladorRequerimientoReto4;
import model.vo.MaterialContruccion;

public class CRUD_Materiales_GUI extends JFrame{

    //Atributes
    private JTable jtMateriales;
    private DefaultTableModel modeloTablaMateriales;
    private JTextField txtNombreMaterial;
    private JTextField txtImportado;
    private JTextField txtPrecioUnidad;   

    //Constructor
    public CRUD_Materiales_GUI(ArrayList<MaterialContruccion> materiales, ControladorRequerimientoReto4 controlador){

        //Propiedades del frame
        setTitle("Gestion de Materiales de Construccion");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout());  

        //================================Construcción de la tabla que presentará los materiales===================================
        String[] encabezado = {"ID Material", "Nombre", "Importado", "Precio Unitario"};
        //Forma basica de contruccion de la tabla
        //this.jtMateriales = new JTable(this.formatoRegistros(materiales, encabezado.length), encabezado);
        //COnstruccion de la tabla a partir del modelo -> personalizacion y actualizacion
        modeloTablaMateriales = new DefaultTableModel(this.formatoRegistros(materiales, encabezado.length), encabezado){
            //Personalizar quien no es editable
            @Override
            public boolean isCellEditable(int row, int column) {
                //Que sea editable cuando la clumna es diferente de la primera
                return column != 0;
                //Establecer cuales son editables
                //return column ==1 || column == 3;
            }            
        };
        this.jtMateriales = new JTable(modeloTablaMateriales);
        //Colocar dentro de scroll panel
        JScrollPane sp = new JScrollPane(this.jtMateriales);
        //Componente intermedio tabla con materiales
        JPanel panel = new JPanel(new GridLayout());
        panel.setBorder(new TitledBorder("Materiales del SI"));
        panel.add(sp);
        
        //===================================Panel para organizar formulario y acciones===============================================
        //Componente intermedio para formulario de adicion de material
        JPanel panelSectorDerecho = new JPanel(new GridLayout(2,1));
        panelSectorDerecho.setBorder(new TitledBorder("Registro de Materiales del SI"));

        String[] labels = {"Nombre Material: ", "Importado: ", "Precio Unidad: "};
        int numPairs = labels.length;

        this.txtNombreMaterial = new JTextField(10);
        this.txtImportado = new JTextField(10);
        this.txtPrecioUnidad = new JTextField(10);
        JTextField[] textFields = {this.txtNombreMaterial, this.txtImportado, this.txtPrecioUnidad};

        //Create and populate the panel.
        JPanel panelFormulario = new JPanel(new SpringLayout());
        for (int i = 0; i < numPairs; i++) {
            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
            panelFormulario.add(l);
            l.setLabelFor(textFields[i]);
            panelFormulario.add(textFields[i]);
        }

        //Lay out the panel.
        SpringUtilities.makeCompactGrid(panelFormulario,
                                        numPairs, 2, //rows, cols
                                        6, 6,        //initX, initY
                                        6, 6);       //xPad, yPad

        //Componente intermedio de botones
        String[] botones = {"Adicionar", "Actualizar", "Borrar"};
        JPanel panelBotones = new JPanel(new SpringLayout());
        //panelBotones.setBorder(new TitledBorder("Acciones"));
        for (int i = 0; i < botones.length; i++) {
            JButton btn = new JButton(redimensionarIcono(new ImageIcon("img/"+botones[i]+".png"), 32));
            btn.setText(botones[i]);
            btn.addActionListener(controlador);        
            btn.setActionCommand(botones[i]);
            panelBotones.add(btn);
        }        
        SpringUtilities.makeCompactGrid(panelBotones, 1,
                                panelBotones.getComponentCount(),
                                6, 6, 6, 6);
        
        panelSectorDerecho.add(panelFormulario);
        panelSectorDerecho.add(panelBotones);
        
        //Contenedor
        getContentPane().add(panel);        
        getContentPane().add(panelSectorDerecho);      

        //Mostrar ventana/frame
        setSize(800,300);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private ImageIcon redimensionarIcono(ImageIcon icono, int pixeles){        
        Image image = icono.getImage(); 
        Image newimg = image.getScaledInstance(pixeles, pixeles,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    private String[][] formatoRegistros(ArrayList<MaterialContruccion> materiales, int numeroEncabezados){
        
        //Declaración del contenedor de retorno
        String[][] registros = new String[materiales.size()][numeroEncabezados];        

        //Desenvolver los objetos de la colección
        for (int i = 0; i < materiales.size(); i++) {
            registros[i][0] = String.valueOf(materiales.get(i).getIdMaterialConstruccion()); 
            registros[i][1] = materiales.get(i).getNombreMaterial();            
            registros[i][2] = materiales.get(i).getImportado();
            registros[i][3] = String.valueOf(materiales.get(i).getPrecioUnidad());            
        }

        //Retornar registros en formato JTable
        return registros;

    }

    public JTable getJtMateriales() {
        return jtMateriales;
    }

    public void setJtMateriales(JTable jtMateriales) {
        this.jtMateriales = jtMateriales;
    }

    public JTextField getTxtNombreMaterial() {
        return txtNombreMaterial;
    }

    public void setTxtNombreMaterial(JTextField txtNombreMaterial) {
        this.txtNombreMaterial = txtNombreMaterial;
    }

    public JTextField getTxtImportado() {
        return txtImportado;
    }

    public void setTxtImportado(JTextField txtImportado) {
        this.txtImportado = txtImportado;
    }

    public JTextField getTxtPrecioUnidad() {
        return txtPrecioUnidad;
    }

    public void setTxtPrecioUnidad(JTextField txtPrecioUnidad) {
        this.txtPrecioUnidad = txtPrecioUnidad;
    }

    public DefaultTableModel getModeloTablaMateriales() {
        return modeloTablaMateriales;
    }

    public void setModeloTablaMateriales(DefaultTableModel modeloTablaMateriales) {
        this.modeloTablaMateriales = modeloTablaMateriales;
    }  

    
}
