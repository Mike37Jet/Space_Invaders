package logica;

import javax.swing.ImageIcon;

import Recursos.Audio;

import Recursos.Constantes;

public class NaveNodriza extends Entidad {

    /**** VARIABLES ****/

    public Audio musicaNaveNodriza = new Audio("/Sonidos/sonidoNaveNodriza.wav");
    public Audio musicaNaveNodrizaDestruida = new Audio("/Sonidos/sonidoNaveNodrizaDestruida.wav");

    private int contador = 0;


    /**** Constructor ****/

    public NaveNodriza() {
        super(Constantes.anchoNaveNodriza, Constantes.altoNaveNodriza, Constantes.posXInicialNaveNodriza,Constantes.posYNaveNodriza,Constantes.espacioDeDesplazamientoEnYNaveNodriza,0);

        super.estaVivo = true;

        this.musicaNaveNodriza.play();
        this.musicaNaveNodrizaDestruida.stop();
        this.contador = 0;
    }

    /**** Metododos ****/

    public int desplazarNave() {
        // Calcula la nueva posición de la nave nodriza después del posible movimiento
        if (this.estaVivo && contadorDeIteraciones % 2 == 0) {
            // Verifica si la nave nodriza está viva y si el contador de iteraciones es par
            if (this.xPos > 0) {
                // Si la nave nodriza no está en el borde izquierdo, se mueve a la izquierda
                this.xPos = this.xPos - this.cambioX;
            } else {
                // Si la nave nodriza llega al borde izquierdo, se reinicia a su posición inicial
                this.xPos = Constantes.posXInicialNaveNodriza;
            }
        }

        return this.xPos; // Devuelve la nueva posición horizontal de la nave nodriza
    }



    public void quitarNave() {
        if (contador < 300) {
            // Si el contador de destrucción es menor a 300, cambia la imagen para mostrar la destrucción

            contador++;
        } else {
            // Cuando el contador llega a 300, reinicia la posición de la nave nodriza
            this.xPos = Constantes.posXInicialNaveNodriza;
        }
    }

    @Override
    public void run() {
        // Método run que se ejecuta al iniciar el hilo de la nave nodriza
        while (true) {
            // Bucle infinito para mantener la nave nodriza en movimiento
            try {
                Thread.sleep(100);
                // Pausa el hilo por 100 milisegundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.desplazarNave();
        }
    }

    @Override
    public void actualizar() {
        if (this.estaVivo == false) {
            // Si la nave nodriza no está viva, se maneja su destrucción
            this.quitarNave();
        }
        // Método para actualizar la posición de la nave nodriza
        this.desplazarNave();
    }
}
