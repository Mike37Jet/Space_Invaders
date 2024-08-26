package logica;

import javax.swing.*;

import Recursos.Audio;

import Recursos.Constantes;
import presentacion.DetectorTeclas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        if (pausado) {
            return; // Si está pausado, no hacer nada
        }
        if (detectorTeclas.tecla == Tecla.DERECHA) {
            if (sePuedeDesplazarNave()) {
                this.xPos += Constantes.espacioDesplazamientoXNaveEspacial;
            }
        } else if (detectorTeclas.tecla == Tecla.IZQUIERDA) {
            if (sePuedeDesplazarNave()) {
                this.xPos -= Constantes.espacioDesplazamientoXNaveEspacial;
            }
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
        } else if (detectorTeclas.tecla == Tecla.PAUSAR) {
            detectorTeclas.tecla = null;
            estaPausado();
        }

    }

    /**** Metodos ****/
    public boolean sePuedeDesplazarNave() {
        if (this.xPos < Constantes.limiteIzquierdoNave) {
            this.xPos = Constantes.limiteIzquierdoNave + 1;
            return false;
        }
        if (this.xPos > Constantes.limiteDerechoNave) {
            this.xPos = Constantes.limiteDerechoNave - 1;
            return false;
        }
        return true;
    }

    //todo: revisar ya que la nave esta siendo dibujada y destruida al mismo tiempo
    public void destruirNave() {
        this.naveEstaDestruida = true; // Cambia el estado de la nave a destruida

    	//if(contador < 300) {
    	//	if(contadorDeIteraciones % 2 == 0) {
    	//		super.imageIcon = new ImageIcon(getClass().getResource(super.strImg2)); // Cambia a la imagen de la nave destruida
    	//	} else {
    	//		super.imageIcon = new ImageIcon(getClass().getResource(super.strImg3)); // Cambia a la segunda imagen de la nave destruida
    	//	}
    	//	contador++;
    	//} else {
    	//	juego = false; // Termina el juego si la nave ha sido destruida durante 300 pasos
    	//}
    	//super.img = this.imageIcon.getImage(); // Actualiza la imagen de la nave
    }



    @Override
    public void detenerSonido() {
        audio.stop();
    }

    @Override
    public void actualizar() {
        if (this.naveEstaDestruida) {
            Thread.currentThread().interrupt();
        } else {
            mover();
        }
    }



    public void setDetectorTeclas(DetectorTeclas controles) {
        this.detectorTeclas = controles;
    }
}
