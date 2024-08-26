package logica;

import Recursos.Audio;
import Recursos.Constantes;

public class Proyectil extends Entidad {

    private Escudo[] arregloEscudos;
    private NaveNodriza naveNodriza;
    /**** VARIABLES ****/

    private boolean estaDisparando = false;

    /**** Constructor ****/

    public Proyectil(Escudo[] arregloEscudos, NaveNodriza naveNodriza) {
        super(Constantes.anchoProyectil, Constantes.altoProyectil, -Constantes.anchoProyectil, Constantes.yPosicionInicialNave - Constantes.altoProyectil, 0, Constantes.unidadesDeDesplazamientos);

        this.arregloEscudos = arregloEscudos;
        this.naveNodriza = naveNodriza;
    }

    /**** Metodos ****/
    public boolean estadisparando() {
        // Metodo para verificar si la nave ha disparado
        return estaDisparando; // Devuelve el true si ha disparado, false si no
    }

    public void setEstaDisparando(boolean estaDisparando) {
        // M�todo para establecer si la nave ha disparado
        this.estaDisparando = estaDisparando;
    }

    public int desplazarProyectil() {
        // M�todo que maneja el movimiento del disparo de la nave
        if (this.estaDisparando == true) {
            // Si la nave ha disparado
            if (this.yPos > -Constantes.altoProyectil) {
                // Si la posici�n Y del disparo es mayor que 0 (no ha llegado al borde superior de la pantalla)
                this.yPos = this.yPos - Constantes.unidadesDeDesplazamientos;
                // Mueve el disparo hacia arriba restando unidadesDeDesplazamientos a la posici�n Y
            } else {
                // Si la posici�n Y del disparo es 0 o menor (ha llegado al borde superior de la pantalla)
                this.estaDisparando = false;
                // Indica que la nave ya no est� disparando
            }
        }
        return yPos;
        // Devuelve la nueva posici�n Y del disparo
    }

    public boolean destruirAlien(Alien alien) {
        // El disparo de la nave destruye un alien
        if (this.yPos < alien.getyPos() + alien.getAlto() // Verifica si la parte superior del proyectil est� por encima de la parte inferior del alien
                && this.yPos + this.alto > alien.getyPos() // Verifica si la parte inferior del proyectil est� por debajo de la parte superior del alien
                && this.xPos + this.ancho > alien.getxPos() // Verifica si la parte derecha del proyectil est� a la derecha de la parte izquierda del alien
                && this.xPos < alien.getxPos() + alien.getAncho()) {    // Verifica si la parte izquierda del proyectil est� a la izquierda de la parte derecha del alien

            // Si todas las condiciones son verdaderas, significa que el proyectil y el alien se est�n superponiendo

            // Reproduce el sonido cuando el alien muere
            Audio.playSound("/Sonidos/sonidoDestruccionDeAlien.wav");

            // Retorna verdadero indicando que hubo una colisi�n
            return true;
        } else {
            // Si no hay colisi�n, retorna falso
            return false;
        }

    }

    private boolean estaElProyectilALaAlturaDeEscudo() {
        // Devuelve verdadero si el proyectil de la nave est� a la altura de los escudos
        if (this.yPos < Constantes.posicionYEscudo + Constantes.altoEscudo
                && this.yPos + this.alto > Constantes.posicionYEscudo) {
            return true; // El proyectil est� dentro del rango vertical de los escudos
        } else {
            return false; // El proyectil no est� dentro del rango vertical de los escudos
        }
    }

    private int numeroEscudoEncontrado() {
        // Devuelve el n�mero del escudo (0, 1, 2 o 3) que est� en la zona del proyectil
        int numeroDeEscudo = -1; // Inicializa el n�mero de escudo como -1, indicando que a�n no se ha encontrado
        int columna = -1; // Inicializa la columna actual en -1 para empezar la b�squeda desde la primera

        // Bucle para buscar el escudo m�s cercano
        while (numeroDeEscudo == -1 && columna < 4) {
            columna++; // Avanza a la siguiente columna (escudo)

            // Verifica si la posici�n del proyectil en x est� dentro del rango de la columna actual del escudo
            if (this.xPos + this.ancho > Constantes.margenVentana + Constantes.posicionXPrimerEscudo + columna *
                    (Constantes.anchoEscudo + Constantes.espacioDeSeparacionEntreEscudos)
                    && this.xPos < Constantes.margenVentana + Constantes.posicionXPrimerEscudo + Constantes.anchoEscudo + columna *
                    (Constantes.anchoEscudo + Constantes.espacioDeSeparacionEntreEscudos)) {
                numeroDeEscudo = columna; // Si est� dentro del rango, guarda el n�mero del escudo actual
            }
        }

        return numeroDeEscudo; // Devuelve el n�mero del escudo encontrado o -1 si no se encuentra ninguno
    }


    private int obtenerAbscisaDeImpactoProyectilConEscudo(Escudo escudo) {
        // Devuelve la abscisa (posici�n x) del proyectil del nave cuando impacta con un escudo
        int xPosProyectil = -1; // Inicializa la posici�n x del proyectil como -1, indicando que a�n no se ha encontrado el impacto

        // Verifica si el proyectil est� en contacto con el escudo
        if (this.xPos + this.ancho > escudo.getxPos() && this.xPos < escudo.getxPos() + Constantes.anchoEscudo) {
            xPosProyectil = this.xPos; // Si est� en contacto, guarda la posici�n x del proyectil
        }

        return xPosProyectil; // Devuelve la posici�n x del proyectil en contacto con el escudo, o -1 si no hay contacto
    }


    public int[] proyectilTocaEscudo(Escudo[] arregloEscudos) {
        // Devuelve el n�mero del escudo tocado y la abscisa (posici�n x) del proyectil
        int[] matrizNumEscudoYPosX = {-1, -1}; // Inicializa el matrizNumEscudoYPosX con -1, indicando que no se ha encontrado ning�n impacto

        if (estaElProyectilALaAlturaDeEscudo()) { // Verifica si el proyectil est� a la altura del escudo
            matrizNumEscudoYPosX[0] = numeroEscudoEncontrado(); // Guarda el n�mero del escudo tocado en matrizNumEscudoYPosX[0]

            if (matrizNumEscudoYPosX[0] != -1) {
                // Guarda la abscisa (posici�n x) del proyectil en contacto con el escudo en matrizNumEscudoYPosX[1]
                matrizNumEscudoYPosX[1] = obtenerAbscisaDeImpactoProyectilConEscudo(arregloEscudos[matrizNumEscudoYPosX[0]]);
            }
        }

        return matrizNumEscudoYPosX; // Devuelve un arreglo con el n�mero del escudo tocado y la abscisa del proyectil, o {-1, -1} si no hubo contacto
    }

    //todo: en lugar de un arreglo podria ser una lista
    public void proyectilDestruyeEscudo(Escudo[] arregloEscudos) {
        int[] matrizNumEscudoYPosX = this.proyectilTocaEscudo(arregloEscudos); // Contiene (-1, -1) o el n�mero del escudo tocado y la abscisa del contacto entre el proyectil y el escudo
        if (matrizNumEscudoYPosX[0] != -1) { // Un escudo ha sido tocado
            // Encuentra la fila del bloque en el escudo tocado y verifica que no sea -1
            int fila = arregloEscudos[matrizNumEscudoYPosX[0]].encontrarFilaDondeSeEncuentraElBloque(
                    arregloEscudos[matrizNumEscudoYPosX[0]].determinarColumnaDondeImpactoMisilEnElEscudo(matrizNumEscudoYPosX[1]));
            if (fila != -1) {

                arregloEscudos[matrizNumEscudoYPosX[0]].romperBloques(matrizNumEscudoYPosX[1], fila); // Destruye los bloques del escudo tocado
                this.yPos = -Constantes.altoProyectil; // Elimina el proyectil y reactiva el ca��n de la nave
            }
        }
    }

    //todo: revisar el nombre de este metodo
    public void proyectilImpactoNaveNodriza(NaveNodriza naveNodriza) {
        // Verifica si el disparo ha impactado en la nave nodriza
        if (this.yPos < naveNodriza.getyPos() + naveNodriza.getAlto() && // Parte superior del disparo es menor que la parte inferior de la nave
                this.yPos + this.alto > naveNodriza.getyPos() && // Parte inferior del disparo es mayor que la parte superior de la nave
                this.xPos + this.ancho > naveNodriza.getxPos() && // Borde derecho del disparo es mayor que el borde izquierdo de la nave
                this.xPos < naveNodriza.getxPos() + naveNodriza.getAncho()) { // Borde izquierdo del disparo es menor que el borde derecho de la nave

            this.estaDisparando = false; // Marca el disparo como inactivo (se ha destruido)
            this.yPos = -Constantes.altoProyectil; // Mueve el disparo fuera de la pantalla
            naveNodriza.vivo = false; // Retorna verdadero para indicar que el disparo ha impactado en la nave nodriza
        } else {
            naveNodriza.vivo = true; // Retorna falso para indicar que no ha habido impacto
        }
    }

    @Override
    public void actualizar() {
        if (estaDisparando) {
            desplazarProyectil();
            proyectilDestruyeEscudo(arregloEscudos);
            proyectilImpactoNaveNodriza(naveNodriza);
        }
    }
}
