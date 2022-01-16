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
	private DefaultTableModel modeloTabla;
	private DefaultTableCellRenderer celda;
	private JTable tabla;
	private Font fuente;
	private final int pixelesQuitar = 5;


	public Tabla(int ancho, int alto) {
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
		this.setPreferredSize(new Dimension(ancho, alto));
	}

	public Tabla(int ancho, int alto, String[] encabezado, String[][] datos) {
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
		this.setPreferredSize(new Dimension(ancho, alto));
	}

	// Tabla con titulo en la parte superior
	public Tabla(int ancho, int alto, String[] encabezado, String[][] datos, String titulo) {
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
		for (int i = 0; i < columnas; i++) {
			tabla.getColumnModel().getColumn(i).setCellRenderer(celda);
			tabla.getColumnModel().getColumn(i).setMinWidth(ancho / encabezado.length - pixelesQuitar);
		}

		this.setViewportView(tabla);
		this.setPreferredSize(new Dimension(ancho, alto));
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray), titulo,
				TitledBorder.CENTER, TitledBorder.TOP));
	}

	public void actualizarDatos(String[] encabezado, String[][] datos) {
		modeloTabla.setDataVector(datos, encabezado);
		modeloTabla.fireTableDataChanged();

		int columnas = tabla.getColumnCount();
		for (int i = 0; i < columnas; i++) {
			tabla.getColumnModel().getColumn(i).setCellRenderer(celda);
			tabla.getColumnModel().getColumn(i).setMinWidth(this.getWidth() / encabezado.length - pixelesQuitar);
		}
	}

}
