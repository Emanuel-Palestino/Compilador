package AnalizadorLexico.AlgoritmoThompson;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Utilidades.Tabla;
import Utilidades.Excepciones.ExcepcionER;

import javax.swing.JButton;
import java.awt.event.*;
import java.io.IOException;
import java.awt.*;

public class VentanaThompson extends JDialog {

	private FlowLayout diseñoPanel;
	private JPanel panelInformacion;
	private JScrollPane panelTabla;
	private JLabel lblAlfabeto, lblExpresionRegular;
	private JTextField textAlfabeto, textExpresionRegular, mostrarArchivo;
	private JTable transiciones;
	private Tabla tabla;
	private JButton boton;
	private final int altoInputs = 30;
	private ServicioThompson servicioThompson;
	private final String rutaExpresionDefecto = "src/ArchivosExtra/ExpresionRegular.txt";

	public VentanaThompson(JFrame parent) {
		super(parent, true);

		// Obtencion de datos
		servicioThompson = new ServicioThompson();
		ModeloResultadoThompson resultado;
		try {
			resultado = servicioThompson.ejecutar(rutaExpresionDefecto);
			// Iniciar componentes que se muestran en la ventana
			inicializarInformacion();
			rellenarInformacion(resultado.getAlfabeto(), resultado.getExpresionRegular());
			mostrarTabla(resultado.getEncabezado(), resultado.getDatos());
			this.setVisible(true);
		} catch (ExcepcionER | IOException e) {
			JOptionPane.showMessageDialog(this, "No se pudo abrir el archivo");
		}
	}

	private void inicializarInformacion() {
		// Propiedades de la this
		diseñoPanel = new FlowLayout(FlowLayout.LEFT, 10, 10);
		this.setSize(1000, 680);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(diseñoPanel);

		this.setTitle("Algoritmo de Thompson");

		// Mostrar Alfabeto y Expresión regular
		panelInformacion = new JPanel();
		panelInformacion.setPreferredSize(new Dimension(900, 150));
		panelInformacion.setLayout(diseñoPanel);

		lblAlfabeto = new JLabel("Alfabeto:");
		lblAlfabeto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAlfabeto.setPreferredSize(new Dimension(200, altoInputs));

		textAlfabeto = new JTextField();
		textAlfabeto.setPreferredSize(new Dimension(600, altoInputs));
		textAlfabeto.setEditable(false);

		lblExpresionRegular = new JLabel("Expresión Regular:");
		lblExpresionRegular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblExpresionRegular.setPreferredSize(new Dimension(200, altoInputs));

		textExpresionRegular = new JTextField();
		textExpresionRegular.setPreferredSize(new Dimension(600, altoInputs));
		textExpresionRegular.setEditable(false);

		// agregamos boton
		boton = new JButton("Buscar Archivo");
		boton.setPreferredSize(new Dimension(200, altoInputs));

		mostrarArchivo = new JTextField();
		mostrarArchivo.setPreferredSize(new Dimension(350, altoInputs));
		mostrarArchivo.setEditable(false);
		mostrarRutaArchivo(mostrarArchivo, "");

		// Agregamos que el boton accione
		boton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/* Codigo para buscar archivo */
			}
		});

		// Agregar elementos al panel Informacion
		panelInformacion.add(lblAlfabeto);
		panelInformacion.add(textAlfabeto);
		panelInformacion.add(lblExpresionRegular);
		panelInformacion.add(textExpresionRegular);
		panelInformacion.add(boton);

		// Integrar elementos a la this y mostrar
		this.add(panelInformacion);
	}

	public void mostrarTabla(String[] encabezado, String[][] datos) {
		tabla = new Tabla(960, 410, encabezado, datos);
		this.add(tabla);
	}

	public void rellenarInformacion(String alfabeto, String expresion) {
		textAlfabeto.setText(alfabeto);
		textExpresionRegular.setText(expresion);
	}

	public void mostrarRutaArchivo(JTextField mostrarText, String rutaArchvo) {
		mostrarText.setText(rutaArchvo);
	}

}
