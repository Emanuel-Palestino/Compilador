package AnalizadorLexico.Final;

import javax.swing.JDialog;
import javax.swing.JFrame;
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
    JTextField mostrarRutaArchivo;
    JTable tablaTokens,tablaErrores,tablaId;
    JButton boton;
    String rutaNueva;

    //Modelos de tabla
    DefaultTableModel modeloTokens = new DefaultTableModel();
    DefaultTableModel modeloErrores = new DefaultTableModel();
    DefaultTableModel modeloId = new DefaultTableModel();
    
    String [][] datosCambio ={
      {"1", "int","int"},
      {"1", "prueba","prueba"},
      {"1", "(","("},
      {"1", ")",")"}
    };

    String [][] datosErroresCambio={
          {"","En función 'prueba'"},
          {"2","Error: # simbolo no definido"},
          {"2","Error: id no tipado"}
    };

    String [][] datosIdCambio={
          {"a","34","prueba"},
          {"g","23","prueba"},
          {"acumulador","0","prueba"}
    };

    public VentanaFinal(JFrame parent, boolean modal, String alfabeto, String expresion, String[] encabezado, String[][] datos, String [] encabezadoErr, String[][] datosErrores, String [] encabezadoId, String [][] datosId,String rutaArchivo) {
    super(parent, modal);
                
    // Iniciar componentes que se muestran en la ventana
    inicializarInformacion(rutaArchivo);
    mostrarTabla(datos,datosErrores,datosId); 
    ventana.setVisible(true);
    }

  public VentanaFinal(JFrame parent, boolean modal, String rutaArchivo){
    super(parent,modal); 
    String [][] datos ={
        {"1", "int","int"},
        {"1", "main","main"},
        {"1", "(","("}
    };

    String [][] datosErrores={
        {"","En función 'main'"},
        {"2","Error: @ simbolo no definido"}
    };

    String [][] datosId={
        {"c","4","32"},
        {"d","6","33"}
    };

    //Iniciar componentes
    inicializarInformacion(rutaArchivo);
    mostrarTabla(datos,datosErrores,datosId);
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

    mostrarRutaArchivo = new JTextField();
    mostrarRutaArchivo.setPreferredSize(new Dimension(350, 30));
    mostrarRutaArchivo.setEditable(false);
    mostrarRutaArchivo(rutaArchivo);   

    //Agregamos que el boton accione
    boton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed (ActionEvent e) {
          /*Codigo archivo */
          //rutaNueva = ruta del archivo que saca del codigo;
          rutaNueva = "C:/compilador/ejemplo2";
          mostrarRutaArchivo(rutaNueva);
          editarTabla(datosCambio, datosErroresCambio, datosIdCambio);
      }
    });

    // Agregar elementos al panel Informacion
    panelInformacion.add(mostrarRutaArchivo);
    panelInformacion.add(boton);
    

    ventana.setLayout(new BorderLayout());
    ventana.add(panelInformacion,BorderLayout.PAGE_START);

  }

  
  public void mostrarTabla( String[][] datos, String[][] datosErrores,  String[][] datosId){
    String [] encabezado = {"# linea", "Lexema", "Token"};
    String [] encabezadoErrores = {"# Linea","Descripción"};
    String [] encabezadoId = {"Id ","Valor","Función"};

    modeloTokens.setDataVector(datos, encabezado);
    modeloErrores.setDataVector(datosErrores, encabezadoErrores);
    modeloId.setDataVector(datosId,encabezadoId);

    tablaTokens = new JTable();
    tablaTokens.setEnabled(false);
    tablaTokens.getTableHeader().setReorderingAllowed(false);
    tablaTokens.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    tablaErrores = new JTable();
    tablaErrores.setEnabled(false);
    tablaErrores.getTableHeader().setReorderingAllowed(false);

    tablaId = new JTable();
    tablaId.setEnabled(false);
    tablaId.getTableHeader().setReorderingAllowed(false);
    tablaId.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    panelTabla = new JScrollPane(tablaTokens);
    panelTabla.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"1. Tabla de Tokens",TitledBorder.CENTER,TitledBorder.TOP));
    panelTabla.setPreferredSize(new Dimension(550,550));

    panelId = new JScrollPane(tablaId);
    panelId.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"2. Tabla de símbolos",TitledBorder.CENTER,TitledBorder.TOP));
    panelId.setPreferredSize(new Dimension(550,550));

    panelErrores = new JScrollPane(tablaErrores);
    panelErrores.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"3. Tabla de Errores",TitledBorder.CENTER,TitledBorder.TOP));
    panelErrores.setPreferredSize(new Dimension(350,550));

    tablaTokens.setModel(modeloTokens);
    tablaId.setModel(modeloId);
    tablaErrores.setModel(modeloErrores);

    //Hacemos visible la tabla
    ventana.add(panelTabla,BorderLayout.LINE_START);
    ventana.add(panelId,BorderLayout.CENTER);
    ventana.add(panelErrores,BorderLayout.LINE_END);
  }

  public void mostrarRutaArchivo(String rutaArchvo){
        mostrarRutaArchivo.setText(rutaArchvo);
    }

  public void editarTabla(String[][] datosCambio, String[][] datosErroresCambio,  String[][] datosIdCambio){
    //Para efectos de las pruebas solo le puse el "(prueba)" para ver si cambiaba pero como seran las mismas columnas cambiarlo
    String [] encabezadoPruebas = {"# linea (prueba)", "Lexema(prueba)", "Token(prueba)"};
    String [] encabezadoErroresPruebas = {"# Linea(prueba)","Descripción(prueba)"};
    String [] encabezadoIdPruebas = {"Id(prueba) ","Valor(prueba)","Función(prueba)"};

    modeloTokens.setDataVector(datosCambio,encabezadoPruebas);
    modeloErrores.setDataVector(datosErroresCambio,encabezadoErroresPruebas);
    modeloId.setDataVector(datosIdCambio,encabezadoIdPruebas);

    modeloTokens.fireTableDataChanged();
    modeloErrores.fireTableDataChanged();
    modeloId.fireTableDataChanged();
  }
}
