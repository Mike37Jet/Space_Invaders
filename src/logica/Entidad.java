package logica;

import Recursos.Constantes;

import java.awt.Image;
import java.io.Serializable;

import javax.swing.ImageIcon;

public abstract class Entidad implements Runnable, Serializable {


	protected int ancho, alto, xPos, yPos, cambioX, cambioY;
	protected boolean estaVivo = true;
	protected Nivel nivel;
	public boolean hilo = true;
	protected int contadorDeIteraciones = 0;

	public Entidad( int ancho, int alto, int xPos, int yPos, int cambioX, int cambioY) {

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

	public boolean isEstaVivo() {
		return estaVivo;
	}

	public void setEstaVivo(boolean estaVivo) {
		this.estaVivo = estaVivo;
	}



	public void detenerSonido(){
		// Metodo para detener el sonido de la entidad
	};

	@Override
	public void run() {
		System.out.println("Hilo de entidad");
		while (hilo) {
			contadorDeIteraciones++;
			actualizar();
			verificarTiempo();
			//if (!pausado) {
			//	run();
			//	verificarTiempo();
			//} else {
			//	estaPausado();
			//	verificarTiempo();
			//}
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
}
