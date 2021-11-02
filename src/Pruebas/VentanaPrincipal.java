//Tengo este problema que quiero que cuando haga click en el boton de analizador lexico me aparezcan los dos botones afd y thompson
//Sin embargo, no salen a menos que le de click a archivo, vaya no puedo encontrar mi problema, quiero creer que es el actionperformed pero ya hice unas pruebas y ando más perdido 
package Pruebas;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Pruebas.alexico.VentanaAThompson;
import Utilidades.ImagenFondo;
public class VentanaPrincipal extends JFrame{
    private static final long serialVersionUID = 1L;
    public JPanel panel;
    
    public VentanaPrincipal (){
        super ("Compilador");
        ImagenFondo ejemplo = new ImagenFondo ();
        panel = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(ejemplo); // Aqui va la imagen de fondo de la ventana, use una clase ImagenFondo que se encuentra en utilidades 
        this.setSize (new Dimension(600,400)); // Cambie las dimensiones de la Ventana Principal, esto fue más por la resolución de la imagen
        this.getContentPane().add(panel);
        panel.setOpaque(false);  //Esto opaca el menú de selección, se ve mucho mejor en mi opinión, hay otra alternativa que es panel.setBackground(new Color (0,0,0,64))
        ini ();
    }
    public void ini (){
        menuArchivo ();
        menuAnalizadorLexico();
        menuAnalizadorSintactico();
        menuAnalizadorSemántico();
    }
    public void menuArchivo (){
        //Esto es una prueba usando JPopupMenu dentro de un botón, una propuesta de menú desplegable, solo eso una propuesta
        JButton botonArchivo = new JButton("Archivo");
        JPopupMenu mnArchivo = new JPopupMenu ();
        mnArchivo.add ("Abrir");
        mnArchivo.add ("Nuevo");
        mnArchivo.add ("Guardar");
        botonArchivo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ev) {
                mnArchivo.show(botonArchivo, 20, 25);
            }
        });
        panel.add(botonArchivo);
    }
    public void menuAnalizadorLexico(){
        JButton botonAnalizadorlex = new JButton("Analizador léxico");
        botonAnalizadorlex.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed (ActionEvent e){
                thompson(e);
                afd (e);
            }
        });
        panel.add (botonAnalizadorlex);
    }
    public void menuAnalizadorSintactico(){
        JButton botonAnalizadorSin = new JButton("Analizador Sintactico");
        botonAnalizadorSin.setEnabled(false);
        panel.add(botonAnalizadorSin);
    }
    public void menuAnalizadorSemántico(){
        JButton botonAnalizadorSem = new JButton("Analizador Semántico");
        botonAnalizadorSem.setEnabled(false);
        panel.add(botonAnalizadorSem);
    }
    public void thompson (ActionEvent evt){
        JButton botonthompson = new JButton("Algoritmo de Thompson");
        botonthompson.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed (ActionEvent event){
                 //VentanaAThompson vthompson = new VentanaAThompson (this,true);    //ESTE CHECALO YA QUE ESTA RELACIONADO CON LA OTRA CLASE
            }
        });
        add (botonthompson);  //El boton lo agrego al Jframe en vez del panel, intente usar otro panel pero me salia una cosa bien fea 
    }
    public void afd (ActionEvent evt){
        JButton botonafd = new JButton("AFN -> AFD");
        botonafd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed (ActionEvent event){
                //
            }
        });
        add (botonafd);   
    }
}