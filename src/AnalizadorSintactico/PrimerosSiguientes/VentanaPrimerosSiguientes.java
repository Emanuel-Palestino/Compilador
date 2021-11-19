package AnalizadorSintactico.PrimerosSiguientes;

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

public class VentanaPrimerosSiguientes extends JDialog {
	private FlowLayout diseñoPanel;
	private JPanel panelInformacion, panelArchivo, panelResultado;
	private JLabel lblSimbolosNoTerminales, lblSimbolosTerminales, lblSimboloInicial, lblGramatica, lblPrimeros,
			lblSiguientes;
	private JTextField textNoTerminales, textTerminales, textSimboloInicial, textRutaArchivo;
	private JTextArea areaGramatica, areaPrimeros, areaSiguientes;
	private JButton botonBuscar;
	private final JDialog estaVentana = this;
	private final int altoElementos = 30;
	private final Border padding = BorderFactory.createEmptyBorder(5, 5, 5, 5);
	private final Border paddingTextArea = BorderFactory.createEmptyBorder(10, 10, 10, 10);

	public VentanaPrimerosSiguientes(JFrame parent, String noTerminales, String terminales, String simboloInicial,
			String gramatica, String primeros, String segundos) {
		super(parent);

		// Iniciar componentes
		inicializarComponentes();

		this.setVisible(true);
	}

	public VentanaPrimerosSiguientes() {
		super();

		// Iniciar componentes
		inicializarComponentes();

		this.setVisible(true);
	}

	private void inicializarComponentes() {
		// Propiedades de la venta
		diseñoPanel = new FlowLayout(FlowLayout.LEFT, 10, 10);
		this.setSize(1000, 700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(diseñoPanel);
		this.setTitle("Analizador Léxico - Algoritmo de Primeros y Siguientes");

		/* Mostrar ruta del archivo y boton para cargar otro archivo */

		// Propiedades del panel de Archivo
		panelArchivo = new JPanel();
		panelArchivo.setPreferredSize(new Dimension(800, 60));
		panelArchivo.setLayout(diseñoPanel);

		// Mostrar la ruta del archivo
		textRutaArchivo = new JTextField("Prueba");
		textRutaArchivo.setPreferredSize(new Dimension(500, altoElementos));
		textRutaArchivo.setEditable(false);
		// Padding a JTextField
		textRutaArchivo.setBorder(BorderFactory.createCompoundBorder(textRutaArchivo.getBorder(), padding));

		// Boton para buscar otro archivo
		botonBuscar = new JButton("Buscar Archivo");
		botonBuscar.setPreferredSize(new Dimension(130, altoElementos));

		// Agregar Accion al boton
		botonBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Obtener nueva ruta del archivo
				String ruta = Archivo.obtenerRutaArchivo(estaVentana);

				// Obtener datos nuevos
			}
		});

		// Agregar elementos al panel Archivo
		panelArchivo.add(textRutaArchivo);
		panelArchivo.add(botonBuscar);

		/* Mostrar Simbolos de la gramatica */

		// Propiedades del panel Informacion
		panelInformacion = new JPanel();
		panelInformacion.setPreferredSize(new Dimension(500, 130));
		panelInformacion.setLayout(diseñoPanel);

		// Mostrar los simbolos no terminales
		lblSimbolosNoTerminales = new JLabel("Simbolos No Terminales: ");
		lblSimbolosNoTerminales.setPreferredSize(new Dimension(120, altoElementos));

		textNoTerminales = new JTextField("A B C D E F G");
		textNoTerminales.setPreferredSize(new Dimension(300, altoElementos));
		textNoTerminales.setEditable(false);
		// Padding a JTextField
		textNoTerminales.setBorder(BorderFactory.createCompoundBorder(textNoTerminales.getBorder(), padding));

		// Mostrar los simbolos terminales
		lblSimbolosTerminales = new JLabel("Simbolos Terminales: ");
		lblSimbolosTerminales.setPreferredSize(new Dimension(120, altoElementos));

		textTerminales = new JTextField("A B C D E F G");
		textTerminales.setPreferredSize(new Dimension(300, altoElementos));
		textTerminales.setEditable(false);
		// Padding a JTextField
		textTerminales.setBorder(BorderFactory.createCompoundBorder(textTerminales.getBorder(), padding));

		// Mostrar simbolo inicial
		lblSimboloInicial = new JLabel("Simbolo Inicial:");
		lblSimboloInicial.setPreferredSize(new Dimension(120, altoElementos));

		textSimboloInicial = new JTextField("A");
		textSimboloInicial.setPreferredSize(new Dimension(300, altoElementos));
		textSimboloInicial.setEditable(false);
		// Padding a JTextField
		textSimboloInicial.setBorder(BorderFactory.createCompoundBorder(textSimboloInicial.getBorder(), padding));

		// Agregar elementos al panel Informacion
		panelInformacion.add(lblSimbolosNoTerminales);
		panelInformacion.add(textNoTerminales);
		panelInformacion.add(lblSimbolosTerminales);
		panelInformacion.add(textTerminales);
		panelInformacion.add(lblSimboloInicial);
		panelInformacion.add(textSimboloInicial);

		/** Panel de Resultados */
		
		// Propiedades del panel de Resultados
		panelResultado = new JPanel();
		panelResultado.setPreferredSize(new Dimension(900, 430));
		panelResultado.setLayout(diseñoPanel);
		// Fuente a utilizar
		Font fuenteResultado = new Font("Arial", Font.PLAIN, 16);

		// Mostrar Etiqueta Gramatica
		lblGramatica = new JLabel("Gramática:");
		lblGramatica.setPreferredSize(new Dimension(287, altoElementos));

		// Mostrar Etiqueta Primeros
		lblPrimeros = new JLabel("Primeros:");
		lblPrimeros.setPreferredSize(new Dimension(287, altoElementos));

		// Mostrar Etiqueta Siguientes
		lblSiguientes = new JLabel("Siguientes:");
		lblSiguientes.setPreferredSize(new Dimension(287, altoElementos));

		// Mostrar el contenido de la Gramatica
		areaGramatica = new JTextArea();
		areaGramatica.setPreferredSize(new Dimension(287, 430 - altoElementos - 20));
		areaGramatica.setEditable(false);
		areaGramatica.setFont(fuenteResultado);
		areaGramatica.setBorder(BorderFactory.createCompoundBorder(areaGramatica.getBorder(), paddingTextArea));
		areaGramatica.setText("ESTO\nES\nUNA\nPRUEBA");

		// Mostrar el contenido de los Primeros
		areaPrimeros = new JTextArea();
		areaPrimeros.setPreferredSize(new Dimension(287, 430 - altoElementos - 20));
		areaPrimeros.setEditable(false);
		areaPrimeros.setFont(fuenteResultado);
		areaPrimeros.setBorder(BorderFactory.createCompoundBorder(areaPrimeros.getBorder(), paddingTextArea));
		areaPrimeros.setText("ESTO\nES\nUNA\nPRUEBA");

		// Mostrar le contenido de los Siguientes
		areaSiguientes = new JTextArea();
		areaSiguientes.setPreferredSize(new Dimension(287, 430 - altoElementos - 20));
		areaSiguientes.setEditable(false);
		areaSiguientes.setFont(fuenteResultado);
		areaSiguientes.setBorder(BorderFactory.createCompoundBorder(areaSiguientes.getBorder(), paddingTextArea));
		areaSiguientes.setText("ESTO\nES\nUNA\nPRUEBA");

		// Agregar elementos al panel Resultado
		panelResultado.add(lblGramatica);
		panelResultado.add(lblPrimeros);
		panelResultado.add(lblSiguientes);
		panelResultado.add(areaGramatica);
		panelResultado.add(areaPrimeros);
		panelResultado.add(areaSiguientes);


		/* Agregar paneles a la ventana */
		this.add(panelArchivo);
		this.add(panelInformacion);
		this.add(panelResultado);

	}

}
