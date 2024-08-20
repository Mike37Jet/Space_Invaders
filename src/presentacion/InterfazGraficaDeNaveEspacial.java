package presentacion;

import Recursos.Constantes;
import logica.Entidad;
import logica.NaveEspacial;
import logica.Nivel;

import java.awt.image.BufferedImage;

public class InterfazGraficaDeNaveEspacial extends InterfazGrafica{
    private final ContenedorSpaceInvaders contenedorSpaceInvaders;
    protected BufferedImage[] spritesDestruida;
    protected NaveEspacial naveEspacial;
    public InterfazGraficaDeNaveEspacial(ContenedorSpaceInvaders contenedorSpaceInvaders) {
        super();
        this.contenedorSpaceInvaders = contenedorSpaceInvaders;
        this.sprites = new BufferedImage[2];
        this.spritesDestruida = new BufferedImage[2];
        obtenerImagen("naveEspacial", sprites);
        obtenerImagen("naveEspacialDestruida", spritesDestruida);
    }

    @Override
    public void setNivel(Nivel nivel) {
        spriteEnUso = sprites;
        this.nivel = nivel;
    }

    @Override
    public void run() {
        while (true) {
            contenedorSpaceInvaders.repaint();
            verificarTiempo();
        }
    }

    public void verificarTiempo() {
        double intervaloDeDibujo = 1000000000 / Constantes.FPS; //frames por seundo
        double nextDrawTime = System.nanoTime() + intervaloDeDibujo; //intervalo de sistema en nanosegundos
        try {
            double tiempoRestante = (nextDrawTime - System.nanoTime()) / 1000000;
            if (tiempoRestante < 0) {
                tiempoRestante = 0;
            }
            Thread.sleep((long) tiempoRestante);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
