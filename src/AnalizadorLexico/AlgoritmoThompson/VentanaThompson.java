package AnalizadorLexico.AlgoritmoThompson;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class VentanaThompson extends JDialog {

	JDialog ventana;
	FlowLayout diseñoPanel;
	JPanel panelInformacion;
	JScrollPane panelTabla;
	JLabel lblAlfabeto, lblExpresionRegular;
	JTextField textAlfabeto, textExpresionRegular;
	JTable transiciones;

	public VentanaThompson(JFrame parent, boolean modal, String alfabeto, String expresion, String[] encabezado, String[][] datos) {
		super(parent, modal);
                
		// Iniciar componentes que se muestran en la ventana
		inicializarInformacion();
		rellenarInformacion(alfabeto, expresion);
		mostrarTabla(encabezado, datos);
		ventana.setVisible(true);
	}

	private void inicializarInformacion() {
		// Propiedades de la ventana
		diseñoPanel = new FlowLayout(FlowLayout.LEFT, 10, 10);
		ventana = new JDialog();
		ventana.setSize(1000, 680);
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false);
		ventana.setLayout(diseñoPanel);

		ventana.setTitle("Algoritmo de Thompson");

		// Mostrar Alfabeto y Expresión regular
		panelInformacion = new JPanel();
		panelInformacion.setPreferredSize(new Dimension(500, 200));
		panelInformacion.setLayout(diseñoPanel);

		lblAlfabeto = new JLabel("Alfabeto:");
		lblAlfabeto.setPreferredSize(new Dimension(200, 30));

		textAlfabeto = new JTextField();
		textAlfabeto.setPreferredSize(new Dimension(200, 30));
		textAlfabeto.setEditable(false);

		lblExpresionRegular = new JLabel("Expresión Regular:");
		lblExpresionRegular.setPreferredSize(new Dimension(200, 30));

		textExpresionRegular = new JTextField();
		textExpresionRegular.setPreferredSize(new Dimension(200, 30));
		textExpresionRegular.setEditable(false);

		// Agregar elementos al panel Informacion
		panelInformacion.add(lblAlfabeto);
		panelInformacion.add(textAlfabeto);
		panelInformacion.add(lblExpresionRegular);
		panelInformacion.add(textExpresionRegular);


		// Integrar elementos a la ventana y mostrar
		ventana.add(panelInformacion);
	}

	public void mostrarTabla(String[] encabezado, String[][] datos) {
		DefaultTableModel modeloTabla = new DefaultTableModel(datos, encabezado);
		transiciones = new JTable(modeloTabla);
		transiciones.setEnabled(false);
		transiciones.getTableHeader().setReorderingAllowed(false);
		transiciones.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		panelTabla = new JScrollPane(transiciones);
		panelTabla.setPreferredSize(new Dimension(960, 410));

		ventana.add(panelTabla);
	}

	public void rellenarInformacion(String alfabeto, String expresion) {
		textAlfabeto.setText(alfabeto);
		textExpresionRegular.setText(expresion);
	}

}
