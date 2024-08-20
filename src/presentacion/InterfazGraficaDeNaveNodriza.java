package presentacion;
import logica.Nivel;
import java.awt.image.BufferedImage;

public class InterfazGraficaDeNaveNodriza extends InterfazGrafica {


    public InterfazGraficaDeNaveNodriza() {
        super();
        sprites = new BufferedImage[2];
        spriteEnUso = sprites;
        obtenerImagen("naveNodriza", sprites);
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;

    }

    @Override
    public void run() {

    }


}
