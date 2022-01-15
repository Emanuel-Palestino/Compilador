package AnalizadorLexico.AlgoritmoThompson;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.*;
import java.awt.*;

public class VentanaThompson extends JDialog {

	private FlowLayout diseñoPanel;
	private JPanel panelInformacion;
	private JScrollPane panelTabla;
	private JLabel lblAlfabeto, lblExpresionRegular;
	private JTextField textAlfabeto, textExpresionRegular, mostrarArchivo;
	private JTable transiciones;
	private JButton boton;
	private final int altoInputs = 30;
	private String prueba = "C:/Descargas/AnalizadorLexico/archivo.txt";

	public VentanaThompson(JFrame parent, boolean modal, String alfabeto, String expresion, String[] encabezado,
			String[][] datos) {
		super(parent, modal);

		// Iniciar componentes que se muestran en la ventana
		inicializarInformacion();
		rellenarInformacion(alfabeto, expresion);
		mostrarTabla(encabezado, datos);
		this.setVisible(true);
	}

	private void inicializarInformacion() {
		// Propiedades de la this
		diseñoPanel = new FlowLayout(FlowLayout.LEFT, 10, 10);
		this.setSize(1000, 680);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(diseñoPanel);

		this.setTitle("Algoritmo de Thompson");

		// Mostrar Alfabeto y Expresión regular
		panelInformacion = new JPanel();
		panelInformacion.setPreferredSize(new Dimension(900, 150));
		panelInformacion.setLayout(diseñoPanel);

		lblAlfabeto = new JLabel("Alfabeto:");
		lblAlfabeto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAlfabeto.setPreferredSize(new Dimension(200, altoInputs));

		textAlfabeto = new JTextField();
		textAlfabeto.setPreferredSize(new Dimension(600, altoInputs));
		textAlfabeto.setEditable(false);

		lblExpresionRegular = new JLabel("Expresión Regular:");
		lblExpresionRegular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblExpresionRegular.setPreferredSize(new Dimension(200, altoInputs));

		textExpresionRegular = new JTextField();
		textExpresionRegular.setPreferredSize(new Dimension(600, altoInputs));
		textExpresionRegular.setEditable(false);

		// agregamos boton
		boton = new JButton("Buscar Archivo");
		boton.setPreferredSize(new Dimension(200, altoInputs));

		mostrarArchivo = new JTextField();
		mostrarArchivo.setPreferredSize(new Dimension(350, altoInputs));
		mostrarArchivo.setEditable(false);
		mostrarRutaArchivo(mostrarArchivo, prueba);

		// Agregamos que el boton accione
		boton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/* Codigo para buscar archivo */
			}
		});

		// Agregar elementos al panel Informacion
		panelInformacion.add(lblAlfabeto);
		panelInformacion.add(textAlfabeto);
		panelInformacion.add(lblExpresionRegular);
		panelInformacion.add(textExpresionRegular);
		panelInformacion.add(boton);

		// Integrar elementos a la this y mostrar
		this.add(panelInformacion);
	}

	public void mostrarTabla(String[] encabezado, String[][] datos) {
		DefaultTableModel modeloTabla = new DefaultTableModel(datos, encabezado);
		transiciones = new JTable(modeloTabla);
		transiciones.setEnabled(false);
		transiciones.getTableHeader().setReorderingAllowed(false);
		transiciones.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		transiciones.setFont(new Font("Tahoma", Font.PLAIN, 12));
		transiciones.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 14));
		transiciones.setShowVerticalLines(false);
		transiciones.setRowHeight(30);
		int columnas = transiciones.getColumnModel().getColumnCount();
		DefaultTableCellRenderer celdaCentro = new DefaultTableCellRenderer();
		celdaCentro.setHorizontalAlignment(SwingConstants.CENTER);
		((DefaultTableCellRenderer) transiciones.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < columnas; i++) {
			transiciones.getColumnModel().getColumn(i).setCellRenderer(celdaCentro);
		}

		panelTabla = new JScrollPane(transiciones);
		panelTabla.setPreferredSize(new Dimension(960, 410));

		this.add(panelTabla);
	}

	public void rellenarInformacion(String alfabeto, String expresion) {
		textAlfabeto.setText(alfabeto);
		textExpresionRegular.setText(expresion);
	}

	public void mostrarRutaArchivo(JTextField mostrarText, String rutaArchvo) {
		mostrarText.setText(rutaArchvo);
	}

}
