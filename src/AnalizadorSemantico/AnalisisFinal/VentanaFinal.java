package AnalizadorSemantico.AnalisisFinal;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import AnalizadorSemantico.ServicioAnalisisSemantico;
import Utilidades.Archivo;
import Utilidades.Tabla;
import Utilidades.Excepciones.ExcepcionER;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class VentanaFinal extends JDialog {

	private FlowLayout diseñoPanel;
	private JPanel panelArchivo, panelResultado;
	private JLabel lblArchivoPrograma, lblAnalisisSintactico;
	private JTextField textRutaArchivoPrograma;
	private JButton botonBuscarPrograma, botonResultado, botonTablaErrores, botonTablaSimbolos;
	private Tabla tablaAnalisis;
	private final JDialog estaVentana = this;
	private final int altoElementos = 30;
	private final Border padding = BorderFactory.createEmptyBorder(5, 5, 5, 5);
	private final String[] encabezadoTabla = { "Pila", "Entrada", "Accion" };
	private ServicioAnalisisSemantico servicioAnalisis;

	// Constructor de la ventana
	public VentanaFinal(JFrame parent) {
		super(parent, true);

		// Iniciar componentes
		servicioAnalisis = new ServicioAnalisisSemantico();
		try {
			servicioAnalisis.actualizarGramatica("src/ArchivosExtra/AccionesSemanticasJS/accionesSemanticasFinal.txt");
			servicioAnalisis.ejecutar("src/ArchivosExtra/programas/programa1G1.js");
		} catch (IOException | ExcepcionER e) {
			e.printStackTrace();
		}

		inicializarComponentes();

		this.setVisible(true);
	}

	// Constructor Vacio
	public VentanaFinal() {
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
		this.setTitle("Analizador Semántico - Convertir JavaScript a PHP");
		this.setLayout(diseñoPanel);

		/** Mostrar ruta del archivo y boton para cargar otro archivo */

		// Propiedades del panel de Archivo
		panelArchivo = new JPanel();
		panelArchivo.setPreferredSize(new Dimension(960, 60));
		panelArchivo.setLayout(diseñoPanel);

		// Mostrar la ruta del archivo del Programa
		lblArchivoPrograma = new JLabel("Programa:");
		lblArchivoPrograma.setPreferredSize(new Dimension(80, altoElementos));

		textRutaArchivoPrograma = new JTextField("src/ArchivosExtra/RecursosGramaticasClase/programa1G1.txt");
		textRutaArchivoPrograma.setPreferredSize(new Dimension(630, altoElementos));
		textRutaArchivoPrograma.setEditable(false);
		textRutaArchivoPrograma
				.setBorder(BorderFactory.createCompoundBorder(textRutaArchivoPrograma.getBorder(), padding));

		// Boton para buscar otro archivo
		botonBuscarPrograma = new JButton("Buscar Archivo de Programa");
		botonBuscarPrograma.setPreferredSize(new Dimension(200, altoElementos));

		// Agregar Accion al boton
		botonBuscarPrograma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Obtener nueva ruta dle archivo
				String ruta = Archivo.obtenerRutaArchivo(estaVentana);
				System.out.println(ruta);

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

		panelArchivo.add(lblArchivoPrograma);
		panelArchivo.add(textRutaArchivoPrograma);
		panelArchivo.add(botonBuscarPrograma);


		/** Panel de Resultados */

		// Propiedades del panel de Resultado
		panelResultado = new JPanel();
		panelResultado.setPreferredSize(new Dimension(960, 610));
		panelResultado.setLayout(diseñoPanel);

		// Tabla del analisis sintactico
		lblAnalisisSintactico = new JLabel("Resultado del Análisis Sintáctico-Semántico:");
		lblAnalisisSintactico.setPreferredSize(new Dimension(300, altoElementos));

		tablaAnalisis = new Tabla(940, 450, encabezadoTabla, servicioAnalisis.gerResultadoSemantico().getDatosTabla());


		// Botones para ventanas extra
		botonResultado = new JButton("Ver Código Resultado");
		botonResultado.setPreferredSize(new Dimension(306, altoElementos));

		botonTablaSimbolos = new JButton("Ver Tabla de Símbolos");
		botonTablaSimbolos.setPreferredSize(new Dimension(306, altoElementos));

		botonTablaErrores = new JButton("Ver Tabla de Errores");
		botonTablaErrores.setPreferredSize(new Dimension(306, altoElementos));


		// Agregar elementos al panel resultado
		panelResultado.add(lblAnalisisSintactico);
		panelResultado.add(tablaAnalisis);
		panelResultado.add(botonResultado);
		panelResultado.add(botonTablaSimbolos);
		panelResultado.add(botonTablaErrores);

		// Agregar componente al Panel Contenido
		this.add(panelArchivo);
		this.add(panelResultado);

	}

	private void editarRutaArchivo(String nuevaRuta) {
		textRutaArchivoPrograma.setText(nuevaRuta);
	}

}
