package Recursos;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {

	/**** Variables ****/
	private Clip clip; // Almacena el clip de audio que se reproducir�

	/**** Constructor ****/

	public Audio(String sonido) {
		try {
			// Carga el archivo de audio desde el recurso especificado
			AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResource(sonido));
			clip = AudioSystem.getClip(); // Obtiene un nuevo clip de audio
			clip.open(audio); // Abre el clip con el flujo de audio
		} catch (Exception e) {
			e.printStackTrace(); // Imprime informaci�n sobre el error
		}
	}

	/**** M�todos ****/

	public Clip getClip() {
		return clip; // Devuelve el clip de audio
	}

	public void play() {
		clip.start(); // Reproduce el clip de audio
	}

	public void stop() {
		clip.stop(); // Detiene la reproducci�n del clip de audio
	}

	public static void playSound(String sonido) {
		// Metodo est�tico para reproducir un sonido espec�fico
		Audio s = new Audio(sonido); // Crea una instancia de Audio con el sonido dado
		s.play(); // Reproduce el sonido
	}

    public void loop() {
    }
}