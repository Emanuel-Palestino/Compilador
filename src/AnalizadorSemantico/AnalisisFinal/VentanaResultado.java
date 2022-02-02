package AnalizadorSemantico.AnalisisFinal;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JTextArea;

public class VentanaResultado extends JDialog {

	public VentanaResultado(JDialog parent, String traduccion) {
		super(parent, true);

		this.setSize(800, 550);
		this.setLocationRelativeTo(parent);
		this.setResizable(false);
		this.setTitle("Código Fuente en PHP");

		// Traduccion:
		String saltoLinea = System.lineSeparator();
		JTextArea trad = new JTextArea("<?php>" + saltoLinea + traduccion.replace("\\n", saltoLinea));
		trad.setFont(new Font("Arial", Font.PLAIN, 14));
		this.add(trad);

		this.setVisible(true);
	}
	
}
