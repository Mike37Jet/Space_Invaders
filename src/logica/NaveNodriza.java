package logica;

import javax.swing.ImageIcon;

import Recursos.Audio;

import Recursos.Constantes;

public class NaveNodriza extends Entidad {

    /**** VARIABLES ****/

    public Audio musicaNaveNodriza = new Audio("/Sonidos/sonidoNaveNodriza.wav");
    public Audio musicaNaveNodrizaDestruida = new Audio("/Sonidos/sonidoNaveNodrizaDestruida.wav");
    public static int contadorDeIteraciones = 0;
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
        // Calcula la nueva posici�n de la nave nodriza despu�s del posible movimiento
        if (this.estaVivo && contadorDeIteraciones % 2 == 0) {
            // Verifica si la nave nodriza est� viva y si el contador de iteraciones es par
            if (this.xPos > 0) {
                // Si la nave nodriza no est� en el borde izquierdo, se mueve a la izquierda
                this.xPos = this.xPos - this.cambioX;
            } else {
                // Si la nave nodriza llega al borde izquierdo, se reinicia a su posici�n inicial
                this.xPos = Constantes.posXInicialNaveNodriza;
            }
        }

        return this.xPos; // Devuelve la nueva posici�n horizontal de la nave nodriza
    }



    public void quitarNave() {
        if (contador < 300) {
            // Si el contador de destrucci�n es menor a 300, cambia la imagen para mostrar la destrucci�n
            this.xPos = Constantes.posXInicialNaveNodriza;
            contador++;
        } else {
            // Cuando el contador llega a 300, reinicia la posici�n de la nave nodriza
            this.xPos = Constantes.posXInicialNaveNodriza;
        }
    }

    @Override
    public void run() {
        // Método run que se ejecuta al iniciar el hilo de la nave nodriza
        while (true) {
            // Bucle infinito para mantener la nave nodriza en movimiento
            try {
                Thread.sleep(100); // Pausa el hilo por 100 milisegundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            contadorDeIteraciones++;
            actualizar();

            // Verifica si se ha alcanzado el número de iteraciones para pausar la aparición
            if (contadorDeIteraciones % 25 == 0) {
                // Aquí la nave debería "desaparecer" o detener su movimiento por un tiempo
                System.out.println("Nave nodriza desaparece temporalmente...");

                try {
                    Thread.sleep(10000); // Pausa el hilo por 5 segundos (ajusta este tiempo según necesites)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Nave nodriza reaparece!");
            }
        }
    }

    @Override
    public void actualizar() {
        if (this.estaVivo == false) {
            // Si la nave nodriza no est� viva, se maneja su destrucci�n
            this.quitarNave();
        }
        // M�todo para actualizar la posici�n de la nave nodriza
        this.desplazarNave();
    }
}
