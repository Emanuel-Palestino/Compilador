package Pruebas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import Pruebas.alexico.VentanaAThompson;
public class VentanaPrincipal extends JFrame{
    private static final long serialVersionUID = 1L;
    public JPanel panel;
    private JMenuBar menuBar;
    
    public VentanaPrincipal (){
        super ("Compilador");
        panel = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize (new Dimension(1000,700));
        this.getContentPane().add(panel);
        this.getContentPane().setBackground (Color.BLACK);  //CHECAR QUE MARQUE EL FONDO NEGRO 
        ini ();
    }
    public void ini (){
        //menuArchivo ();
        menuAnalizadorLexico();
        //menuAnalizadorSintactico();
        //menuAnalizadorSemántico();
    }
    //public void menuArchivo (){
        
    //}
    public void menuAnalizadorLexico(){
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu mnAlexico= new JMenu ("Analizador léxico");
        menuBar.add (mnAlexico);
        JMenuItem mntmAThompson = new JMenuItem ("Algoritmo de Thompson AFN");
        mntmAThompson.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed (ActionEvent e){
                thompson(e);
            }
        });
        mnAlexico.add (mntmAThompson);
    }
    //public void menuAnalizadorSintactico(){

    //}
    //public void menuAnalizadorSemántico(){

    //}
    public void thompson (ActionEvent evt){
        //VentanaAThompson vthompson = new VentanaAThompson (this,true);    //ESTE CHECALO YA QUE ESTA RELACIONADO CON LA OTRA CLASE
    }
}