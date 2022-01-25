package AnalizadorSemantico;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Utilidades.Archivo;
import Utilidades.Tabla;
import Utilidades.Excepciones.ExcepcionER;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VentanaAnalisisSemantico extends JDialog {

	private FlowLayout diseñoPanel;
	private JPanel panelArchivo, panelResultado;
	private JLabel lblResultadoAL, lblArchivoPrograma, lblAnalisisSintactico, lblRelleno;
	private JTextField textRutaArchivoPrograma;
	private JButton botonBuscarPrograma, botonResultadoAL;
	private Tabla tablaAnalisis;
	private final JDialog estaVentana = this;
	private final int altoElementos = 30;
	private final Border padding = BorderFactory.createEmptyBorder(5, 5, 5, 5);
	private final String[] encabezadoTabla = { "Pila", "Entrada", "Accion" };
	private ServicioAnalisisSemantico servicioAnalisis;

	// Constructor de la ventana
	public VentanaAnalisisSemantico(JFrame parent) {
		super(parent, true);

		// Iniciar componentes
		servicioAnalisis = new ServicioAnalisisSemantico();
		try {
			servicioAnalisis.ejecutar("src/ArchivosExtra/RecursosGramaticasClase/programa1G1.txt");
		} catch (IOException | ExcepcionER e) {
			e.printStackTrace();
		}

		inicializarComponentes();

		this.setVisible(true);
	}

	// Constructor Vacio
	public VentanaAnalisisSemantico() {
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
				try {
					servicioAnalisis.ejecutar(ruta);
					// Modificar datos
					tablaAnalisis.actualizarDatos(encabezadoTabla,
							servicioAnalisis.gerResultadoSemantico().getDatosTabla());

				} catch (IOException | ExcepcionER e1) {
					e1.printStackTrace();
				}
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

		tablaAnalisis = new Tabla(940, 450, encabezadoTabla, servicioAnalisis.gerResultadoSemantico().getDatosTabla());

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

	private void editarRutaArchivo(String nuevaRuta) {
		textRutaArchivoPrograma.setText(nuevaRuta);
	}

}
