package AnalizadorSemantico.AnalisisFinal;

import javax.swing.JDialog;

import Utilidades.Tabla;

public class VentanaSimbolos extends JDialog {

	public VentanaSimbolos(JDialog parent, String[] encabezado, String[][] datos) {
		super(parent, true);

		this.setSize(600, 700);
		this.setLocationRelativeTo(parent);
		this.setResizable(false);
		this.setTitle("Tabla de Símbolos");

		// Tabla
		Tabla tabla = new Tabla(600, 700, encabezado, datos);
		this.add(tabla);
		this.setVisible(true);
	}
	
}
