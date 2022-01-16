package AnalizadorSintactico.Analisis;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import AnalizadorSintactico.ConstruccionTablaLR.TablaLR;
import Utilidades.Archivo;
import Utilidades.ResultadoAnalisisSintactico;
import Utilidades.Tabla;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

public class VentanaAnalisisSintactico extends JDialog {
	private FlowLayout diseñoPanel;
	private JPanel panelArchivo, panelResultado;
	private JLabel lblResultadoAL, lblArchivoPrograma, lblAnalisisSintactico, lblRelleno;
	private JTextField textRutaArchivoPrograma;
	private DefaultTableModel modeloTabla = new DefaultTableModel();
	private DefaultTableModel modeloAccion = new DefaultTableModel();
	private DefaultTableModel modeloIrA = new DefaultTableModel();
	private JButton botonBuscarPrograma, botonResultadoAL;
	private Tabla tablaAnalisis;
	private final JDialog estaVentana = this;
	private final int altoElementos = 30;
	private final Border padding = BorderFactory.createEmptyBorder(5, 5, 5, 5);
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
		//rellenarTablaLR(tablaLR, simbolosTerminales, simbolosNoTerminales);

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
		this.setSize(1002, 680);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Analizador Sintáctico - Análisis Sintáctico");
		this.setLayout(diseñoPanel);

		/** Mostrar ruta del archivo y boton para cargar otro archivo */

		// Propiedades del panel de Archivo
		panelArchivo = new JPanel();
		panelArchivo.setPreferredSize(new Dimension(900, 60));
		panelArchivo.setLayout(diseñoPanel);

		// Mostrar la ruta del archivo del Programa
		lblArchivoPrograma = new JLabel("Programa:");
		lblArchivoPrograma.setPreferredSize(new Dimension(80, altoElementos));

		textRutaArchivoPrograma = new JTextField("src/ArchivosExtra/programaGramatica1.js");
		textRutaArchivoPrograma.setPreferredSize(new Dimension(600, altoElementos));
		textRutaArchivoPrograma.setEditable(false);
		textRutaArchivoPrograma
				.setBorder(BorderFactory.createCompoundBorder(textRutaArchivoPrograma.getBorder(), padding));

		// Boton para buscar otro archivo
		botonBuscarPrograma = new JButton("Buscar Archivo de Programa");
		botonBuscarPrograma.setPreferredSize(new Dimension(180, altoElementos));

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


		/** Panel de Resultados */

		// Propiedades del panel de Resultado
		panelResultado = new JPanel();
		panelResultado.setPreferredSize(new Dimension(960, 550));
		panelResultado.setLayout(diseñoPanel);

		lblResultadoAL = new JLabel("Resultado del Análisis Léxico:");
		lblResultadoAL.setPreferredSize(new Dimension(220, altoElementos));

		botonResultadoAL = new JButton("Ver Resultado");
		botonResultadoAL.setPreferredSize(new Dimension(100, altoElementos));

		lblRelleno = new JLabel();
		lblRelleno.setPreferredSize(new Dimension(500, 0));


		// Tabla del analisis sintactico
		lblAnalisisSintactico = new JLabel("Resultado del Análisis Sintáctico:");
		lblAnalisisSintactico.setPreferredSize(new Dimension(220, altoElementos));

		tablaAnalisis = new Tabla(940, 450);


		// Agregar elementos al panel resultado
		panelResultado.add(lblResultadoAL);
		panelResultado.add(botonResultadoAL);
		panelResultado.add(lblRelleno);
		panelResultado.add(lblAnalisisSintactico);
		panelResultado.add(tablaAnalisis);


		// Agregar componente al Panel Contenido
		this.add(panelArchivo);
		this.add(panelResultado);

	}

	private void rellenarComponentes(String noTerminales, String terminales, String simboloInicial, String gramatica) {
	}

	private void editarRutaArchivo(String nuevaRuta) {
		textRutaArchivoPrograma.setText(nuevaRuta);
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