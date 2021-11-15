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
    JScrollPane panelTablaTokens,panelTablaErrores,panelTablaSimbolos;
    JTextField mostrarRutaArchivo;
    JTable tablaTokens,tablaErrores,tablaSimbolos;
    JButton botonArchivo;
    String rutaNueva;

     //encabezados tablas
    String [] encabezadoTokensFinal = {"# linea", "Lexema", "Token"};
    String [] encabezadoErroresFinal = {"# Linea","Descripción"};
    String [] encabezadoVariablesFinal = {"Id ","Valor","Función"};

    //Modelos de tabla
    DefaultTableModel modeloTablaTokens = new DefaultTableModel();
    DefaultTableModel modeloTablaErrores = new DefaultTableModel();
    DefaultTableModel modeloTablaSimbolos = new DefaultTableModel();
    
    

    public VentanaFinal(JFrame parent, boolean modal, String alfabeto, String expresion, String[] encabezado, String[][] datos, String [] encabezadoErr, String[][] datosErrores, String [] encabezadoId, String [][] datosId,String rutaArchivo) {
        super(parent, modal);
                      
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

		inicializarInformacion(rutaArchivo);
		mostrarTabla(datos,datosErrores,datosId);
		ventana.setVisible(true);
  	}

	private void inicializarInformacion(String rutaArchivo){
	  	//Strings de prueba
		String [][] datosTokensPrueba ={
			{"1", "int","int"},
			{"1", "prueba","prueba"},
			{"1", "(","("},
			{"1", ")",")"}
		};

		String [][] datosErroresPrueba={
			{"","En función 'prueba'"},
			{"2","Error: # simbolo no definido"},
			{"2","Error: id no tipado"}
		};

		String [][] datosSimbolosPrueba={
			{"a","34","prueba"},
			{"g","23","prueba"},
			{"acumulador","0","prueba"}
		};
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
		botonArchivo = new JButton("Buscar Archivo");
		botonArchivo.setPreferredSize(new Dimension(130,30));

		mostrarRutaArchivo = new JTextField();
		mostrarRutaArchivo.setPreferredSize(new Dimension(350, 30));
		mostrarRutaArchivo.setEditable(false);
		mostrarRutaArchivo(rutaArchivo);   

		//Agregamos que el boton accione
		botonArchivo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e) {
				/*Codigo archivo */
				//rutaNueva = ruta del archivo que saca del codigo;
				rutaNueva = "C:/compilador/ejemplo2";
				mostrarRutaArchivo(rutaNueva);
				editarTabla(datosTokensPrueba, datosErroresPrueba, datosSimbolosPrueba);
			}
		});

		// Agregar elementos al panel Informacion
		panelInformacion.add(mostrarRutaArchivo);
		panelInformacion.add(botonArchivo);
	

		ventana.setLayout(new BorderLayout());    
		ventana.add(panelInformacion,BorderLayout.PAGE_START);
	}

  
	public void mostrarTabla( String[][] datos, String[][] datosErrores,  String[][] datosId){
		modeloTablaTokens.setDataVector(datos, encabezadoTokensFinal);
		modeloTablaErrores.setDataVector(datosErrores, encabezadoErroresFinal);
		modeloTablaSimbolos.setDataVector(datosId,encabezadoVariablesFinal);

		tablaTokens = new JTable();
		tablaTokens.setEnabled(false);
		tablaTokens.getTableHeader().setReorderingAllowed(false);
		tablaTokens.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tablaErrores = new JTable();
		tablaErrores.setEnabled(false);
		tablaErrores.getTableHeader().setReorderingAllowed(false);

		tablaSimbolos = new JTable();
		tablaSimbolos.setEnabled(false);
		tablaSimbolos.getTableHeader().setReorderingAllowed(false);
		tablaSimbolos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		panelTablaTokens = new JScrollPane(tablaTokens);
		panelTablaTokens.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"1. Tabla de Tokens",TitledBorder.CENTER,TitledBorder.TOP));
		panelTablaTokens.setPreferredSize(new Dimension(550,550));

		panelTablaSimbolos = new JScrollPane(tablaSimbolos);
		panelTablaSimbolos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"2. Tabla de símbolos",TitledBorder.CENTER,TitledBorder.TOP));
		panelTablaSimbolos.setPreferredSize(new Dimension(550,550));

		panelTablaErrores = new JScrollPane(tablaErrores);
		panelTablaErrores.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"3. Tabla de Errores",TitledBorder.CENTER,TitledBorder.TOP));
		panelTablaErrores.setPreferredSize(new Dimension(350,550));

		tablaTokens.setModel(modeloTablaTokens);
		tablaSimbolos.setModel(modeloTablaSimbolos);
		tablaErrores.setModel(modeloTablaErrores);

		//Hacemos visible la tabla
		ventana.add(panelTablaTokens,BorderLayout.LINE_START);
		ventana.add(panelTablaSimbolos,BorderLayout.CENTER);
		ventana.add(panelTablaErrores,BorderLayout.LINE_END);
	}

  	public void mostrarRutaArchivo(String rutaArchvo){
        mostrarRutaArchivo.setText(rutaArchvo);
    }

	public void editarTabla(String[][] datosCambio, String[][] datosErroresCambio,  String[][] datosSimbolosCambio){
		//Para efectos de las pruebas solo le puse el "(prueba)" para ver si cambiaba pero como seran las mismas columnas cambiarlo
		modeloTablaTokens.setDataVector(datosCambio,encabezadoErroresFinal);
		modeloTablaErrores.setDataVector(datosErroresCambio,encabezadoErroresFinal);
		modeloTablaSimbolos.setDataVector(datosSimbolosCambio,encabezadoVariablesFinal);

		modeloTablaTokens.fireTableDataChanged();
		modeloTablaErrores.fireTableDataChanged();
		modeloTablaSimbolos.fireTableDataChanged();
	}
}
