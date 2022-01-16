package Main;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import AnalizadorLexico.ConversionAFD.IniciarConjuntos;
import AnalizadorLexico.Final.IniciarFinal;
import AnalizadorSintactico.Analisis.VentanaAnalisisSintactico;
import AnalizadorSintactico.ColeccionCanonica.iniciarColeccionCanonica;
import AnalizadorSintactico.ConstruccionTablaLR.IniciarTablaLR;
import AnalizadorSintactico.PrimerosSiguientes.IniciarPrimerosSiguientes;
import AnalizadorLexico.AlgoritmoThompson.VentanaThompson;
import Utilidades.ImagenFondo;
import Utilidades.Excepciones.ExcepcionER;

public class VentanaPrincipal extends JFrame {

    public JButton botonThompson, botonAfd, botonAnalizadorLex, botonPrimerosSiguientes, botonColeccionCanonica, botonTablaLR, botonAnalisisSintactico;
    private JLabel tituloAnalizadorLex, tituloAnalizadorSintac;
    private final JFrame ventanaPrincipal = this;

    public VentanaPrincipal() throws FontFormatException, IOException {
        super("Compilador");
        ImagenFondo ejemplo = new ImagenFondo();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(1200, 700)); // 600 400
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setContentPane(ejemplo);
        this.setLayout(null);
        GraphicsEnvironment crearFuente = GraphicsEnvironment.getLocalGraphicsEnvironment();
        crearFuente
                .registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src\\ArchivosExtra\\good times rg.ttf")));
        menuPrincipal();
        this.setVisible(true);
    }

    public void menuPrincipal() {
        menuAnalizadorLexico();
        menuAnalizadorSintactico();
    }

    public void menuAnalizadorLexico() {
        botonThompson = new JButton("Algoritmo de Thompson");
        botonAfd = new JButton("AFN->AFD");
        botonAnalizadorLex = new JButton("Analizador Léxico");
        botonAnalizadorLex = new JButton("Analizador Léxico");

        // PROPIEDADES DEL LOS LABELS A UTILIZAR
        tituloAnalizadorLex = new JLabel("Analizador Léxico");
        tituloAnalizadorLex.setBounds(45, 75, 628, 35);
        tituloAnalizadorLex.setFont(new Font("good times rg", Font.ROMAN_BASELINE, 40));
        tituloAnalizadorLex.setForeground(Color.WHITE);
        this.add(tituloAnalizadorLex);

        // DIMENSIONES DE LOS BOTONES
        botonThompson.setBounds(100, 165, 285, 65);
        botonAfd.setBounds(450, 165, 285, 65);
        botonAnalizadorLex.setBounds(800, 165, 285, 65);

        // DISEÑO DE LOS BOTONES
        botonThompson.setBackground(new Color(99, 8, 103));
        botonAfd.setBackground(new Color(99, 8, 103));
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

        botonThompson.setContentAreaFilled(false);
        botonThompson.setOpaque(true);
        botonThompson.setFocusPainted(false);
        botonAfd.setContentAreaFilled(false);
        botonAfd.setOpaque(true);
        botonAfd.setFocusPainted(false);
        botonAnalizadorLex.setContentAreaFilled(false);
        botonAnalizadorLex.setOpaque(true);
        botonAnalizadorLex.setFocusPainted(false);

        // ACCIONES DE LOS BOTONES
        botonThompson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                new VentanaThompson(ventanaPrincipal);
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
    }

    public void menuAnalizadorSintactico() {
        botonPrimerosSiguientes = new JButton("Primeros y Siguientes");
        botonColeccionCanonica = new JButton("Coleccion Canonica");
        botonTablaLR = new JButton("Tabla de Análisis Sintáctico");
        botonAnalisisSintactico = new JButton("Análisis Sintáctico");

        // PROPIEDADES DEL LOS LABELS A UTILIZAR
        tituloAnalizadorSintac = new JLabel("Analizador Sintáctico");
        tituloAnalizadorSintac.setBounds(45, 375, 828, 35);
        tituloAnalizadorSintac.setFont(new Font("good times rg", Font.ROMAN_BASELINE, 40));
        tituloAnalizadorSintac.setForeground(Color.WHITE);
        this.add(tituloAnalizadorSintac);

        // DIMENSIONES DE LOS BOTONES
        botonPrimerosSiguientes.setBounds(100, 465, 285, 65);
        botonColeccionCanonica.setBounds(450, 465, 285, 65);
        botonTablaLR.setBounds(800, 465, 285, 65);
        botonAnalisisSintactico.setBounds(100, 540, 285, 65);

        // DISEÑO DE LOS BOTONES
        botonPrimerosSiguientes.setBackground(new Color(99, 8, 103));
        botonColeccionCanonica.setBackground(new Color(99, 8, 103));
        botonTablaLR.setBackground(new Color(99, 8, 103));
        botonAnalisisSintactico.setBackground(new Color(99, 8, 103));

        botonPrimerosSiguientes.setBorder(new BevelBorder(BevelBorder.RAISED));
        botonColeccionCanonica.setBorder(new BevelBorder(BevelBorder.RAISED));
        botonTablaLR.setBorder(new BevelBorder(BevelBorder.RAISED));
        botonAnalisisSintactico.setBorder(new BevelBorder(BevelBorder.RAISED));

        botonPrimerosSiguientes.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 20));
        botonColeccionCanonica.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 20));
        botonTablaLR.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 20));
        botonAnalisisSintactico.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 20));

        botonPrimerosSiguientes.setForeground(Color.WHITE);
        botonColeccionCanonica.setForeground(Color.WHITE);
        botonTablaLR.setForeground(Color.WHITE);
        botonAnalisisSintactico.setForeground(Color.WHITE);

        botonPrimerosSiguientes.setContentAreaFilled(false);
        botonPrimerosSiguientes.setOpaque(true);
        botonPrimerosSiguientes.setFocusPainted(false);
        botonColeccionCanonica.setContentAreaFilled(false);
        botonColeccionCanonica.setOpaque(true);
        botonColeccionCanonica.setFocusPainted(false);
        botonTablaLR.setContentAreaFilled(false);
        botonTablaLR.setOpaque(true);
        botonTablaLR.setFocusPainted(false);
        botonAnalisisSintactico.setContentAreaFilled(false);
        botonAnalisisSintactico.setOpaque(true);
        botonAnalisisSintactico.setFocusPainted(false);

        // ACCIONES DE LOS BOTONES
        botonPrimerosSiguientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                new IniciarPrimerosSiguientes(ventanaPrincipal);
            }
        });

        botonColeccionCanonica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                new iniciarColeccionCanonica(ventanaPrincipal);
            }
        });

        botonTablaLR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                new IniciarTablaLR(ventanaPrincipal);
            }
        });

        botonAnalisisSintactico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                new VentanaAnalisisSintactico(ventanaPrincipal);
            }
        });
        

        // ANEXO DE LOS BOTONES AL LABEL
        add(botonPrimerosSiguientes);
        add(botonColeccionCanonica);
        add(botonTablaLR);
        add(botonAnalisisSintactico);
    }

}