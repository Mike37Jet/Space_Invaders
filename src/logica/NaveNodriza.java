package logica;

import Recursos.Audio;
import Recursos.Constantes;

public class NaveNodriza extends Entidad {

    /**** VARIABLES ****/

    public Audio musicaNaveNodriza = new Audio("/Sonidos/sonidoNaveNodriza.wav");
    public Audio musicaNaveNodrizaDestruida = new Audio("/Sonidos/sonidoNaveNodrizaDestruida.wav");
    private int contador;
    private static boolean enPausa = true;  // Empieza en pausa

    /**** Constructor ****/

    public NaveNodriza() {
        super(Constantes.anchoNaveNodriza, Constantes.altoNaveNodriza, Constantes.posXInicialNaveNodriza, Constantes.posYNaveNodriza, Constantes.espacioDeDesplazamientoEnYNaveNodriza, 0);
        super.vivo = true;
        this.contador = 0;
    }

    /**** Métodos ****/

    public int desplazarNave() {
        if (this.vivo && contadorDeIteraciones % 2 == 0) {
            if (this.xPos > 0) {
                this.xPos -= this.cambioX;
            } else {
                this.xPos = Constantes.posXInicialNaveNodriza; // Reinicia la posición al llegar al borde
                enPausa = true;  // Pausa el hilo al llegar al borde
            }
        }

        return this.xPos;
    }

    public void quitarNave() {
        if (contador < 300) {
            this.xPos = Constantes.posXInicialNaveNodriza;
            contador++;
        } else {
            this.xPos = Constantes.posXInicialNaveNodriza;
        }
    }




    @Override
    public void actualizar() {
        if (enPausa) {
            try {
                Thread.sleep((long) (10000 * 2.5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            enPausa = false;

        }
        if (!this.vivo) {
            this.quitarNave();
        }
        musicaNaveNodriza.play();
        this.desplazarNave();
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
