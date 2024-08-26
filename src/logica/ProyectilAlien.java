package logica;


import Recursos.Audio;

import Recursos.Constantes;

public class ProyectilAlien extends Entidad {


    private ColmenaDeAliens colmenaDeAliens;
    private Escudo[] arregloEscudos;
    private NaveEspacial naveEspacial;
    private int contadorDisparosAlien = 0;
    /**** Variables ****/


    /**** CONSTRUCTEUR ****/

    public ProyectilAlien(ColmenaDeAliens matrizPosicionALien, Escudo[] arregloEscudos, NaveEspacial naveEspacial) {
        super(Constantes.anchoDisparoAlien, Constantes.altoDisparoAlien, matrizPosicionALien.elegirPosicionDeAlienParaDisparar()[0] + Constantes.anchoAlien / 2 - 1, matrizPosicionALien.elegirPosicionDeAlienParaDisparar()[1] + Constantes.alturaAlien, 0, Constantes.espacioDeDesplazamientoEnYDisparoAlien);
        this.colmenaDeAliens = matrizPosicionALien;
        this.arregloEscudos = arregloEscudos;
        this.naveEspacial = naveEspacial;

    }

    @Override
    public void verificarTiempo() {
        double intervaloDeDibujo = 200000000 / Constantes.FPS; //frames por seundo
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

    /**** METHODES ****/

    public void desplazarDisparoAlien() {

        // Verifica si el contador de iteraciones es m�ltiplo de 4
        if (contadorDeIteraciones % 4 == 0) {
            // Si la posici�n y del disparo es menor que 600(Tama�o de la pantalla), desplaza el disparo hacia abajo
            if (this.yPos < Constantes.alturaVentana) {
                this.yPos += Constantes.espacioDeDesplazamientoEnYDisparoAlien;
            }
        }
    }


    private boolean estaElDisparoALaAlturaDeLosEscudos() {
        // Comprueba si el disparo del alien�gena est� a la altura de los escudos
        if (this.yPos < Constantes.posicionYEscudo + Constantes.altoEscudo &&
                this.yPos + this.alto > Constantes.posicionYEscudo) {
            // Si el disparo est� en la altura de los escudos, retorna true
            return true;
        } else {
            // Si el disparo no est� en la altura de los escudos, retorna false
            return false;
        }
    }


    private int determinarEscudoDeImpacto() {
        // Inicializa el n�mero del escudo a -1, indicando que a�n no se ha encontrado un escudo cercano.
        int numeroDeEscudo = -1;
        // Inicializa la columna a -1 para comenzar la b�squeda desde la primera columna de escudos.
        int columna = -1;

        // Mientras no se haya encontrado un escudo y la columna actual sea menor que 4.
        while (numeroDeEscudo == -1 && columna < 4) {
            // Avanza a la siguiente columna.
            columna++;

            // Verifica si el disparo del alien est� en la misma �rea horizontal que el escudo actual.
            if (this.xPos + this.ancho - 1 > Constantes.margenVentana + Constantes.posicionXPrimerEscudo + columna * (Constantes.anchoEscudo + Constantes.espacioDeSeparacionEntreEscudos)
                    && this.xPos + 1 < Constantes.margenVentana + Constantes.posicionXPrimerEscudo + Constantes.anchoEscudo + columna * (Constantes.anchoEscudo + Constantes.espacioDeSeparacionEntreEscudos)) {
                // Si el disparo est� en el rango horizontal del escudo, guarda el �ndice de la columna del escudo encontrado.
                numeroDeEscudo = columna;
            }
        }

        // Devuelve el n�mero del escudo m�s cercano al disparo del alien, o -1 si no se ha encontrado ning�n escudo en la trayectoria del disparo.
        return numeroDeEscudo;
    }


    private int obtenerAbscisaDeImpactoConEscudo(Escudo escudo) {
        // Inicializa la posici�n en x del disparo del alien a -1, lo que indica que no se ha registrado el impacto a�n.
        int posXDisparoAlien = -1;

        // Verifica si la posici�n horizontal del disparo del alien se superpone con el �rea del escudo.
        if (this.xPos + this.ancho > escudo.getxPos() && this.xPos < escudo.getxPos() + Constantes.anchoEscudo) {
            // Si hay una superposici�n, guarda la posici�n en x del disparo del alien.
            posXDisparoAlien = this.xPos;
        }

        // Devuelve la posici�n en x del disparo del alien si hay impacto con el escudo, o -1 si no hay impacto.
        return posXDisparoAlien;
    }


    public int[] obtenerImpactoConEscudo() { // Devuelve el n�mero del escudo tocado y la posici�n en x del disparo
        // Inicializa un arreglo para almacenar el n�mero del escudo tocado y la posici�n en x del disparo
        // Inicialmente, se establece en {-1, -1} indicando que no se ha detectado un impacto
        int[] resultado = {-1, -1};

        // Verifica si el disparo del alien est� a la altura de los escudos
        if (this.estaElDisparoALaAlturaDeLosEscudos()) {
            // Si el disparo est� a la altura de los escudos, determina el n�mero del escudo tocado
            resultado[0] = this.determinarEscudoDeImpacto();

            // Si se ha encontrado un escudo (es decir, el �ndice no es -1)
            // calcula la posici�n en x del impacto del disparo con el escudo
            if (resultado[0] != -1) {
                resultado[1] = this.obtenerAbscisaDeImpactoConEscudo(
                        arregloEscudos[resultado[0]]
                );
            }
        }
        // Devuelve el arreglo con el n�mero del escudo tocado y la posici�n en x del disparo.
        return resultado;
    }


    public void detectarImpactoConEscudo(Escudo[] arregloEscudos) {
        // Obtiene el impacto del disparo con el escudo, devolviendo el n�mero del escudo y la posici�n en x del impacto.
        int[] resultado = this.obtenerImpactoConEscudo(); // Contiene (-1, -1) o el n�mero del escudo tocado y la posici�n en x del disparo

        // Verifica si se ha tocado un escudo (es decir, el n�mero del escudo no es -1).
        if (resultado[0] != -1) {
            // Obtiene el n�mero de fila donde se encuentra el bloque afectado en el escudo tocado.
            int filaBloque = arregloEscudos[resultado[0]].encontrarBloqueSuperior(
                    arregloEscudos[resultado[0]].determinarColumnaDondeImpactoMisilEnElEscudo(resultado[1])
            );

            // Verifica que el bloque encontrado est� dentro del rango v�lido (no debe ser 27, ya que es el limite del escudo).
            if (filaBloque != -1 && filaBloque != 27) {
                // Si el bloque es v�lido, destruye los bloques desde arriba en el escudo tocado.
                arregloEscudos[resultado[0]].romperBloquesDesdeArriba(resultado[1]);
                // Mueve el disparo del alien a una posici�n fuera de la pantalla (700) para simular su destrucci�n.
                this.yPos = 700;
            }
        }
    }


    public boolean detectarImpactoNave(NaveEspacial naveEspacial) {
        // Verifica si el dispar del alien ha impactado en la nave espacial
        // Primero, verifica si hay superposici�n vertical entre el disparo y la nave
        if (this.yPos < naveEspacial.getyPos() + naveEspacial.getAlto() &&
                this.yPos + this.alto > naveEspacial.getyPos() &&
                // Luego, verifica si hay superposici�n horizontal entre el disparo y la nave
                this.xPos + this.ancho > naveEspacial.getxPos() &&
                this.xPos < naveEspacial.getxPos() + naveEspacial.getAncho()) {
            // Si hay impacto, se mueve el disparo fuera del �rea visible
            this.yPos = 700;
            // Reproduce el sonido de destrucci�n de la nave
            Audio.playSound("/Sonidos/sonidoDestruccionNave.wav");
            // Retorna verdadero indicando que hubo un impacto
            return true;
        } else {
            // Si no hay impacto, retorna falso
            return false;
        }
    }


    @Override
    public void actualizar() {
        desplazarDisparoAlien();
        if (this.estaElDisparoALaAlturaDeLosEscudos()) {
            this.detectarImpactoConEscudo(arregloEscudos);

        }

        // Verifica si el disparo del alien ha impactado en la nave espacial
        if (this.detectarImpactoNave(naveEspacial)) {
            naveEspacial.destruirNave();
            //

        }

        if (this.yPos >= Constantes.alturaVentana) {
            if(colmenaDeAliens.elegirPosicionDeAlienParaDisparar()[0] != -1){
                this.yPos = colmenaDeAliens.elegirPosicionDeAlienParaDisparar()[1] + Constantes.alturaAlien;
                this.xPos = colmenaDeAliens.elegirPosicionDeAlienParaDisparar()[0] + Constantes.anchoAlien / 2 - 1;
            }

        }


    }
}
