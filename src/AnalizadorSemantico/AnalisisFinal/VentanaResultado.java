package AnalizadorSemantico.AnalisisFinal;

import javax.swing.JDialog;
import Utilidades.AreaTexto;

public class VentanaResultado extends JDialog {

	public VentanaResultado(JDialog parent, String traduccion) {
		super(parent, true);

		this.setSize(800, 550);
		this.setLocationRelativeTo(parent);
		this.setResizable(false);
		this.setTitle("Código Fuente en PHP");

		// Traduccion:
		String saltoLinea = System.lineSeparator();
		String trad = "<?php>" + saltoLinea + traduccion.replace("\\n", saltoLinea);
		AreaTexto area = new AreaTexto(800, 550, trad);
		this.add(area);

		this.setVisible(true);
	}
	
}
