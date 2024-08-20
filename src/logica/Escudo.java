package logica;

import Recursos.Audio;
import Recursos.Constantes;

public class Escudo extends Entidad {

    /**** Variables ****/

    private final int numeroDeFilasEnEscudo = Constantes.altoEscudo / Constantes.dimensionesDeBloqueDeEscudo;
    private final int numeroDeColumnasEnEscudo = Constantes.anchoEscudo / Constantes.dimensionesDeBloqueDeEscudo;

    // Matriz que contiene los bloques que componen al escudo verdadero es que si hay escudo y falso que no hay
    boolean matrizEscudo[][] = new boolean[numeroDeFilasEnEscudo][numeroDeColumnasEnEscudo];

    //Constructor

    public Escudo(int xPos) {
        super(Constantes.anchoEscudo, Constantes.altoEscudo, xPos, Constantes.posicionYEscudo, 0, 0);
        this.crearEscudo();
    }

    /**** Metodos ****/

// Creación de la matriz inicial asociada al escudo sin daño
    public void crearEscudo() {
        // Inicializa todas las casillas de la matriz con true, indicando que todas las partes del escudo están intactas
        for (int fila = 0; fila < numeroDeFilasEnEscudo; fila++) {
            for (int columna = 0; columna < numeroDeColumnasEnEscudo; columna++) {
                matrizEscudo[fila][columna] = true;
            }
        }

        // Rellena las casillas sin bloque en la matriz con false para crear el diseño

        for (int columna = 0; columna < 6; columna++) {
            for (int fila = 0; fila < 2; fila++) {
                matrizEscudo[fila][columna] = false;
                matrizEscudo[fila][numeroDeColumnasEnEscudo - columna - 1] = false;
            }
        }


        for (int columna = 0; columna < 4; columna++) {
            for (int fila = 2; fila < 4; fila++) {
                matrizEscudo[fila][columna] = false;
                matrizEscudo[fila][numeroDeColumnasEnEscudo - columna - 1] = false;
            }
        }

        for (int columna = 0; columna < 2; columna++) {
            for (int fila = 4; fila < 6; fila++) {
                matrizEscudo[fila][columna] = false;
                matrizEscudo[fila][numeroDeColumnasEnEscudo - columna - 1] = false;
            }
        }

        for (int fila = 18; fila < numeroDeFilasEnEscudo; fila++) {
            for (int columna = 10; columna < numeroDeColumnasEnEscudo - 10; columna++) {
                matrizEscudo[fila][columna] = false;
            }
        }

        for (int columna = 12; columna < numeroDeColumnasEnEscudo - 12; columna++) {
            for (int fila = 16; fila < 18; fila++) {
                matrizEscudo[fila][columna] = false;
                matrizEscudo[fila][numeroDeColumnasEnEscudo - columna - 1] = false;
            }
        }

        for (int columna = 14; columna < numeroDeColumnasEnEscudo - 14; columna++) {
            for (int fila = 14; fila < 16; fila++) {
                matrizEscudo[fila][columna] = false;
                matrizEscudo[fila][numeroDeColumnasEnEscudo - columna - 1] = false;
            }
        }

    }


    public int determinarColumnaDondeImpactoMisilEnElEscudo(int posicionXProyectil) {
        // Encuentra la columna del escudo en el array asociada al punto donde el misil ha tocado el escudo

        int columna = -1; // Inicializa la variable columna con -1 (valor por defecto que indica que la columna aún no ha sido encontrada)

        // Calcula la columna en la que el misil ha tocado el escudo:
        // Restamos la posición X del escudo de la posición X del misil
        // Luego dividimos el resultado por el tamaño de cada bloque del escudo
        // Esto nos da la columna del bloque dentro del escudo
        columna = (posicionXProyectil - this.xPos) / Constantes.dimensionesDeBloqueDeEscudo;

        // Devuelve la columna encontrada
        return columna;
    }


    public int encontrarFilaDondeSeEncuentraElBloque(int columna) {
        // Encuentra el primer bloque desde el fondo en la columna especificada del escudo
        // o devuelve -1 si no hay ningún bloque en esa columna.

        // Comienza desde la fila más baja del escudo
        int fila = numeroDeFilasEnEscudo - 1;

        // Mientras haya filas por revisar y el bloque en la posición actual sea falso (es decir, no está presente)

        while (fila >= 0 && matrizEscudo[fila][columna] == false) {
            // Baja una fila (mueve hacia arriba)
            fila--;
        }

        // Devuelve la fila donde se encontró el bloque o -1 si no se encontró ningún bloque en esa columna
        return fila;
    }


    private void eliminarBloques(int fila, int columna) {
        // Elimina hasta 6 bloques en la columna especificada, comenzando desde la fila dada y moviéndose hacia abajo,
        // siempre que existan bloques para eliminar.
        for (int contador = 0; contador < 6; contador++) {
            if (fila - contador >= 0) { // Verifica si la fila actual está dentro de los límites del escudo
                matrizEscudo[fila - contador][columna] = false; // Elimina el bloque en la posición (fila - contador, columna)
                // También elimina el bloque en la columna adyacente a la derecha si está dentro de los límites
                if (columna < numeroDeColumnasEnEscudo - 1) {
                    matrizEscudo[fila - contador][columna + 1] = false;
                }
            }
        }
    }


    public void romperBloques(int posicionXProyectil) {
        // Récapitule les 3 méthodes qui précédent
        Audio.playSound("/Sonidos/sonidoImactoDisparoContraEscudo.wav");
        int columna = this.determinarColumnaDondeImpactoMisilEnElEscudo(posicionXProyectil);
        this.eliminarBloques(encontrarFilaDondeSeEncuentraElBloque(columna), columna);
    }

    public int encontrarBloqueSuperior(int columna) {
        // Encuentra el primer bloque en la columna comenzando desde la parte superior o devuelve -1 si no se encuentra
        int fila = 0;
        if (columna != -1) {
            // Recorre las filas desde la parte superior hacia abajo
            while (fila < numeroDeFilasEnEscudo && matrizEscudo[fila][columna] == false) {
                fila++;
            }
        }
        return fila;
    }


    private void eliminarBloquesDesdeArribaEnLaMatrizBooleanDelEscudo(int fila, int columna) {
        // Elimina los primeros 6 bloques en la columna comenzando desde la fila dada si existen
        for (int contador = 0; contador < 6; contador++) {
            // Verifica que la fila es válida y que la columna no es -1
            if (fila + contador < numeroDeFilasEnEscudo && columna != -1) {
                matrizEscudo[fila + contador][columna] = false;
                // También elimina el bloque a la derecha si la columna es válida
                if (columna < numeroDeColumnasEnEscudo - 1) {
                    matrizEscudo[fila + contador][columna + 1] = false;
                }
            }
        }
    }


    public void romperBloquesDesdeArriba(int posXDisparoAlien) {
        // Reproduce el sonido de romper bloques
        Audio.playSound("/Sonidos/sonidoImactoDisparoContraEscudo.wav");
        // Determina la columna en la que impactó el misil en el escudo
        int columna = this.determinarColumnaDondeImpactoMisilEnElEscudo(posXDisparoAlien);
        // Encuentra el primer bloque en la columna desde la parte superior y lo elimina junto con los bloques debajo de él
        this.eliminarBloquesDesdeArribaEnLaMatrizBooleanDelEscudo(encontrarBloqueSuperior(columna), columna);
    }

    public int getNumeroDeFilasEnEscudo() {
        return numeroDeFilasEnEscudo;
    }

    public int getNumeroDeColumnasEnEscudo() {
        return numeroDeColumnasEnEscudo;
    }

    public boolean[][] getMatrizEscudo() {
        return matrizEscudo;
    }

    @Override
    public void actualizar() {

    }
}