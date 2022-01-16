package Utilidades;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class Tabla extends JScrollPane {
	DefaultTableModel modeloTabla;
	DefaultTableCellRenderer celda;
	JTable tabla;
	Font fuente;

	public Tabla(int alto, int ancho) {
		// Iniciar fuente
		fuente = new Font("Tahoma", Font.PLAIN, 12);

		// Iniciar tabla
		modeloTabla = new DefaultTableModel();
		tabla = new JTable(modeloTabla);

		// Propiedades de la tabla
		JTableHeader encabezado = tabla.getTableHeader();
		encabezado.setReorderingAllowed(false);
		encabezado.setFont(fuente.deriveFont(Font.BOLD, 14));
		tabla.setEnabled(false);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabla.setFont(fuente);
		tabla.setShowVerticalLines(false);
		tabla.setRowHeight(30);

		// Centrar texto de las celdas
		int columnas = tabla.getColumnCount();
		celda = new DefaultTableCellRenderer();
		celda.setHorizontalAlignment(SwingConstants.CENTER);
		((DefaultTableCellRenderer) encabezado.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < columnas; i++)
			tabla.getColumnModel().getColumn(i).setCellRenderer(celda);

		this.add(tabla);
		this.setPreferredSize(new Dimension(alto, ancho));
	}

	public Tabla(int alto, int ancho, String[] encabezado, String[][] datos) {
		// Iniciar fuente
		fuente = new Font("Tahoma", Font.PLAIN, 12);

		// Iniciar tabla
		modeloTabla = new DefaultTableModel(datos, encabezado);
		tabla = new JTable(modeloTabla);

		// Propiedades de la tabla
		JTableHeader header = tabla.getTableHeader();
		header.setReorderingAllowed(false);
		header.setFont(fuente.deriveFont(Font.BOLD, 14));
		tabla.setEnabled(false);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabla.setFont(fuente);
		tabla.setShowVerticalLines(false);
		tabla.setRowHeight(30);

		// Centrar texto de las celdas
		int columnas = tabla.getColumnCount();
		celda = new DefaultTableCellRenderer();
		celda.setHorizontalAlignment(SwingConstants.CENTER);
		((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < columnas; i++)
			tabla.getColumnModel().getColumn(i).setCellRenderer(celda);

		this.setViewportView(tabla);
		this.setPreferredSize(new Dimension(alto, ancho));
	}

	// Tabla con titulo en la parte superior
	public Tabla(int alto, int ancho, String[] encabezado, String[][] datos, String titulo) {
		// Iniciar fuente
		fuente = new Font("Tahoma", Font.PLAIN, 12);

		// Iniciar tabla
		modeloTabla = new DefaultTableModel(datos, encabezado);
		tabla = new JTable(modeloTabla);

		// Propiedades de la tabla
		JTableHeader header = tabla.getTableHeader();
		header.setReorderingAllowed(false);
		header.setFont(fuente.deriveFont(Font.BOLD, 14));
		tabla.setEnabled(false);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabla.setFont(fuente);
		tabla.setShowVerticalLines(false);
		tabla.setRowHeight(30);
		
		// Centrar texto de las celdas
		int columnas = tabla.getColumnCount();
		celda = new DefaultTableCellRenderer();
		celda.setHorizontalAlignment(SwingConstants.CENTER);
		((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < columnas; i++)
			tabla.getColumnModel().getColumn(i).setCellRenderer(celda);

		this.setViewportView(tabla);
		this.setPreferredSize(new Dimension(alto, ancho));
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray), titulo, TitledBorder.CENTER, TitledBorder.TOP));
	}

}
