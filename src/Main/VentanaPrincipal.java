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
import AnalizadorLexico.AlgoritmoThompson.IniciarThompson;
import Utilidades.ImagenFondo;
import Utilidades.Excepciones.ExcepcionER;

public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel label1;

    public VentanaPrincipal() {
        super("Compilador");
        ImagenFondo ejemplo = new ImagenFondo();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(600, 400));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setContentPane(ejemplo);
        this.setLayout(null);
        ini();
    }

    public void ini() {
        menuAnalizadorLexico();
    }

    public void menuAnalizadorLexico() {
        JButton botonthompson = new JButton("Thompson");
        JButton botonafd = new JButton("AFN->AFD");
        JButton botonAnalizadorlex = new JButton("A.Léxico");

        // PROPIEDADES DEL LOS LABELS A UTILIZAR
        label1 = new JLabel("Analizador Léxico");
        label1.setBounds(134, 75, 307, 50);
        label1.setFont(new Font("Tahoma", Font.ITALIC, 40));
        label1.setForeground(Color.WHITE);
        this.add(label1);

        // DIMENSIONES DE LOS BOTONES
        botonthompson.setBounds(25, 165, 168, 30);
        botonafd.setBounds(210, 165, 168, 30);
        botonAnalizadorlex.setBounds(395, 165, 168, 30);

        // DISEÑO DE LOS BOTONES
        botonthompson.setBackground(new Color(99, 8, 103));
        botonafd.setBackground(new Color(99, 8, 103));
        botonAnalizadorlex.setBackground(new Color(99, 8, 103));

        botonthompson.setBorder(new BevelBorder(BevelBorder.RAISED));
        botonafd.setBorder(new BevelBorder(BevelBorder.RAISED));
        botonAnalizadorlex.setBorder(new BevelBorder(BevelBorder.RAISED));

        botonthompson.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 17));
        botonafd.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 17));
        botonAnalizadorlex.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 17));

        botonthompson.setForeground(Color.WHITE);
        botonafd.setForeground(Color.WHITE);
        botonAnalizadorlex.setForeground(Color.WHITE);

        botonthompson.setFocusPainted(false);
        botonafd.setFocusPainted(false);
        botonAnalizadorlex.setFocusPainted(false);

        // ACCIONES DE LOS BOTONES
        {
            botonthompson.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    try {
                        new IniciarThompson();
                    } catch (IOException | ExcepcionER e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        {
            botonafd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    try {
                        new IniciarConjuntos();
                    } catch (IOException | ExcepcionER e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        {
            botonAnalizadorlex.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    // POR AGREGAR FINAL
                }
            });
        }
        // ANEXO DE LOS BOTONES AL LABEL
        add(botonthompson);
        add(botonafd);
        add(botonAnalizadorlex);
    }

}