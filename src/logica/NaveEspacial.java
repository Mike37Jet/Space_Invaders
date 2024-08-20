package logica;

import javax.swing.*;

import Recursos.Audio;

import Recursos.Constantes;
import presentacion.DetectorTeclas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class NaveEspacial extends Entidad {
    private transient DetectorTeclas detectorTeclas;
    private Proyectil proyectil;
    /**** Variables ****/
    private Timer timer;
    private int contador = 0;
    private static final int TIEMPO_ENTRE_DISPAROS_MS = 750;
    /**** Constructor ****/
    private Audio audio;

    private boolean puedeDisparar;

    public NaveEspacial(Proyectil proyectil) {
        super(Constantes.anchoNave, Constantes.altoNave, Constantes.xPosicionInicialNave, Constantes.yPosicionInicialNave, 0, 0);
        this.proyectil = proyectil;
        puedeDisparar = true;
    }

    public void mover() {
        System.out.println("Moviendo");
        System.out.println("Tecla: " + detectorTeclas.tecla);
        if (detectorTeclas.tecla == Tecla.DERECHA) {
            System.out.println("Moviendo a la derecha");
            this.xPos += Constantes.espacioDesplazamientoXNaveEspacial;

        } else if (detectorTeclas.tecla == Tecla.IZQUIERDA) {
            System.out.println("Moviendo a la izquierda");
            this.xPos -= Constantes.espacioDesplazamientoXNaveEspacial;

        } else if (detectorTeclas.tecla == Tecla.DISPARAR) {
            if (puedeDisparar) {
                Audio.playSound("/Sonidos/sonidoProyectil.wav");
                proyectil.setyPos(Constantes.yPosicionInicialNave - Constantes.altoProyectil);
                proyectil.setxPos(getxPos() + Constantes.anchoNave / 2 - 1);
                // Se resta 1 para visualmente ajustar la posición del proyectil
                proyectil.setEstaDisparando(true);
                puedeDisparar = false; // No se puede disparar hasta que el timer reinicie

                // Inicializar el timer para permitir el próximo disparo después de 1 segundo
                timer = new Timer(TIEMPO_ENTRE_DISPAROS_MS, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        puedeDisparar = true; // Permitir disparar nuevamente
                    }
                });
                timer.setRepeats(false); // El timer no se repite automáticamente
                timer.start(); // Iniciar el timer
            }
        }

    }

    /**** Metodos ****/
    public void desplazarNave() {
        // Devuelve la nueva posici�n de la nave despu�s de un posible movimiento
        if (this.cambioX < 0) {  // Si el cambio en la posici�n X es negativo (movi�ndose a la izquierda)
            if (this.xPos > Constantes.limiteIzquierdoNave) {  // Si la posici�n actual de la nave est� dentro del l�mite izquierdo
                this.xPos = this.xPos + this.cambioX;  // Actualiza la posici�n X de la nave movi�ndola a la izquierda
            }
        } else if (cambioX > 0) {  // Si el cambio en la posici�n X es positivo (movi�ndose a la derecha)
            if (this.xPos + this.cambioX < Constantes.limiteDerechoNave) {  // Si la nueva posici�n no supera el l�mite derecho
                this.xPos = this.xPos + this.cambioX;  // Actualiza la posici�n X de la nave movi�ndola a la derecha
            }
        }
    }

    //todo: revisar ya que la nave esta siendo dibujada y destruida al mismo tiempo
    //public void destruirNave() {
    //	if(contador < 300) {
    //		if(ManejadorDeEventosRepintar.contadorDeIteraciones % 2 == 0) {
    //			super.imageIcon = new ImageIcon(getClass().getResource(super.strImg2)); // Cambia a la imagen de la nave destruida
    //		} else {
    //			super.imageIcon = new ImageIcon(getClass().getResource(super.strImg3)); // Cambia a la segunda imagen de la nave destruida
    //		}
    //		contador++;
    //	} else {
    //		Main.juego = false; // Termina el juego si la nave ha sido destruida durante 300 pasos
    //	}
    //	super.img = this.imageIcon.getImage(); // Actualiza la imagen de la nave
    //}

    //public boolean estaPausado() {
    //	if (detectorTeclas.tecla == Tecla.PAUSAR) {
    //		detectorTeclas.tecla = null;
    //		this.pausado = !this.pausado;
    //		if (pausado) {
    //			audio.stop();
    //		} else {
    //			audio.play();
    //		}
    //	}
    //	return this.pausado;
    //}
    @Override
    public void detenerSonido() {
        audio.stop();
    }

    @Override
    public void actualizar() {

        if (this.estaVivo == false) {
            //this.destruirNave(); // Llama al m�todo que maneja la destrucci�n de la nave
        } else {

            mover();
        }
    }

    public void setDetectorTeclas(DetectorTeclas controles) {
        this.detectorTeclas = controles;
    }
}
