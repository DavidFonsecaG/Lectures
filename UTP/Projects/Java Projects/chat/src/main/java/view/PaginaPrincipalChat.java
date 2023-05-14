package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PaginaPrincipalChat extends JFrame{
    private JMenuBar mb;
    private JMenu m1;
    private JMenu m2;
    private JMenuItem m11;
    private JMenuItem m12;
    private JPanel p;
    private JLabel l;
    private JTextField tf;
    private JButton btnSend;
    private JButton btnReset;
    private JTextArea ta;

    public PaginaPrincipalChat(){
    }

    public void iniciarApp(){
        //Frame Properties
        setTitle("My WhatsApp");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Creating MenuBar
        this.mb = new JMenuBar();
        this.m1 = new JMenu("File");
        this.m2 = new JMenu("Help");
        this.mb.add(this.m1);
        this.mb.add(this.m2);
        this.m11 = new JMenuItem("Open");
        this.m12 = new JMenuItem("Save as");
        this.m1.add(this.m11);
        this.m1.add(this.m12);

        //Creating lower panel
        this.p = new JPanel();
        this.l = new JLabel("Enter Text");
        this.tf = new JTextField(10);
        this.btnSend = new JButton("Send");
        this.btnReset = new JButton("Reset");
        this.p.add(this.l);
        this.p.add(this.tf);
        this.p.add(this.btnSend);
        this.p.add(this.btnReset);

        //Creating central Text Area
        this.ta = new JTextArea();

        //Adding components to Frame
        getContentPane().add(BorderLayout.NORTH, mb);
        getContentPane().add(BorderLayout.CENTER, ta);
        getContentPane().add(BorderLayout.SOUTH, p);

        //Show Frame
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
