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
import model.vo.Compra;

public class CRUD_Compras_GUI extends JFrame{

    //Atributes
    private JTable jtCompras;
    private DefaultTableModel modeloTablaCompras;
    private JTextField txtCantidad;
    private JTextField txtProveedor;
    private JTextField txtPagado;   
    private JTextField txtFecha;   
    private JTextField txtIdProyecto;   
    private JTextField txtIdMaterial;

    //Constructor
    public CRUD_Compras_GUI(ArrayList<Compra> compras, ControladorRequerimientoReto4 controlador){

        //Propiedades del frame
        setTitle("Gestion de Compras");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout());  

        //================================Construcción de la tabla que presentará los materiales===================================
        String[] encabezado = {"ID Compra", "Cantidad", "Proveedor", "Pagado", "Fecha", "ID Proyecto", "ID Material"};
        modeloTablaCompras = new DefaultTableModel(this.formatoRegistros(compras, encabezado.length), encabezado){
            //Personalizar quien no es editable
            @Override
            public boolean isCellEditable(int row, int column) {
                //Que sea editable cuando la clumna es diferente de la primera
                return column != 0;
                //Establecer cuales son editables
                //return column ==1 || column == 3;
            }            
        };
        this.jtCompras = new JTable(modeloTablaCompras);
        //Colocar dentro de scroll panel
        JScrollPane sp = new JScrollPane(this.jtCompras);
        //Componente intermedio tabla con materiales
        JPanel panel = new JPanel(new GridLayout());
        panel.setBorder(new TitledBorder("Compras del SI"));
        panel.add(sp);
        
        //===================================Panel para organizar formulario y acciones===============================================
        //Componente intermedio para formulario de adicion de material
        JPanel panelSectorDerecho = new JPanel(new SpringLayout());
        panelSectorDerecho.setBorder(new TitledBorder("Registro de Materiales del SI"));

        String[] labels = {"Cantidad: ", "Proveedor: ", "Pagado: ", "Fecha: ", "ID Proyecto: ", "ID Material: "};
        int numPairs = labels.length;

        this.txtCantidad = new JTextField(10);
        this.txtProveedor = new JTextField(10);
        this.txtPagado = new JTextField(10);
        this.txtFecha = new JTextField(10);
        this.txtIdProyecto = new JTextField(10);
        this.txtIdMaterial = new JTextField(10);

        JTextField[] textFields = {this.txtCantidad, this.txtProveedor, this.txtPagado, this.txtFecha, this.txtIdProyecto, this.txtIdMaterial};

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
        SpringUtilities.makeCompactGrid(panelSectorDerecho,
                                        panelSectorDerecho.getComponentCount(), 1,
                                        6, 6, 6, 6);
        
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

    private String[][] formatoRegistros(ArrayList<Compra> compras, int numeroEncabezados){
        
        //Declaración del contenedor de retorno
        String[][] registros = new String[compras.size()][numeroEncabezados];        

        //Desenvolver los objetos de la colección
        for (int i = 0; i < compras.size(); i++) {
            registros[i][0] = String.valueOf(compras.get(i).getIdCompra()); 
            registros[i][1] = String.valueOf(compras.get(i).getCantidad());            
            registros[i][2] = compras.get(i).getProveedor();
            registros[i][3] = compras.get(i).getPagado();
            registros[i][4] = compras.get(i).getFecha();            
            registros[i][5] = String.valueOf(compras.get(i).getIdProyecto());
            registros[i][6] = String.valueOf(compras.get(i).getIdMaterialConstruccion());            
        }

        //Retornar registros en formato JTable
        return registros;

    }

    
}
