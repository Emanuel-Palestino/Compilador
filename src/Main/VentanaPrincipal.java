package Main;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import AnalizadorLexico.ConversionAFD.IniciarConjuntos;
import AnalizadorLexico.Final.IniciarFinal;
import AnalizadorLexico.AlgoritmoThompson.IniciarThompson;
import Utilidades.ImagenFondo;
import Utilidades.Excepciones.ExcepcionER;

public class VentanaPrincipal extends JFrame {

    public JButton botonThompson, botonAfd, botonAnalizadorLex;
    private JLabel tituloAnalizadorLex;
    private final JFrame ventanaPrincipal = this;

    public VentanaPrincipal() {
        super("Compilador");
        ImagenFondo ejemplo = new ImagenFondo();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(1200, 700)); // 600 400
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setContentPane(ejemplo);
        this.setLayout(null);
        menuPrincipal();
    }

    public void menuPrincipal() {
        menuAnalizadorLexico();
    }

    public void menuAnalizadorLexico() {
        botonThompson = new JButton("Algoritmo de Thompson");
        botonAfd = new JButton("AFN->AFD");
        botonAnalizadorLex = new JButton("Analizador Léxico");

        // PROPIEDADES DEL LOS LABELS A UTILIZAR
        tituloAnalizadorLex = new JLabel("Analizador Léxico");
        tituloAnalizadorLex.setBounds(45, 75, 528, 35);
        tituloAnalizadorLex.setFont(new Font("Good Times Rg", Font.ROMAN_BASELINE, 40));
        tituloAnalizadorLex.setForeground(Color.WHITE);
        this.add(tituloAnalizadorLex);

        // DIMENSIONES DE LOS BOTONES
        botonThompson.setBounds(100, 165, 285, 65);
        botonAfd.setBounds(450, 165, 285, 65);
        botonAnalizadorLex.setBounds(800, 165, 285, 65);

        // DISEÑO DE LOS BOTONES
        botonThompson.setBackground(new Color (99, 8, 103));
        botonAfd.setBackground(new Color (99, 8, 103));
        botonAnalizadorLex.setBackground(new Color(99, 8, 103));

        botonThompson.setBorder(new BevelBorder(BevelBorder.RAISED));
        botonAfd.setBorder(new BevelBorder(BevelBorder.RAISED));
        botonAnalizadorLex.setBorder(new BevelBorder(BevelBorder.RAISED));

        botonThompson.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 20));
        botonAfd.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 20));
        botonAnalizadorLex.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 20));

        botonThompson.setForeground(Color.WHITE);
        botonAfd.setForeground(Color.WHITE);
        botonAnalizadorLex.setForeground(Color.WHITE);

        botonThompson.setContentAreaFilled (false);
        botonThompson.setOpaque(true);
        botonThompson.setFocusPainted(false);
        botonAfd.setContentAreaFilled (false);
        botonAfd.setOpaque(true);
        botonAfd.setFocusPainted(false);
        botonAnalizadorLex.setContentAreaFilled (false);
        botonAnalizadorLex.setOpaque(true);  
        botonAnalizadorLex.setFocusPainted(false);

        // ACCIONES DE LOS BOTONES
        botonThompson.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    try {
                        new IniciarThompson(ventanaPrincipal);
                    } catch (IOException | ExcepcionER e) {
                        e.printStackTrace();
                    }
                }
            });
        
        botonAfd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    try {
                        new IniciarConjuntos(ventanaPrincipal);
                    } catch (IOException | ExcepcionER e) {
                        e.printStackTrace();
                    }
                }
            });
        botonAnalizadorLex.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    new IniciarFinal(ventanaPrincipal);
                }
        });
        // ANEXO DE LOS BOTONES AL LABEL
        add(botonThompson);
        add(botonAfd);
        add(botonAnalizadorLex);
        this.setVisible(true);
    }
}