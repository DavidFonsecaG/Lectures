package view;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;

import model.vo.ProyectoRankeadoCompras;

import java.util.ArrayList;

public class Requerimiento1_GUI extends JFrame{

    //Atributes
    private JTable jtProyectos;

    //Constructor
    public Requerimiento1_GUI(ArrayList<ProyectoRankeadoCompras> proyectos){

        //Propiedades del frame
        setTitle("-----10 Proyectos Mayor Gasto-------");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Construcción de la tabla que presentará los proyectos
        String[] encabezado = {"ID_Proyecto", "Clasificación", "Gasto_Compras", "Serial"};
        this.jtProyectos = new JTable(this.formatoRegistros(proyectos, encabezado.length), encabezado);
        JScrollPane sp = new JScrollPane(this.jtProyectos);

        //Componente intermedio
        JPanel panel = new JPanel(new GridLayout());
        panel.setBorder(new TitledBorder("Ranking Mayor Gasto Por Compras"));
        panel.add(sp);     
        
        //Contenedor
        getContentPane().add(panel);        

        //Mostrar ventana/frame
        setSize(400,400);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private String[][] formatoRegistros(ArrayList<ProyectoRankeadoCompras> proyectos, int numeroEncabezados){
        
        //Declaración del contenedor de retorno
        String[][] registros = new String[proyectos.size()][numeroEncabezados];        

        //Desenvolver los objetos de la colección
        for (int i = 0; i < proyectos.size(); i++) {
            registros[i][0] = String.valueOf(proyectos.get(i).getIdProyecto()); 
            registros[i][1] = proyectos.get(i).getClasificacion();            
            registros[i][2] = String.valueOf(proyectos.get(i).getGastoCompras());
            registros[i][3] = proyectos.get(i).getSerial();            
        }

        //Retornar registros en formato JTable
        return registros;

    }

    
}