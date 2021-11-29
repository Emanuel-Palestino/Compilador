package AnalizadorSintactico.ColeccionCanonica;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import Utilidades.Archivo;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaColeccionCanonica extends JDialog {
	private FlowLayout diseñoPanel;
	private JPanel panelInformacion, panelArchivo, panelResultado;
	private JLabel lblSimbolosNoTerminales, lblSimbolosTerminales, lblSimboloInicial, lblGramatica,
			lblColeccionCanonica;
	private JTextField textNoTerminales, textTerminales, textSimboloInicial, textRutaArchivo;
	private JTextArea areaGramatica, areaColeccionCanonica;
	private JButton botonBuscar;
	private final JDialog estaVentana = this;
	private final int altoElementos = 30;
	private final Border padding = BorderFactory.createEmptyBorder(5, 5, 5, 5);
	private final Border paddingTextArea = BorderFactory.createEmptyBorder(10, 10, 10, 10);

	// Constructor de la ventana
	public VentanaColeccionCanonica(JFrame parent, String noTerminales, String terminales, String simboloInicial,
			String gramatica, String primeros, String siguientes) {
		super(parent);

		// Iniciar componentes

		// Rellenar con informacion dada

		this.setVisible(true);
	}

	// Constructor Vacio
	public VentanaColeccionCanonica() {
		super();

		// Iniciar Componentes
		inicializarComponentes();

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

				// Obtener datos nuevos
			}
		});

		// Agregar elementos al panel Archivo
		panelArchivo.add(textRutaArchivo);
		panelArchivo.add(botonBuscar);


		/** MOstrar Simbolos de la gramatica */

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
		panelResultado.setPreferredSize(new Dimension(900, 430));
		panelResultado.setLayout(diseñoPanel);
		Font fuenteResultado = new Font("Arial", Font.PLAIN, 16);

		// Mostrar Etiqueta Gramatica
		lblGramatica = new JLabel("Gramática");
		lblGramatica.setPreferredSize(new Dimension(287, altoElementos));

		// Mostrar Etiqueta Coleccion Canonica
		lblColeccionCanonica = new JLabel("Colección Canonica:");
		lblColeccionCanonica.setPreferredSize(new Dimension(574, altoElementos));

		// Mostrar Contenido de la Gramatica
		areaGramatica = new JTextArea();
		areaGramatica.setPreferredSize(new Dimension(287, 430 - altoElementos - 20));
		areaGramatica.setEditable(false);
		areaGramatica.setFont(fuenteResultado);
		areaGramatica.setBorder(BorderFactory.createCompoundBorder(areaGramatica.getBorder(), paddingTextArea));

		// Mostrar Coleccion Canonica
		areaColeccionCanonica = new JTextArea();
		areaColeccionCanonica.setPreferredSize(new Dimension(574, 430 - altoElementos - 20));
		areaColeccionCanonica.setEditable(false);
		areaColeccionCanonica.setFont(fuenteResultado);
		areaColeccionCanonica.setBorder(BorderFactory.createCompoundBorder(areaColeccionCanonica.getBorder(), paddingTextArea));

		// Agregar elementos al panel resultado
		panelResultado.add(lblGramatica);
		panelResultado.add(lblColeccionCanonica);
		panelResultado.add(areaGramatica);
		panelResultado.add(areaColeccionCanonica);



		// Agregar componente a la Ventana
		this.add(panelArchivo);
		this.add(panelInformacion);
		this.add(panelResultado);

	}

}