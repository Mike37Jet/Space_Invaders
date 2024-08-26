package logica;

import Recursos.Constantes;

import java.io.Serializable;

public abstract class Entidad implements Runnable, Serializable {


    protected int ancho, alto, xPos, yPos, cambioX, cambioY;
    protected boolean vivo = true;
    protected Nivel nivel;
    protected boolean pausado = false;
    public volatile boolean hiloActivo = true;
    protected int contadorDeIteraciones = 0;
    protected volatile boolean naveEstaDestruida;

    public Entidad(int ancho, int alto, int xPos, int yPos, int cambioX, int cambioY) {

        this.ancho = ancho;
        this.alto = alto;
        this.xPos = xPos;
        this.yPos = yPos;
        this.cambioX = cambioX;
        this.cambioY = cambioY;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }


    public void setCambioX(int cambioX) {
        this.cambioX = cambioX;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }


    public void detenerSonido() {
        // Metodo para detener el sonido de la entidad
    }


    @Override
    public void run() {
        while (this.hiloActivo) {
            if(nivel.getNaveEspacial().estaLaNaveEspacialDestruida()){
                this.hiloActivo = false;
            } else if (!pausado && !this.naveEstaDestruida) {
                actualizar();
                contadorDeIteraciones++;
            	verificarTiempo();
            } else {
            	estaPausado();
            	verificarTiempo();
            }
        }
    }

    public synchronized void estaPausado() {
        pausado = !pausado;
        if (pausado) {
            try {
                wait(); // Pausa el hilo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restaura el estado de interrupción del hilo
            }
        } else {
            notifyAll(); // Reanuda todos los hilos en espera
        }
    }

    //public abstract boolean estaPausado();
    public abstract void actualizar();


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

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public boolean estaLaNaveEspacialDestruida() {
        return naveEstaDestruida;
    }
}
