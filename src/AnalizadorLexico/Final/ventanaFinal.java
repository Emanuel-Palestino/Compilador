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

public class ventanaFinal extends JDialog{
	JDialog ventana;
    FlowLayout diseñoPanel;
    JPanel panelInformacion;
    JScrollPane panelTabla,panelErrores,panelId;
	JLabel lblAlfabeto,lblExpresionRegular;
	JTextField textAlfabeto,textExpresionRegular;
	JTable transiciones,tablaErrores,tablaId;
    JButton boton;

    public ventanaFinal(JFrame parent, boolean modal, String alfabeto, String expresion, String[] encabezado, String[][] datos, String [] encabezadoErr, String[][] datosErrores, String [] encabezadoId, String [][] datosId) {
		super(parent, modal);
                
		// Iniciar componentes que se muestran en la ventana
		inicializarInformacion();
		rellenarInformacion(alfabeto, expresion);
		mostrarTabla(encabezado, datos,encabezadoErr,datosErrores,encabezadoId,datosId); 
		ventana.setVisible(true);
	}

	public ventanaFinal(JFrame parent, boolean modal){
		super(parent,modal); 
		String [] encabezado = {"Encabezado prueba"};
		String [][] datos ={
			{"Juan", "25"},
            {"Sonia", "33"},
            {"Pedro", "42"}
		};

		String [] encabezadoErr = {"Errores","Valor","ejemplo"};
		String [][] datosErrores={
			{"a","2","33"},
			{"b","3","32"}
		};

		String [] encabezadoId = {"Id Prueba","Valor","ejemplo"};
		String [][] datosId={
			{"c","4","32"},
			{"d","6","33"}
		};

		//Iniciar componentes
		inicializarInformacion();
		//rellenarInformacion(alfabeto, expresion);
		mostrarTabla(encabezado, datos,encabezadoErr,datosErrores,encabezadoId,datosId);
		ventana.setVisible(true);
	}

	private void inicializarInformacion(){
		diseñoPanel = new FlowLayout(FlowLayout.LEFT,10,10);
		ventana = new JDialog();
		ventana.setSize(1150,940);
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false);
		ventana.setLayout(diseñoPanel);

		ventana.setTitle("Analizador Lexico");

		panelInformacion = new JPanel();
		panelInformacion.setPreferredSize(new Dimension(500,200));
		panelInformacion.setLayout(diseñoPanel);
		
		lblAlfabeto = new JLabel("Alfabeto: ");
        lblAlfabeto.setPreferredSize(new Dimension(200, 30));

		textAlfabeto = new JTextField();
		textAlfabeto.setPreferredSize(new Dimension(200, 30));
		textAlfabeto.setEditable(false);

		lblExpresionRegular = new JLabel("Expresión Regular:");
		lblExpresionRegular.setPreferredSize(new Dimension(200, 30));

		textExpresionRegular = new JTextField();
		textExpresionRegular.setPreferredSize(new Dimension(200, 30));
		textExpresionRegular.setEditable(false);

        //agregamos boton
        boton = new JButton("Buscar Archivo");
        boton.setPreferredSize(new Dimension(200,30));

		//Agregamos que el boton accione
		boton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e) {
				/*Codigo para buscar archivo*/
			}
		});

		// Agregar elementos al panel Informacion
		panelInformacion.add(lblAlfabeto);
		panelInformacion.add(textAlfabeto);
		panelInformacion.add(lblExpresionRegular);
		panelInformacion.add(textExpresionRegular);
        panelInformacion.add(boton);

		ventana.add(panelInformacion);

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
		tablaErrores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tablaId = new JTable(modeloId);
		tablaId.setEnabled(false);
		tablaId.getTableHeader().setReorderingAllowed(false);
		tablaId.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		panelTabla = new JScrollPane(transiciones);
		panelTabla.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"1. Tabla de Tokens",TitledBorder.CENTER,TitledBorder.TOP));
		panelTabla.setPreferredSize(new Dimension(661,650));

		panelId = new JScrollPane(tablaId);
		panelId.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"2. Tabla de símbolos",TitledBorder.CENTER,TitledBorder.TOP));
		panelId.setPreferredSize(new Dimension(220,650));

		panelErrores = new JScrollPane(tablaErrores);
		panelErrores.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),"3. Tabla de Errores",TitledBorder.CENTER,TitledBorder.TOP));
		panelErrores.setPreferredSize(new Dimension(220,650));

		
		
		//Hacemos visible la tabla
		ventana.add(panelTabla);
		ventana.add(panelErrores);
		ventana.add(panelId);
	}

	public void rellenarInformacion(String alfabeto, String expresion){
		textAlfabeto.setText(alfabeto);
		textExpresionRegular.setText(expresion);
    }

	
}
