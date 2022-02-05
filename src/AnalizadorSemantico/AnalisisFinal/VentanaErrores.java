package AnalizadorSemantico.AnalisisFinal;

import javax.swing.JDialog;

import Utilidades.Tabla;

public class VentanaErrores extends JDialog {

	public VentanaErrores(JDialog parent, String[] encabezado, String[][] datos) {
		super(parent, true);
		this.setSize(700, 600);
		this.setLocationRelativeTo(parent);
		this.setResizable(false);
		this.setTitle("Tabla de Errores");

		// Tabla
		Tabla tabla = new Tabla(700, 600, encabezado, datos);
		this.add(tabla);
		this.setVisible(true);
	}
	
}
