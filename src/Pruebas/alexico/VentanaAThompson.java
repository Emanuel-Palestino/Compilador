package Pruebas.alexico;

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import javax.swing.JTable;

public class VentanaAThompson extends JDialog {
    /**
     * 
     */
    private static final long serialVersionUID = 4873204386349097717L;
    JDialog dialogo;
    JPanel formulario;
    JTextField textExpRegular;
    JTextField textAlfabeto;
    DefaultTableModel modelo = new DefaultTableModel();
    JTable jTableTransiciones;
    JPanel jPanelTabla;
    JScrollPane js;
    JButton botonobtenerAFN;
    JButton botonLimpiar;
    JButton botonLeerExpresion;
    String er = new String();
    String cadAlfabeto = new String();

    /**
     * 
     */
    /**
     * Crea la ventana dialog
     */
    public VentanaAThompson(JFrame parent, boolean modal, String[] encabezado, String[][] contenidoTabla, String exp, String alf) {
        super(parent, modal);
        ini(exp, alf);
        rellenarTablaTransiciones(jTableTransiciones, encabezado, contenidoTabla);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void ini(String exp, String alf) {
        dialogo = new JDialog();
        dialogo.setBounds(40, 70, 1000, 700);
        dialogo.setTitle("Algoritmo de Thompson");
        dialogo.setModal(true);
        dialogo.setLayout(new BorderLayout());
        {
            formulario = new JPanel();
            formulario.setBounds(40, 70, 800, 150);
            formulario.setLayout(null);

            JLabel lblExpRegular = new JLabel("Expresión regular : ");
            textExpRegular = new JTextField();
            lblExpRegular.setBounds(62, 20, 220, 14);
            formulario.add(lblExpRegular);

            textExpRegular = new JTextField();
            textExpRegular.setBounds(180, 20, 300, 20);
            textExpRegular.setEditable(false);
            textExpRegular.setText(exp);
            formulario.add(textExpRegular);

            JLabel lblAlfabeto = new JLabel("Alfabeto : ");
            lblAlfabeto.setBounds(120, 60, 220, 14); // (280,80,300,20)
            formulario.add(lblAlfabeto);

            textAlfabeto = new JTextField();
            textAlfabeto.setBounds(180, 60, 300, 20);
            textAlfabeto.setEditable(false);
            textAlfabeto.setText(alf);
            formulario.add(textAlfabeto);
        }
        /*
         * { botonobtenerAFN = new JButton ("Obtener AFN"); botonobtenerAFN.setBounds
         * (180,110,130,20); botonobtenerAFN.setEnabled (false);
         * botonobtenerAFN.addActionListener (new ActionListener(){
         * 
         * @Override public void actionPerformed (ActionEvent e){ //UNA VEZ SELECCIONADO
         * EL AFN, POR LO MIENTRAS SERA LA TABLA } }); botonobtenerAFN.setActionCommand
         * ("Obtener AFN"); formulario.add (botonobtenerAFN); }
         */
        { // Parte de la Tabla
            jPanelTabla = new JPanel(); // Instanciamos un panel para la tabla
            jPanelTabla.setBounds(40, 70, 800, 450);
            
            /*
             * Para pasarle al constructor de abajo necsitas pasarle una matriz
             * bidemensional arriba esta si quieres ocupar esa Los datos necesitan venir con
             * el formato para que se puedan acomodar de forma correcta en el string
             * nombreColumnas se le pasaran los nombres de las columnas ejemplo
             * nombreColumnas = {"nombre","apellido"};
             */
            /* DefaultTableModel modelo = new DefaultTableModel(datos, nombreColumnas); // Instanciamos un modelo de una
                                                                                     // tabla default
            jTableTransiciones = new JTable(modelo); // Creamos la tabla
            js = new JScrollPane(jTableTransiciones); // Agregamos un scroll por si se hace grande
            jTableTransiciones.setPreferredScrollableViewportSize(new Dimension(800, 400)); // Pone la tabla de tamaño
                                                                                            // 800 x 400
            js.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); // Añade el scroll
            js.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS); // Añade el scroll
            js.setVisible(true);
            jPanelTabla.add(js);
            jTableTransiciones.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            dialogo.getContentPane().add(jPanelTabla, BorderLayout.SOUTH);

 */        }
        dialogo.add(formulario);
    }

    public void rellenarTablaTransiciones(JTable tabla, String[] encabezado, String[][] datos) {

        DefaultTableModel modelo = new DefaultTableModel(datos, encabezado); // Instanciamos un modelo de una
                                                                                 // tabla default
        tabla = new JTable(modelo); // Creamos la tabla
        js = new JScrollPane(tabla); // Agregamos un scroll por si se hace grande
        tabla.setPreferredScrollableViewportSize(new Dimension(800, 400)); // Pone la tabla de tamaño
                                                                                        // 800 x 400
        js.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); // Añade el scroll
        js.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS); // Añade el scroll
        js.setVisible(true);
        jPanelTabla.add(js);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        dialogo.getContentPane().add(jPanelTabla, BorderLayout.SOUTH);

        dialogo.setVisible(true);
    }
}
