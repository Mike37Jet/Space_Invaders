package presentacion;

import logica.ProyectilAlien;
import logica.Nivel;

import java.awt.image.BufferedImage;

public class InterfazGraficaDeProyectilAlien extends InterfazGrafica {
    private ProyectilAlien proyectilAlien;

    public InterfazGraficaDeProyectilAlien() {
        super();
        sprites = new BufferedImage[2];
        spriteEnUso = sprites;
        obtenerImagen("proyectilAlien", sprites);

    }

    @Override
    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }


    @Override
    public void run() {

    }
}
