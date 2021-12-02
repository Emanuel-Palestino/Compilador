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
import Utilidades.ConjuntoSimbolos;
import Utilidades.ResultadoPrimerosSiguientes;
import Utilidades.Gramatica.Gramatica;
import Utilidades.Gramatica.ReglaProduccion;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
			String gramatica, String primeros, String siguientes) {
		super(parent, true);

		// Iniciar componentes
		inicializarComponentes();
		// Rellenar con informacion dada
		rellenarComponentes(noTerminales, terminales, simboloInicial, gramatica, primeros, siguientes);

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
		textRutaArchivo = new JTextField("src/ArchivosExtra/gramatica.txt");
		textRutaArchivo.setPreferredSize(new Dimension(600, altoElementos));
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
				editarRutaArchivo(ruta);

				// Obtener datos nuevos
				PrimerosSiguientes ps = new PrimerosSiguientes();
				// Cargar gramatica
				Gramatica grama = new Gramatica(ruta);
				ResultadoPrimerosSiguientes resultado = ps.hacer(grama);

				// pasarle los datos a la ventana
				String noTerminales, terminales, simboloInicial, gramatica, primeros, siguientes;
				ArrayList<String> aux = new ArrayList<String>();

				// No Terminales
				aux = grama.getNoTerminales();
				noTerminales = "";
				for (String simbolo : aux) {
					noTerminales += simbolo + " ";
				}

				// Terminales
				aux = grama.getTerminales();
				terminales = "";
				for (String simbolo : aux) {
					terminales += simbolo + " ";
				}

				// Simbolo Inicial
				simboloInicial = grama.getSimboloInicial();

				// Gramatica
				gramatica = "";
				ArrayList<ReglaProduccion> reglas = grama.getReglasProduccion();
				for (ReglaProduccion regla : reglas) {
					gramatica += regla.getSimboloGramatical() + " -> ";
					aux = regla.getProduccion();
					for (String simbolo : aux)
						gramatica += simbolo + " ";
					gramatica += "\n";
				}

				// Primeros
				primeros = "";
				ArrayList<ConjuntoSimbolos> conjuntoSimbolos = resultado.getPrimeros();
				for (ConjuntoSimbolos simbolos : conjuntoSimbolos) {
					primeros += "P(" + simbolos.getId() + ") = { ";
					aux = simbolos.getSimbolos();
					for (String simbolo : aux)
						primeros += simbolo + " ";
					primeros += "}\n";
				}

				// Siguientes
				siguientes = "";
				conjuntoSimbolos = resultado.getSiguientes();
				for (ConjuntoSimbolos simbolos : conjuntoSimbolos) {
					siguientes += "S(" + simbolos.getId() + ") = { ";
					aux = simbolos.getSimbolos();
					for (String simbolo : aux)
						siguientes += simbolo + " ";
					siguientes += "}\n";
				}

				rellenarComponentes(noTerminales, terminales, simboloInicial, gramatica, primeros, siguientes);
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
		lblSimbolosNoTerminales = new JLabel("Símbolos No Terminales: ");
		lblSimbolosNoTerminales.setPreferredSize(new Dimension(120, altoElementos));

		textNoTerminales = new JTextField();
		textNoTerminales.setPreferredSize(new Dimension(300, altoElementos));
		textNoTerminales.setEditable(false);
		// Padding a JTextField
		textNoTerminales.setBorder(BorderFactory.createCompoundBorder(textNoTerminales.getBorder(), padding));

		// Mostrar los simbolos terminales
		lblSimbolosTerminales = new JLabel("Símbolos Terminales: ");
		lblSimbolosTerminales.setPreferredSize(new Dimension(120, altoElementos));

		textTerminales = new JTextField();
		textTerminales.setPreferredSize(new Dimension(300, altoElementos));
		textTerminales.setEditable(false);
		// Padding a JTextField
		textTerminales.setBorder(BorderFactory.createCompoundBorder(textTerminales.getBorder(), padding));

		// Mostrar simbolo inicial
		lblSimboloInicial = new JLabel("Símbolo Inicial:");
		lblSimboloInicial.setPreferredSize(new Dimension(120, altoElementos));

		textSimboloInicial = new JTextField();
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

		// Mostrar el contenido de los Primeros
		areaPrimeros = new JTextArea();
		areaPrimeros.setPreferredSize(new Dimension(287, 430 - altoElementos - 20));
		areaPrimeros.setEditable(false);
		areaPrimeros.setFont(fuenteResultado);
		areaPrimeros.setBorder(BorderFactory.createCompoundBorder(areaPrimeros.getBorder(), paddingTextArea));

		// Mostrar le contenido de los Siguientes
		areaSiguientes = new JTextArea();
		areaSiguientes.setPreferredSize(new Dimension(287, 430 - altoElementos - 20));
		areaSiguientes.setEditable(false);
		areaSiguientes.setFont(fuenteResultado);
		areaSiguientes.setBorder(BorderFactory.createCompoundBorder(areaSiguientes.getBorder(), paddingTextArea));

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

	private void rellenarComponentes(String noTerminales, String terminales, String simboloInicial, String gramatica,
			String primeros, String siguientes) {
		textNoTerminales.setText(noTerminales);
		textTerminales.setText(terminales);
		textSimboloInicial.setText(simboloInicial);
		areaGramatica.setText(gramatica);
		areaPrimeros.setText(primeros);
		areaSiguientes.setText(siguientes);
	}

	private void editarRutaArchivo(String nuevaRuta) {
		textRutaArchivo.setText(nuevaRuta);
	}

}
