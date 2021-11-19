package AnalizadorSintactico.PrimerosSiguientes;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Utilidades.Archivo;
import Utilidades.ResultadoAnalisisLexico;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrimerosSiguientes extends JDialog {
	private FlowLayout diseñoPanel;
	private JPanel panelInformacion, panelArchivo, panelResultado;
	private JLabel lblSimbolosNoTerminales, lblSimbolosTerminales, lblSimboloInicial, lblGramatica, lblPrimeros, lblSiguientes;
	private JTextField textNoTerminales, textTerminales, textSimboloInicial, textRutaArchivo;
	private JTextArea areaGramatica, areaPrimeros, areaSegundos;
	private JButton botonBuscar;
	private final JDialog estaVentana = this;
	private final int altoElementos = 30;

	public VentanaPrimerosSiguientes(JFrame parent, String noTerminales, String terminales, String simboloInicial, String gramatica, String primeros, String segundos) {
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
	 	textRutaArchivo.setBorder(BorderFactory.createCompoundBorder(
			textRutaArchivo.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)
		));

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
		panelInformacion.setPreferredSize(new Dimension(500, 200));
		panelInformacion.setLayout(diseñoPanel);

		// Mostrar los simbolos no terminales
		lblSimbolosNoTerminales = new JLabel("Simbolos No Terminales: ");
		lblSimbolosNoTerminales.setPreferredSize(new Dimension(120, altoElementos));

		textNoTerminales = new JTextField("A B C D E F G");
		textNoTerminales.setPreferredSize(new Dimension(300, altoElementos));
		textNoTerminales.setEditable(false);

		// Mostrar los simbolos terminales
		lblSimbolosTerminales = new JLabel("Simbolos Terminales: ");
		lblSimbolosTerminales.setPreferredSize(new Dimension(120, altoElementos));

		textTerminales = new JTextField("A B C D E F G");
		textTerminales.setPreferredSize(new Dimension(300, altoElementos));
		textTerminales.setEditable(false);

		// Mostrar simbolo inicial
		lblSimboloInicial = new JLabel("Simbolo Inicial");
		lblSimboloInicial.setPreferredSize(new Dimension(120, altoElementos));
		
		textSimboloInicial = new JTextField("A");
		textSimboloInicial.setPreferredSize(new Dimension(300, altoElementos));
		textSimboloInicial.setEditable(false);


		// Agregar elementos al panel Informacion
		panelInformacion.add(lblSimbolosNoTerminales);
		panelInformacion.add(textNoTerminales);
		panelInformacion.add(lblSimbolosTerminales);
		panelInformacion.add(textTerminales);
		panelInformacion.add(lblSimboloInicial);
		panelInformacion.add(textSimboloInicial);


		/* Agregar paneles a la ventana */
		this.add(panelArchivo);
		this.add(panelInformacion);

	}

}
