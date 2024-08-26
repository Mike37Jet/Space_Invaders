package presentacion;

import logica.Escudo;
import Recursos.Constantes;
import logica.Nivel;

import java.awt.*;

public class InterfazGraficaDeEscudo extends InterfazGrafica {
    private Escudo[] escudos;

    public InterfazGraficaDeEscudo() {
        super();
        //spriteEnUso = sprites;
    }

    @Override
    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    @Override
    public void dibujar(Graphics2D g2) {
        for(Escudo escudo : escudos){
            for (int fila = 0; fila < escudo.getNumeroDeFilasEnEscudo(); fila++) {
                // Bucle para recorrer todas las columnas del arreglo del escudo
                for (int columna = 0; columna < escudo.getNumeroDeColumnasEnEscudo(); columna++) {
                    // Verifica si la celda en la matriz del escudo es verdadera (es decir, que el bloque está presente)
                    if (escudo.getMatrizEscudo()[fila][columna] == true) {
                        // Si es verdadero, establece el color como verde
                        g2.setColor(Color.CYAN);
                    } else {
                        // Si no, establece el color como negro (para las áreas dañadas)
                        g2.setColor(Color.BLACK);
                    }
                    // Dibuja un rectángulo relleno en la posición (x, y) calculada con la dimensión especificada
                    // La posición se determina sumando las coordenadas xPos e yPos del escudo
                    // Basicamente dibuja bloquesitos
                    g2.fillRect(
                            escudo.getxPos() + Constantes.dimensionesDeBloqueDeEscudo * columna, // Coordenada x de la esquina superior izquierda del rectángulo
                            escudo.getyPos() + Constantes.dimensionesDeBloqueDeEscudo * fila,   // Coordenada y de la esquina superior izquierda del rectángulo
                            Constantes.dimensionesDeBloqueDeEscudo,                      // Ancho del rectángulo
                            Constantes.dimensionesDeBloqueDeEscudo                       // Alto del rectángulo
                    );
                }
            }
        }
    }


    public void setEscudos(Escudo[] escudos) {
        this.escudos = escudos;
    }

    @Override
    public void run() {

    }
}
