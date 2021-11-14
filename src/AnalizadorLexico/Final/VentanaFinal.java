package AnalizadorLexico.Final;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.*;
import java.awt.*;

public class VentanaFinal extends JDialog{
    JDialog ventana;
    FlowLayout diseñoPanel;
    JPanel panelInformacion;
    JScrollPane panelTabla,panelErrores,panelId;
    JLabel lblAlfabeto,lblExpresionRegular;
    JTextField textAlfabeto,textExpresionRegular,mostrarArchivo;
    JTable transiciones,tablaErrores,tablaId;
    JButton boton;
    String rutaNueva;

    public VentanaFinal(JFrame parent, boolean modal, String alfabeto, String expresion, String[] encabezado, String[][] datos, String [] encabezadoErr, String[][] datosErrores, String [] encabezadoId, String [][] datosId,String rutaArchivo) {
    super(parent, modal);
                
    // Iniciar componentes que se muestran en la ventana
    inicializarInformacion(rutaArchivo);
    mostrarTabla(encabezado, datos,encabezadoErr,datosErrores,encabezadoId,datosId); 
    ventana.setVisible(true);
    }

  public VentanaFinal(JFrame parent, boolean modal, String rutaArchivo){
    super(parent,modal); 
    String [] encabezado = {"# linea", "Lexema", "Token"};
    String [][] datos ={
      {"1", "int","int"},
            {"1", "main","main"},
            {"1", "(","("}
    };

    String [] encabezadoErr = {"# Linea","Descripción"};
    String [][] datosErrores={
      {"","En función 'main'"},
      {"2","Error: @ simbolo no definido"}
    };

    String [] encabezadoId = {"Id ","Valor","Función"};
    String [][] datosId={
      {"c","4","32"},
      {"d","6","33"}
    };

    //Iniciar componentes
    inicializarInformacion(rutaArchivo);
    mostrarTabla(encabezado, datos,encabezadoErr,datosErrores,encabezadoId,datosId);
    ventana.setVisible(true);
  }

  private void inicializarInformacion(String rutaArchivo){
    diseñoPanel = new FlowLayout(FlowLayout.LEFT,10,10);
    ventana = new JDialog();
    ventana.setSize(1400,760);
    ventana.setLocationRelativeTo(null);
    ventana.setResizable(false);
    ventana.setLayout(diseñoPanel);

    ventana.setTitle("Analizador Lexico");

    panelInformacion = new JPanel();
    panelInformacion.setPreferredSize(new Dimension(500,200));
    panelInformacion.setLayout(diseñoPanel);
    

    //agregamos boton
    boton = new JButton("Buscar Archivo");
    boton.setPreferredSize(new Dimension(130,30));

    mostrarArchivo = new JTextField();
    mostrarArchivo.setPreferredSize(new Dimension(350, 30));
    mostrarArchivo.setEditable(false);
    mostrarRutaArchivo(rutaArchivo);   

    //Agregamos que el boton accione
    boton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed (ActionEvent e) {
          /*Codigo archivo */
          //rutaNueva = ruta del archivo que saca del codigo;
          rutaNueva = "C:/compilador/ejemplo2";
          mostrarRutaArchivo(rutaNueva); 
      }
    });

        
    // Agregar elementos al panel Informacion
    panelInformacion.add(mostrarArchivo);
    panelInformacion.add(boton);
    

    ventana.setLayout(new BorderLayout());
    ventana.add(panelInformacion,BorderLayout.PAGE_START);

  }

  
  public void mostrarTabla(String [] encabezado, String[][] datos, String[] encabezadoErrores, String[][] datosErrores, String[] encabezadoId, String[][] datosId){
    DefaultTableModel modeloTabla = new DefaultTableModel(datos,encabezado);
    DefaultTableModel modeloErrores = new DefaultTableModel(datosErrores,encabezadoErrores);
    DefaultTableModel modeloId = new DefaultTableModel(datosId,encabezadoId);

    transiciones = new JTable(modeloTabla);
    transiciones.setEnabled(false);
    transiciones.getTableHeader().setReorderingAllowed(false);
    transiciones.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    tablaErrores = new JTable(modeloErrores);
    tablaErrores.setEnabled(false);
    tablaErrores.getTableHeader().setReorderingAllowed(false);

    tablaId = new JTable(modeloId);
    tablaId.setEnabled(false);
    tablaId.getTableHeader().setReorderingAllowed(false);
    tablaId.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    panelTabla = new JScrollPane(transiciones);
    panelTabla.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"1. Tabla de Tokens",TitledBorder.CENTER,TitledBorder.TOP));
    panelTabla.setPreferredSize(new Dimension(550,550));

    panelId = new JScrollPane(tablaId);
    panelId.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"2. Tabla de símbolos",TitledBorder.CENTER,TitledBorder.TOP));
    panelId.setPreferredSize(new Dimension(550,550));

    panelErrores = new JScrollPane(tablaErrores);
    panelErrores.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"3. Tabla de Errores",TitledBorder.CENTER,TitledBorder.TOP));
    panelErrores.setPreferredSize(new Dimension(350,550));

    //Hacemos visible la tabla
    ventana.add(panelTabla,BorderLayout.LINE_START);
    ventana.add(panelId,BorderLayout.CENTER);
    ventana.add(panelErrores,BorderLayout.LINE_END);
  }

  public void mostrarRutaArchivo(String rutaArchvo){
        mostrarArchivo.setText(rutaArchvo);
    }
}
