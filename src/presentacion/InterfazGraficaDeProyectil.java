package presentacion;

import logica.Nivel;

import java.awt.image.BufferedImage;

public class InterfazGraficaDeProyectil extends InterfazGrafica {
    public InterfazGraficaDeProyectil() {
        super();
        sprites = new BufferedImage[2];
        //spriteEnUso = sprites;
        obtenerImagen("proyectil", sprites);
    }

    @Override
    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    @Override
    public void run() {

    }
}
