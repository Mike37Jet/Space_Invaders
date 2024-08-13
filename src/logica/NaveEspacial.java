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
		// Devuelve la nueva posición de la nave después de un posible movimiento
		if (this.cambioX < 0) {  // Si el cambio en la posición X es negativo (moviéndose a la izquierda)
			if (this.xPos > Constantes.limiteIzquierdoNave) {  // Si la posición actual de la nave está dentro del límite izquierdo
				this.xPos = this.xPos + this.cambioX;  // Actualiza la posición X de la nave moviéndola a la izquierda
			}
		} else if (cambioX > 0) {  // Si el cambio en la posición X es positivo (moviéndose a la derecha)
			if (this.xPos + this.cambioX < Constantes.limiteDerechoNave) {  // Si la nueva posición no supera el límite derecho
				this.xPos = this.xPos + this.cambioX;  // Actualiza la posición X de la nave moviéndola a la derecha
			}
		}

		return this.xPos;  // Devuelve la nueva posición X de la nave
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
			//this.destruirNave(); // Llama al método que maneja la destrucción de la nave
		}else {
			this.desplazarNave();
		}
	}
}
