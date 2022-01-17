package AnalizadorLexico.Final;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Utilidades.Archivo;
import Utilidades.Tabla;
import Utilidades.AnalizadorLexico.ResultadoAnalisisLexico;
import Utilidades.Excepciones.ExcepcionER;

import javax.swing.JButton;
import java.awt.event.*;
import java.io.IOException;
import java.awt.*;

public class VentanaFinal extends JDialog {
	private FlowLayout diseñoPanel;
	private JPanel panelInformacion;
	private JTextField mostrarRutaArchivo;
	private Tabla tablaTokens, tablaErrores, tablaSimbolos;
	private JButton botonArchivo;
	private final JDialog estaVentana = this;

	// encabezados tablas
	final String[] encabezadoTokens = { "# linea", "Lexema", "Token" };
	final String[] encabezadoErrores = { "# Linea", "Descripción" };
	final String[] encabezadoSimbolos = { "Id ", "Valor", "Función" };

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
		panelInformacion.setPreferredSize(new Dimension(1000, 200));
		panelInformacion.setLayout(diseñoPanel);

		// agregamos boton
		botonArchivo = new JButton("Buscar Archivo");
		botonArchivo.setPreferredSize(new Dimension(150, 30));

		mostrarRutaArchivo = new JTextField();
		mostrarRutaArchivo.setPreferredSize(new Dimension(700, 30));
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
					datosTokens = res.getTokensTabla();
					datosSimbolos = res.getSimbolosTabla();
					datosErrores = res.getErroresTabla();

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

		this.add(panelInformacion);
	}

	public void mostrarTabla(String[][] datos, String[][] datosErrores, String[][] datosSimbolos) {
		tablaTokens = new Tabla(520, 500, encabezadoTokens, datos, "Tira de Tokens");
		tablaSimbolos = new Tabla(300, 500, encabezadoSimbolos, datosSimbolos, "Tabla de Símbolos");
		tablaErrores = new Tabla(520, 500, encabezadoErrores, datosErrores, "Tabla de Errores");

		// Hacemos visible la tabla
		this.add(tablaTokens);
		this.add(tablaSimbolos);
		this.add(tablaErrores);
	}

	public void mostrarRutaArchivo(String rutaArchvo) {
		mostrarRutaArchivo.setText(rutaArchvo);
	}

	public void editarTabla(String[][] datosTokensCambio, String[][] datosSimbolosCambio,
			String[][] datosErroresCambio) {
		tablaTokens.actualizarDatos(encabezadoTokens, datosTokensCambio);
		tablaSimbolos.actualizarDatos(encabezadoSimbolos, datosSimbolosCambio);
		tablaErrores.actualizarDatos(encabezadoErrores, datosErroresCambio);
	}

}
