package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.SpringLayout;
import java.awt.Image;

import controller.ControladorRequerimientoReto4;
import util.SpringUtilities;

public class MenuPrincipalGUI extends JFrame{  

    //Constructor realizar la composicion de la ventana
    public MenuPrincipalGUI(){
    }

    //Metodo para Redimensionar iconos
    private ImageIcon redimensionarIcon(ImageIcon icono, int pixeles){
        Image image = icono.getImage();
        Image newimg = image.getScaledInstance(pixeles, pixeles,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    //Metodo para la composicion
    public void iniciarGUI(ControladorRequerimientoReto4 controlador){

        //Propiedades del frame
        setTitle("Menu Principal Reto 5 / CRUD");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout());
        
        //==============Panel de opcionse menu principal=========================
        //Agregar Componentes
        JPanel panelMenu = new JPanel(new SpringLayout());
        String[] botonesPrincipales = { "Ranking Proyectos", 
                                        "Ranking Bancos", 
                                        "Ranking Materiales Importados",
                                        "Gestion Materiales",
                                        "Gestion Compras"
                                        };
        for (int i = 0; i < botonesPrincipales.length; i++) {
            JButton btn = new JButton(redimensionarIcon(new ImageIcon("img/"+botonesPrincipales[i]+".png"), 48));
            btn.setText(botonesPrincipales[i]);
            btn.addActionListener(controlador);
            btn.setActionCommand(botonesPrincipales[i]);
            panelMenu.add(btn);
        }
        SpringUtilities.makeCompactGrid(panelMenu,
                                        panelMenu.getComponentCount(), 1,
                                        9, 25, 6, 6);

        //Contenedor
        getContentPane().add(panelMenu);

        //Mostrar ventana/frame
        setSize(300, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
