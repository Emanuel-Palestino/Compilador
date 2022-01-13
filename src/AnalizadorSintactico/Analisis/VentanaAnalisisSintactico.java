package AnalizadorSintactico.Analisis;

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
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import AnalizadorSintactico.ConstruccionTablaLR.TablaLR;
import Utilidades.Archivo;
import Utilidades.ResultadoAnalisisSintactico;
import Utilidades.Gramatica.Gramatica;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

public class VentanaAnalisisSintactico extends JDialog {
	private JScrollPane panelPrincipal, scrollPanelTabla, panelAccion, panelIrA;
	private FlowLayout diseñoPanel;
	private JPanel panelContenido, panelInformacion, panelArchivo, panelResultado, panelTabla;
	private JLabel lblSimbolosNoTerminales, lblSimbolosTerminales, lblSimboloInicial, lblGramatica,
			lblTablaLR, lblArchivoGramatica, lblArchivoPrograma, lblTablaAnalisis;
	private JTextField textNoTerminales, textTerminales, textSimboloInicial, textRutaArchivoGramatica,
			textRutaArchivoPrograma;
	private JTextArea areaGramatica;
	private JTable tablaAnalisisSintactico, tablaAccion, tablaIrA;
	private DefaultTableModel modeloTabla = new DefaultTableModel();
	private DefaultTableModel modeloAccion = new DefaultTableModel();
	private DefaultTableModel modeloIrA = new DefaultTableModel();
	private JButton botonBuscarGramatica, botonBuscarPrograma;
	private final JDialog estaVentana = this;
	private final int altoElementos = 30;
	private final Border padding = BorderFactory.createEmptyBorder(5, 5, 5, 5);
	private final Border paddingTextArea = BorderFactory.createEmptyBorder(10, 10, 10, 10);
	private final String[] encabezadoTabla = { "Pila", "Entrada", "Accion" };

	// Constructor de la ventana
	public VentanaAnalisisSintactico(JFrame parent, String noTerminales, String terminales, String simboloInicial,
			String gramatica, TablaLR tablaLR, ArrayList<String> simbolosTerminales,
			ArrayList<String> simbolosNoTerminales, ResultadoAnalisisSintactico analisisSintactico) {
		super(parent, true);

		// Iniciar componentes
		inicializarComponentes();

		// Rellenar con informacion dada
		rellenarComponentes(noTerminales, terminales, simboloInicial, gramatica);
		rellenarTablaLR(tablaLR, simbolosTerminales, simbolosNoTerminales);

		this.setVisible(true);
	}

	// Constructor Vacio
	public VentanaAnalisisSintactico() {
		super();

		// Iniciar Componentes
		inicializarComponentes();

		this.setVisible(true);
	}

	private void inicializarComponentes() {
		// Propiedades de la ventana
		diseñoPanel = new FlowLayout(FlowLayout.LEFT, 10, 10);
		this.setSize(1002, 760);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Analizador Sintáctico - Análisis Sintactico");

		// panel
		panelContenido = new JPanel();
		panelContenido.setLayout(diseñoPanel);
		panelContenido.setPreferredSize(new Dimension(800, 1200));

		/** Mostrar ruta dle archivo y boton para cargar otro archivo */

		// Propiedades del panel de Archivo
		panelArchivo = new JPanel();
		panelArchivo.setPreferredSize(new Dimension(900, 90));
		panelArchivo.setLayout(diseñoPanel);

		// Mostrar la ruta del archivo de Gramatica
		lblArchivoGramatica = new JLabel("Gramática:");
		lblArchivoGramatica.setPreferredSize(new Dimension(80, altoElementos));

		textRutaArchivoGramatica = new JTextField("src/ArchivosExtra/gramaticaJS1.txt");
		textRutaArchivoGramatica.setPreferredSize(new Dimension(600, altoElementos));
		textRutaArchivoGramatica.setEditable(false);
		textRutaArchivoGramatica
				.setBorder(BorderFactory.createCompoundBorder(textRutaArchivoGramatica.getBorder(), padding));

		// Boton para buscar otro archivo
		botonBuscarGramatica = new JButton("Buscar Archivo Gramatica");
		botonBuscarGramatica.setPreferredSize(new Dimension(160, altoElementos));

		// Agregar Accion al boton
		botonBuscarGramatica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Obtener nueva ruta dle archivo
				String ruta = Archivo.obtenerRutaArchivo(estaVentana);

				editarRutaArchivo(ruta);

				// Obtener datos nuevos
				Gramatica grama = new Gramatica(ruta);

				// Modificar datos
				rellenarComponentes(grama.stringSimbolosNoTerminales(), grama.stringSimbolosTerminales(),
						grama.getSimboloInicial(), grama.stringGramatica());

			}
		});

		// Agregar elementos al panel Archivo
		panelArchivo.add(lblArchivoGramatica);
		panelArchivo.add(textRutaArchivoGramatica);
		panelArchivo.add(botonBuscarGramatica);

		// Mostrar la ruta del archivo del Programa
		lblArchivoPrograma = new JLabel("Programa:");
		lblArchivoPrograma.setPreferredSize(new Dimension(80, altoElementos));

		textRutaArchivoPrograma = new JTextField("src/ArchivosExtra/programaGramatica1.js");
		textRutaArchivoPrograma.setPreferredSize(new Dimension(600, altoElementos));
		textRutaArchivoPrograma.setEditable(false);
		textRutaArchivoPrograma
				.setBorder(BorderFactory.createCompoundBorder(textRutaArchivoPrograma.getBorder(), padding));

		// Boton para buscar otro archivo
		botonBuscarPrograma = new JButton("Buscar Archivo Programa");
		botonBuscarPrograma.setPreferredSize(new Dimension(160, altoElementos));

		// Agregar Accion al boton
		botonBuscarPrograma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Obtener nueva ruta dle archivo
				String ruta = Archivo.obtenerRutaArchivo(estaVentana);

				editarRutaArchivo(ruta);

				// Obtener datos nuevos

				// Modificar datos

			}
		});

		// Agregar elementos al panel Archivo
		panelArchivo.add(lblArchivoPrograma);
		panelArchivo.add(textRutaArchivoPrograma);
		panelArchivo.add(botonBuscarPrograma);

		/** Mostrar Simbolos de la gramatica */

		// Propiedades del panel Informacion
		panelInformacion = new JPanel();
		panelInformacion.setPreferredSize(new Dimension(500, 128));
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
		panelResultado.setPreferredSize(new Dimension(960, 470));
		panelResultado.setLayout(diseñoPanel);
		Font fuenteResultado = new Font("Arial", Font.PLAIN, 16);

		// Mostrar Etiqueta Gramatica
		lblGramatica = new JLabel("Gramática");
		lblGramatica.setPreferredSize(new Dimension(237, altoElementos));

		// Mostrar Etiqueta Coleccion Canonica
		lblTablaLR = new JLabel("Tabla LR");
		lblTablaLR.setPreferredSize(new Dimension(700, altoElementos));

		// Mostrar Contenido de la Gramatica
		areaGramatica = new JTextArea();
		areaGramatica.setPreferredSize(new Dimension(237, 470 - altoElementos - 20));
		areaGramatica.setEditable(false);
		areaGramatica.setFont(fuenteResultado);
		areaGramatica.setBorder(BorderFactory.createCompoundBorder(areaGramatica.getBorder(), paddingTextArea));

		// Mostrar Tabla Accion
		tablaAccion = new JTable(modeloAccion);
		tablaAccion.setEnabled(false);
		tablaAccion.getTableHeader().setReorderingAllowed(false);

		panelAccion = new JScrollPane(tablaAccion);
		panelAccion.setPreferredSize(new Dimension(390, 430 - altoElementos - 20));
		panelAccion.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				"Accion", TitledBorder.CENTER, TitledBorder.TOP));
		// areaColeccionCanonica.setPreferredSize(new Dimension(700, 430 - altoElementos
		// - 20));

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

		/* Tabla de Análisis */
		panelTabla = new JPanel();
		panelTabla.setPreferredSize(new Dimension(960, 500));
		panelTabla.setLayout(diseñoPanel);

		// Etiqueta tabla de analisis
		lblTablaAnalisis = new JLabel("Tabla de Analisis");
		lblTablaAnalisis.setPreferredSize(new Dimension(900, altoElementos));

		String[][] datos = { { "0 8 3 5 6 g  h ", "D F D E F D F S D F D S F", "Aceptado sdfsdfsdf" },
				{ "1", "main", "main" }, { "1", "(", "(" } };
		modeloTabla.setDataVector(datos, encabezadoTabla);
		tablaAnalisisSintactico = new JTable(modeloTabla);
		tablaAnalisisSintactico.setEnabled(false);
		tablaAnalisisSintactico.getTableHeader().setReorderingAllowed(false);
		tablaAnalisisSintactico.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		scrollPanelTabla = new JScrollPane(tablaAnalisisSintactico);
		scrollPanelTabla.setPreferredSize(new Dimension(900, 420));

		panelTabla.add(lblTablaAnalisis);
		panelTabla.add(scrollPanelTabla);

		// Agregar componente al Panel Contenido
		panelContenido.add(panelArchivo);
		panelContenido.add(panelInformacion);
		panelContenido.add(panelResultado);
		panelContenido.add(panelTabla);

		// Scroll panel
		panelPrincipal = new JScrollPane();
		panelPrincipal.setViewportView(panelContenido);
		this.add(panelPrincipal);
	}

	private void rellenarComponentes(String noTerminales, String terminales, String simboloInicial, String gramatica) {
		textNoTerminales.setText(noTerminales);
		textTerminales.setText(terminales);
		textSimboloInicial.setText(simboloInicial);
		areaGramatica.setText(gramatica);
	}

	private void editarRutaArchivo(String nuevaRuta) {
		textRutaArchivoGramatica.setText(nuevaRuta);
	}

	private void rellenarTablaLR(TablaLR tabla, ArrayList<String> simbolosTerminales,
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
		modeloIrA.setDataVector(datosIrA, encabezadoIrA);
		modeloIrA.fireTableDataChanged();
	}

	private void rellenarTablaAnalisis(ResultadoAnalisisSintactico resultado) {

		// Acciones
		/* String[][] datos = new String[acciones.size()][simbolosTerminales.size() + 2];
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
		} */


		//modeloTabla.setDataVector(datos, encabezadoTabla);
		modeloTabla.fireTableDataChanged();
	}

}