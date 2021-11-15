package AnalizadorLexico.Final;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Utilidades.Archivo;
import Utilidades.ResultadoAnalisisLexico;
import Utilidades.Excepciones.ExcepcionER;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.*;
import java.io.IOException;
import java.awt.*;

public class VentanaFinal extends JDialog {
	private FlowLayout diseñoPanel;
	private JPanel panelInformacion;
	private JScrollPane panelTablaTokens, panelTablaErrores, panelTablaSimbolos;
	private JTextField mostrarRutaArchivo;
	private JTable tablaTokens, tablaErrores, tablaSimbolos;
	private JButton botonArchivo;
	private final JDialog estaVentana = this;

	// encabezados tablas
	final String[] encabezadoTokens = { "# linea", "Lexema", "Token" };
	final String[] encabezadoErrores = { "# Linea", "Descripción" };
	final String[] encabezadoVariables = { "Id ", "Valor", "Función" };

	// Modelos de tabla
	DefaultTableModel modeloTablaTokens = new DefaultTableModel();
	DefaultTableModel modeloTablaErrores = new DefaultTableModel();
	DefaultTableModel modeloTablaSimbolos = new DefaultTableModel();

	public VentanaFinal(JFrame parent, boolean modal, String[][] datos, String[][] datosErrores, String[][] datosId,
			String rutaArchivo) {
		super(parent, modal);

		inicializarInformacion(rutaArchivo);
		mostrarTabla(datos, datosErrores, datosId);
		this.setVisible(true);
	}

	public VentanaFinal(JFrame parent, boolean modal, String rutaArchivo) {
		super(parent, modal);

		String[][] datos = { { "1", "int", "int" }, { "1", "main", "main" }, { "1", "(", "(" } };

		String[][] datosErrores = { { "", "En función 'main'" }, { "2", "Error: @ simbolo no definido" } };

		String[][] datosId = { { "c", "4", "32" }, { "d", "6", "33" } };

		inicializarInformacion(rutaArchivo);
		mostrarTabla(datos, datosErrores, datosId);
		this.setVisible(true);
	}

	private void inicializarInformacion(String rutaArchivo) {
		// Strings de prueba

		diseñoPanel = new FlowLayout(FlowLayout.LEFT, 10, 10);
		this.setSize(1400, 760);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(diseñoPanel);

		this.setTitle("Analizador Lexico");

		panelInformacion = new JPanel();
		panelInformacion.setPreferredSize(new Dimension(500, 200));
		panelInformacion.setLayout(diseñoPanel);

		// agregamos boton
		botonArchivo = new JButton("Buscar Archivo");
		botonArchivo.setPreferredSize(new Dimension(130, 30));

		mostrarRutaArchivo = new JTextField();
		mostrarRutaArchivo.setPreferredSize(new Dimension(350, 30));
		mostrarRutaArchivo.setEditable(false);
		mostrarRutaArchivo(rutaArchivo);

		// Agregamos que el boton accione
		botonArchivo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Obtener ruta del nuevo archivo
				String ruta = Archivo.obtenerRutaArchivo(estaVentana);

				// Obtener datos nuevos
				try {
					String[][] datosTokens, datosSimbolos, datosErrores;
					ResultadoAnalisisLexico res;
					res = EvaluarCodigo.evaluar(ruta);
					datosTokens = res.getTokens();
					datosSimbolos = res.getSimbolos();
					datosErrores = res.getErrores();

					mostrarRutaArchivo(ruta);
					editarTabla(datosTokens, datosSimbolos, datosErrores);
				} catch (IOException | ExcepcionER e1) {
					e1.printStackTrace();
				}
			}
		});

		// Agregar elementos al panel Informacion
		panelInformacion.add(mostrarRutaArchivo);
		panelInformacion.add(botonArchivo);

		this.setLayout(new BorderLayout());
		this.add(panelInformacion, BorderLayout.PAGE_START);
	}

	public void mostrarTabla(String[][] datos, String[][] datosErrores, String[][] datosId) {
		modeloTablaTokens.setDataVector(datos, encabezadoTokens);
		modeloTablaErrores.setDataVector(datosErrores, encabezadoErrores);
		modeloTablaSimbolos.setDataVector(datosId, encabezadoVariables);

		tablaTokens = new JTable();
		tablaTokens.setEnabled(false);
		tablaTokens.getTableHeader().setReorderingAllowed(false);
		tablaTokens.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		tablaErrores = new JTable();
		tablaErrores.setEnabled(false);
		tablaErrores.getTableHeader().setReorderingAllowed(false);

		tablaSimbolos = new JTable();
		tablaSimbolos.setEnabled(false);
		tablaSimbolos.getTableHeader().setReorderingAllowed(false);
		tablaSimbolos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		panelTablaTokens = new JScrollPane(tablaTokens);
		panelTablaTokens.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				"1. Tabla de Tokens", TitledBorder.CENTER, TitledBorder.TOP));
		panelTablaTokens.setPreferredSize(new Dimension(550, 550));

		panelTablaSimbolos = new JScrollPane(tablaSimbolos);
		panelTablaSimbolos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				"2. Tabla de símbolos", TitledBorder.CENTER, TitledBorder.TOP));
		panelTablaSimbolos.setPreferredSize(new Dimension(550, 550));

		panelTablaErrores = new JScrollPane(tablaErrores);
		panelTablaErrores.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				"3. Tabla de Errores", TitledBorder.CENTER, TitledBorder.TOP));
		panelTablaErrores.setPreferredSize(new Dimension(350, 550));

		tablaTokens.setModel(modeloTablaTokens);
		tablaSimbolos.setModel(modeloTablaSimbolos);
		tablaErrores.setModel(modeloTablaErrores);

		// Hacemos visible la tabla
		this.add(panelTablaTokens, BorderLayout.LINE_START);
		this.add(panelTablaSimbolos, BorderLayout.CENTER);
		this.add(panelTablaErrores, BorderLayout.LINE_END);
	}

	public void mostrarRutaArchivo(String rutaArchvo) {
		mostrarRutaArchivo.setText(rutaArchvo);
	}

	public void editarTabla(String[][] datosTokensCambio, String[][] datosSimbolosCambio,
			String[][] datosErroresCambio) {
		// Para efectos de las pruebas solo le puse el "(prueba)" para ver si cambiaba
		// pero como seran las mismas columnas cambiarlo
		modeloTablaTokens.setDataVector(datosTokensCambio, encabezadoTokens);
		modeloTablaErrores.setDataVector(datosErroresCambio, encabezadoErrores);
		modeloTablaSimbolos.setDataVector(datosSimbolosCambio, encabezadoVariables);

		modeloTablaTokens.fireTableDataChanged();
		modeloTablaErrores.fireTableDataChanged();
		modeloTablaSimbolos.fireTableDataChanged();
	}

}
