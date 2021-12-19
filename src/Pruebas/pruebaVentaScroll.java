package Pruebas;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class pruebaVentaScroll extends JFrame {
	
	private JScrollPane panelPrincipal;
	private JPanel paneljeje;

	public pruebaVentaScroll() {
		this.setSize(500, 300);
		this.setResizable(false);

		// panel
		panelPrincipal = new JScrollPane();
		paneljeje = new JPanel();
		paneljeje.setBackground(Color.BLUE);
		paneljeje.setPreferredSize(new Dimension(100, 600));

		panelPrincipal.setViewportView(paneljeje);

		this.add(panelPrincipal);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new pruebaVentaScroll();
	}
}
