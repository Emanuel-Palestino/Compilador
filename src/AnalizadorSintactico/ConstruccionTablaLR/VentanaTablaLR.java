package AnalizadorSintactico.ConstruccionTablaLR;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Utilidades.Archivo;
import Utilidades.ColeccionCanonica;
import Utilidades.Gramatica.Gramatica;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

public class VentanaTablaLR extends JDialog {
	private FlowLayout diseñoPanel;
	private JPanel panelInformacion, panelArchivo, panelResultado;
	private JLabel lblSimbolosNoTerminales, lblSimbolosTerminales, lblSimboloInicial, lblGramatica,
			lblTablaLR;
	private JTextField textNoTerminales, textTerminales, textSimboloInicial, textRutaArchivo;
	private JTextArea areaGramatica;
	private JButton botonBuscar;
	private final JDialog estaVentana = this;
	private final int altoElementos = 30;
	private final Border padding = BorderFactory.createEmptyBorder(5, 5, 5, 5);
	private final Border paddingTextArea = BorderFactory.createEmptyBorder(10, 10, 10, 10);
	private JScrollPane panelAccion, panelIrA;
	private JTable tablaAccion, tablaIrA;
	private DefaultTableModel modeloAccion = new DefaultTableModel();
	private DefaultTableModel modeloIrA = new DefaultTableModel();

	// Constructor de la ventana
	public VentanaTablaLR(JFrame parent, String noTerminales, String terminales, String simboloInicial,
			String gramatica, TablaLR tabla, ArrayList<String> simbolosTerminales,
			ArrayList<String> simbolosNoTerminales) {
		super(parent, true);

		// Iniciar componentes
		inicializarComponentes();

		// Rellenar con informacion dada
		rellenarComponentes(noTerminales, terminales, simboloInicial, gramatica, tabla, simbolosTerminales,
				simbolosNoTerminales);

		this.setVisible(true);
	}

	// Constructor Vacio
	public VentanaTablaLR(JFrame parent) {
		super(parent, true);

		// Iniciar Componentes
		inicializarComponentes();
		rellenarTabla(null, null, null);

		this.setVisible(true);
	}

	private void inicializarComponentes() {
		// Propiedades de la ventana
		diseñoPanel = new FlowLayout(FlowLayout.LEFT, 10, 10);
		this.setSize(1000, 700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(diseñoPanel);
		this.setTitle("Analizador Léxico - Colección Canonica");

		/** Mostrar ruta dle archivo y boton para cargar otro archivo */

		// Propiedades del panel de Archivo
		panelArchivo = new JPanel();
		panelArchivo.setPreferredSize(new Dimension(800, 60));
		panelArchivo.setLayout(diseñoPanel);

		// Mostrar la ruta del archivo
		textRutaArchivo = new JTextField("src/ArchivosExtra/gramatica.txt");
		textRutaArchivo.setPreferredSize(new Dimension(600, altoElementos));
		textRutaArchivo.setEditable(false);
		textRutaArchivo.setBorder(BorderFactory.createCompoundBorder(textRutaArchivo.getBorder(), padding));

		// Boton para buscar otro archivo
		botonBuscar = new JButton("Buscar Archivo");
		botonBuscar.setPreferredSize(new Dimension(130, altoElementos));

		// Agregar Accion al boton
		botonBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Obtener nueva ruta dle archivo
				String ruta = Archivo.obtenerRutaArchivo(estaVentana);

				editarRutaArchivo(ruta);

				// Obtener datos nuevos
				Gramatica grama = new Gramatica(ruta);
				ColeccionCanonica coleccionCanonica = ColeccionCanonica.hacer(grama);
				TablaLR resultado = TablaLR.construir(coleccionCanonica, grama);

				// Modificar datos
				rellenarComponentes(grama.stringSimbolosNoTerminales(), grama.stringSimbolosTerminales(),
						grama.getSimboloInicial(), grama.stringGramatica(), resultado, grama.getTerminales(),
						grama.getNoTerminales());

			}
		});

		// Agregar elementos al panel Archivo
		panelArchivo.add(textRutaArchivo);
		panelArchivo.add(botonBuscar);

		/** Mostrar Simbolos de la gramatica */

		// Propiedades del panel Informacion
		panelInformacion = new JPanel();
		panelInformacion.setPreferredSize(new Dimension(500, 130));
		panelInformacion.setLayout(diseñoPanel);

		// Mostrar los simbolos no terminales
		lblSimbolosNoTerminales = new JLabel("Símbolos no Terminales");
		lblSimbolosNoTerminales.setPreferredSize(new Dimension(120, altoElementos));

		textNoTerminales = new JTextField();
		textNoTerminales.setPreferredSize(new Dimension(300, altoElementos));
		textNoTerminales.setEditable(false);
		textNoTerminales.setBorder(BorderFactory.createCompoundBorder(textNoTerminales.getBorder(), padding));

		// Mostrar los simbolos terminales
		lblSimbolosTerminales = new JLabel("Símbolos Terminales: ");
		lblSimbolosTerminales.setPreferredSize(new Dimension(120, altoElementos));

		textTerminales = new JTextField();
		textTerminales.setPreferredSize(new Dimension(300, altoElementos));
		textTerminales.setEditable(false);
		textTerminales.setBorder(BorderFactory.createCompoundBorder(textTerminales.getBorder(), padding));

		// Mostrar Simbolo Inicial
		lblSimboloInicial = new JLabel("Símbolo Inicial:");
		lblSimboloInicial.setPreferredSize(new Dimension(120, altoElementos));

		textSimboloInicial = new JTextField();
		textSimboloInicial.setPreferredSize(new Dimension(300, altoElementos));
		textSimboloInicial.setEditable(false);
		textSimboloInicial.setBorder(BorderFactory.createCompoundBorder(textSimboloInicial.getBorder(), padding));

		// Agregar elementos al panel Informacion
		panelInformacion.add(lblSimbolosNoTerminales);
		panelInformacion.add(textNoTerminales);
		panelInformacion.add(lblSimbolosTerminales);
		panelInformacion.add(textTerminales);
		panelInformacion.add(lblSimboloInicial);
		panelInformacion.add(textSimboloInicial);

		/** Panel de Resultados */

		// Propiedades del panel de Resultado
		panelResultado = new JPanel();
		panelResultado.setPreferredSize(new Dimension(960, 430));
		panelResultado.setLayout(diseñoPanel);
		Font fuenteResultado = new Font("Arial", Font.PLAIN, 16);

		// Mostrar Etiqueta Gramatica
		lblGramatica = new JLabel("Gramática");
		lblGramatica.setPreferredSize(new Dimension(237, altoElementos));

		// Mostrar Etiqueta para la Tabla LR
		lblTablaLR = new JLabel("Tabla LR");
		lblTablaLR.setPreferredSize(new Dimension(700, altoElementos));

		// Mostrar Contenido de la Gramatica
		areaGramatica = new JTextArea();
		areaGramatica.setPreferredSize(new Dimension(237, 430 - altoElementos - 20));
		areaGramatica.setEditable(false);
		areaGramatica.setFont(fuenteResultado);
		areaGramatica.setBorder(BorderFactory.createCompoundBorder(areaGramatica.getBorder(), paddingTextArea));

		// Mostrar Tabla Accion
		tablaAccion = new JTable(modeloAccion);
		tablaAccion.setEnabled(false);
		tablaAccion.getTableHeader().setReorderingAllowed(false);
		tablaAccion.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaAccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tablaAccion.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		tablaAccion.setShowVerticalLines(false);
		tablaAccion.setRowHeight(30);
		int columnas = tablaAccion.getColumnModel().getColumnCount();
		DefaultTableCellRenderer celdaCentro = new DefaultTableCellRenderer();
		celdaCentro.setHorizontalAlignment(SwingConstants.CENTER);
		((DefaultTableCellRenderer) tablaAccion.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < columnas; i++) {
			tablaAccion.getColumnModel().getColumn(i).setCellRenderer(celdaCentro);
		}

		panelAccion = new JScrollPane(tablaAccion);
		panelAccion.setPreferredSize(new Dimension(390, 430 - altoElementos - 20));
		panelAccion.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				"Accion", TitledBorder.CENTER, TitledBorder.TOP));

		// Mostrar Tabla IrA
		tablaIrA = new JTable(modeloIrA);
		tablaIrA.setEnabled(false);
		tablaIrA.getTableHeader().setReorderingAllowed(false);

		panelIrA = new JScrollPane(tablaIrA);
		panelIrA.setPreferredSize(new Dimension(300, 430 - altoElementos - 20));
		panelIrA.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				"Ir A", TitledBorder.CENTER, TitledBorder.TOP));

		// Agregar elementos al panel resultado
		panelResultado.add(lblGramatica);
		panelResultado.add(lblTablaLR);
		panelResultado.add(areaGramatica);
		panelResultado.add(panelAccion);
		panelResultado.add(panelIrA);

		// Agregar componente a la Ventana
		this.add(panelArchivo);
		this.add(panelInformacion);
		this.add(panelResultado);
	}

	private void rellenarTabla(TablaLR tabla, ArrayList<String> simbolosTerminales,
			ArrayList<String> simbolosNoTerminales) {
		ArrayList<Map<String, String>> acciones = tabla.getAcciones();
		ArrayList<Map<String, String>> irA = tabla.getIrA();

		// Acciones
		String[][] datosAcciones = new String[acciones.size()][simbolosTerminales.size() + 2];
		String[] encabezadoAcciones = new String[simbolosTerminales.size() + 2];
		encabezadoAcciones[0] = "Estado";
		for (int i = 0; i < acciones.size(); i++) {
			datosAcciones[i][0] = "" + i;
			for (int j = 0; j < simbolosTerminales.size(); j++) {
				encabezadoAcciones[j + 1] = simbolosTerminales.get(j);
				datosAcciones[i][j + 1] = acciones.get(i).get(simbolosTerminales.get(j));
			}
			encabezadoAcciones[encabezadoAcciones.length - 1] = "$";
			datosAcciones[i][datosAcciones[0].length - 1] = acciones.get(i).get("$");
		}

		// Ir A
		String[][] datosIrA = new String[irA.size()][simbolosNoTerminales.size() + 1];
		String[] encabezadoIrA = new String[simbolosNoTerminales.size() + 1];
		encabezadoIrA[0] = "Estado";
		for (int i = 0; i < irA.size(); i++) {
			datosIrA[i][0] = "" + i;
			for (int j = 0; j < simbolosNoTerminales.size(); j++) {
				encabezadoIrA[j + 1] = simbolosNoTerminales.get(j);
				datosIrA[i][j + 1] = irA.get(i).get(simbolosNoTerminales.get(j));
			}
		}

		modeloAccion.setDataVector(datosAcciones, encabezadoAcciones);
		modeloAccion.fireTableDataChanged();
		int columnas = tablaAccion.getColumnModel().getColumnCount();
		DefaultTableCellRenderer celdaCentro = new DefaultTableCellRenderer();
		celdaCentro.setHorizontalAlignment(SwingConstants.CENTER);
		((DefaultTableCellRenderer) tablaAccion.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < columnas; i++) {
			tablaAccion.getColumnModel().getColumn(i).setCellRenderer(celdaCentro);
		}
		modeloIrA.setDataVector(datosIrA, encabezadoIrA);
		modeloIrA.fireTableDataChanged();
	}

	private void rellenarComponentes(String noTerminales, String terminales, String simboloInicial, String gramatica,
			TablaLR tabla, ArrayList<String> simbolosTerminales, ArrayList<String> simbolosNoTerminales) {
		textNoTerminales.setText(noTerminales);
		textTerminales.setText(terminales);
		textSimboloInicial.setText(simboloInicial);
		areaGramatica.setText(gramatica);
		// Rellenar la tabla LR
		rellenarTabla(tabla, simbolosTerminales, simbolosNoTerminales);
	}

	private void editarRutaArchivo(String nuevaRuta) {
		textRutaArchivo.setText(nuevaRuta);
	}

}