package presentacion;

import logica.Alien;
import logica.Nivel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InterfazGraficaDeColmena extends InterfazGrafica {
    private final InterfazGraficaDeAlien[] interfazGraficaAliens;
    protected Alien[][] colmena;

    public InterfazGraficaDeColmena(InterfazGraficaDeAlien... interfazGraficaAliens) {
        super();
        this.interfazGraficaAliens = interfazGraficaAliens;
    }

    @Override
    public void dibujar(Graphics2D g2) {
        BufferedImage image = siguienteImagen();
        for (int columna = 0; columna < 10; columna++) { // Recorre cada columna de la colmena
            // Crear el alien en la primera fila
            for (int fila = 0; fila < 1; fila++) { // Recorre las filas 1 y 2
                // Crear los aliens en las filas 1 y 2
                if (colmena[fila][columna] != null) {
                    this.interfazGraficaAliens[0].setEntidad(colmena[fila][columna]);
                    this.interfazGraficaAliens[0].dibujar(g2);
                }
            }
            for (int fila = 1; fila < 3; fila++) { // Recorre las filas 1 y 2
                // Crear los aliens en las filas 1 y 2
                if (colmena[fila][columna] != null) {
                    this.interfazGraficaAliens[1].setEntidad(colmena[fila][columna]);
                    this.interfazGraficaAliens[1].dibujar(g2);
                }
            }
            for (int fila = 3; fila < 4; fila++) { // Recorre las filas 3 y 4
                // Crear los aliens en las filas 3 y 4
                if (colmena[fila][columna] != null) {
                    this.interfazGraficaAliens[2].setEntidad(colmena[fila][columna]);
                    this.interfazGraficaAliens[2].dibujar(g2);
                }
            }
            for (int fila = 4; fila < 5; fila++) { // Recorre las filas 3 y 4
                // Crear los aliens en las filas 3 y 4
                if (colmena[fila][columna] != null) {
                    this.interfazGraficaAliens[3].setEntidad(colmena[fila][columna]);
                    this.interfazGraficaAliens[3].dibujar(g2);
                }
            }
        }
    }

    @Override
    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public void setAliens(Alien[][] colmena) {
        this.colmena = colmena;
    }

    @Override
    public void run() {

    }
}
