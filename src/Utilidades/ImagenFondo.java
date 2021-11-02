package Utilidades;
import java.awt.*;
import javax.swing.*;

//Esta clase se encarga de poner una imagen de fondo en la ventana, la imagen se encuentra en ArchivosExtra
public class ImagenFondo extends JPanel {
    private Image imagen;
    @Override
    public void paint (Graphics g){
        imagen = new ImageIcon (getClass().getResource("/ArchivosExtra/background.jpg")).getImage();
        g.drawImage(imagen, 0, 0, getWidth(),getHeight(),this);
        setOpaque(false);
        super.paint (g);
    }
}