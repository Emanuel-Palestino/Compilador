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

	}

}