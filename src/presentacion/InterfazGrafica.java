package presentacion;

import logica.Entidad;
import logica.NaveEspacial;
import logica.Nivel;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public abstract class InterfazGrafica implements Runnable{

    protected Nivel nivel;
    protected BufferedImage[] sprites;
    protected Entidad entidad;
    Random random = new Random();


    public InterfazGrafica() {
        sprites = new BufferedImage[2];
    }


    public void dibujar(Graphics2D g2) {
        BufferedImage image = siguienteImagen();
        g2.drawImage(image, this.entidad.getxPos(), this.entidad.getyPos(), null);
    }

    protected BufferedImage siguienteImagen() {
        if (random.nextInt(2) == 0) {
            return sprites[0];
        } else {
            return sprites[1];
        }
    }

    public void obtenerImagen(String direccionImagen, BufferedImage[] sprites) {
        try {
            sprites[0] = ImageIO.read(getClass().getResource("/imagenes/" + direccionImagen + "/" + direccionImagen + "1.png"));
            sprites[1] = ImageIO.read(getClass().getResource("/imagenes/" + direccionImagen + "/" + direccionImagen + "2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void setNivel(Nivel nivel);

    public void actualizar() {

    }

    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }
}
