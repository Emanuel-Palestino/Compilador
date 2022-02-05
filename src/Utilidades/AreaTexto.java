package Utilidades;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.plaf.DimensionUIResource;

import java.awt.Font;

public class AreaTexto extends JScrollPane {

	private JTextArea area;
	private Font fuente;

	public AreaTexto(int ancho, int alto) {
		// Iniciar fuente
		fuente = new Font("Tahoma", Font.PLAIN, 14);

		// Iniciar area
		area = new JTextArea();
		area.setFont(fuente);
		this.setViewportView(area);
		this.setPreferredSize(new DimensionUIResource(ancho, alto));
	}

	public AreaTexto(int ancho, int alto, String contenido) {
		// Iniciar fuente
		fuente = new Font("Tahoma", Font.PLAIN, 14);

		// Iniciar area
		area = new JTextArea(contenido);
		area.setFont(fuente);
		this.setViewportView(area);
		this.setPreferredSize(new DimensionUIResource(ancho, alto));
	}
	
}
