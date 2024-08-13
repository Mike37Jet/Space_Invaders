package logica;

import javax.swing.ImageIcon;

import Recursos.Audio;
import menu.Main;

import Recursos.Constantes;

public class NaveEspacial extends Entidad {
	
	/**** Variables ****/
	private int contador = 0;
	
	/**** Constructor ****/
	private Audio audio;
		public NaveEspacial() {
			super(Constantes.anchoNave, Constantes.altoNave, Constantes.xPosicionInicialNave,Constantes.yPosicionInicialNave,0,0);
		}
		
		
	/**** Metodos ****/
	public int desplazarNave() {
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

		return this.xPos;  // Devuelve la nueva posici�n X de la nave
	}

	//todo: reviar ya que la nave esta siendo dibujada y destruida al mismo tiempo
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
		if(this.estaVivo == false) {
			//this.destruirNave(); // Llama al m�todo que maneja la destrucci�n de la nave
		}else {
			this.desplazarNave();
		}
	}
}
