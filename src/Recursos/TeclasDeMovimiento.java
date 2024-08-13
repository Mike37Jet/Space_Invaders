package Recursos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

import logica.NaveEspacial;
import logica.Proyectil;

public class TeclasDeMovimiento implements KeyListener {

    private static final int TIEMPO_ENTRE_DISPAROS_MS = 750; // Tiempo en milisegundos
    private NaveEspacial naveEspacial;
    private Proyectil proyectil;
    private Timer timer;
    private boolean puedeDisparar;

    public TeclasDeMovimiento(NaveEspacial naveEspacial, Proyectil proyectil) {
        this.naveEspacial = naveEspacial;
        this.proyectil = proyectil;
        // Inicializar el flag de disparo
        puedeDisparar = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (naveEspacial.isEstaVivo()) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                naveEspacial.setCambioX(Constantes.espacioDesplazamientoXNaveEspacial);
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                naveEspacial.setCambioX(-Constantes.espacioDesplazamientoXNaveEspacial);
            } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                if (puedeDisparar) {
                    Audio.playSound("/Sonidos/sonidoProyectil.wav");
                    proyectil.setyPos(Constantes.yPosicionInicialNave - Constantes.altoProyectil);
                    proyectil.setxPos(naveEspacial.getxPos() + Constantes.anchoNave / 2 - 1);
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
    }

    @Override
    public void keyReleased(KeyEvent e) {
        naveEspacial.setCambioX(0);
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // No se necesita implementar
    }
}
