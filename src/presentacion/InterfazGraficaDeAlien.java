package presentacion;

import logica.Alien;
import logica.Entidad;
import logica.Nivel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class InterfazGraficaDeAlien extends InterfazGrafica {
    protected Alien alien;
    protected BufferedImage[] spritesDestruido;

    public InterfazGraficaDeAlien(String imagen) {
        super();
        this.sprites = new BufferedImage[8];
        obtenerImagen(imagen, sprites);
        obtenerImagen("alienDestruido", spritesDestruido);
    }


    @Override
    public void setNivel(Nivel nivel) {

    }

    public void actualizar() {
        this.spritesDestruido = sprites;
    }

    @Override
    public void run() {

    }
}
