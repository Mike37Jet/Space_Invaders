package logica;

import java.util.Random;

import Recursos.Audio;
import Recursos.Constantes;

public class ColmenaDeAliens implements Runnable {

    /**** VARIABLES ****/

    // Matriz que contiene la colmena de los aliens (50)
    // Todo: ahora esta usando los valores  de 5 y 10 directamente en lugar de usar una funcion que determina las dimensiones de la matriz directamente
    private Alien colmenaDeAliens[][] = new Alien[5][10];
    private boolean estaYendoHaciaLaDerecha;
    private boolean esPrimeraImagendelAlien;
    private int velocidad;
    private boolean colmenaViva = true;
    private Nivel nivel;
    private int[] posicionAlienMuerto = {-1, -1}; // Emplacement alien mort dans tableau aliens

    Random random = new Random();

    private int numeroTotalAliens = Constantes.numeroTotalAliens;

    private int contadorDeNumeroDeSonidosAliens = 0;


    /**** CONSTRUCTEUR ****/

    public ColmenaDeAliens() {

        this.crearColmenaDeAliens();
        this.estaYendoHaciaLaDerecha = true;
        this.esPrimeraImagendelAlien = true;
        this.velocidad = Constantes.velocidadAlienigenas;
    }


    /**** METHODES ****/

    private void crearColmenaDeAliens() {
        // M�todo que llena la matriz de aliens
        for (int columna = 0; columna < 10; columna++) { // Recorre cada columna de la colmena
            for (int fila = 0; fila < 1; fila++) {
                // Crear el alien en la primera fila
                this.colmenaDeAliens[fila][columna] = new Alien(
                        Constantes.posicionInicialEnXAlienigenas + (Constantes.anchoAlien + Constantes.espacioHorizontalEntreColumnasAlienigenas) * columna,
                        Constantes.alturaInicialAlienigena + Constantes.espacioVerticalEntreFilasAlienigenas * fila
                );
            }
            for (int fila = 1; fila < 3; fila++) { // Recorre las filas 1 y 2
                // Crear los aliens en las filas 1 y 2
                this.colmenaDeAliens[fila][columna] = new Alien(
                        Constantes.posicionInicialEnXAlienigenas + (Constantes.anchoAlien + Constantes.espacioHorizontalEntreColumnasAlienigenas) * columna,
                        Constantes.alturaInicialAlienigena + Constantes.espacioVerticalEntreFilasAlienigenas * fila
                );
            }
            for (int fila = 3; fila < 4; fila++) { // Recorre las filas 3 y 4
                // Crear los aliens en las filas 3 y 4
                this.colmenaDeAliens[fila][columna] = new Alien(
                        Constantes.posicionInicialEnXAlienigenas + (Constantes.anchoAlien + Constantes.espacioHorizontalEntreColumnasAlienigenas) * columna,
                        Constantes.alturaInicialAlienigena + Constantes.espacioVerticalEntreFilasAlienigenas * fila
                );
            }
            for (int fila = 4; fila < 5; fila++) { // Recorre las filas 3 y 4
                // Crear los aliens en las filas 3 y 4
                this.colmenaDeAliens[fila][columna] = new Alien(
                        Constantes.posicionInicialEnXAlienigenas + (Constantes.anchoAlien + Constantes.espacioHorizontalEntreColumnasAlienigenas) * columna,
                        Constantes.alturaInicialAlienigena + Constantes.espacioVerticalEntreFilasAlienigenas * fila
                );
            }
        }
    }


    private boolean estaTocandoBordeIzquierdo() {
        // Metodo que detecta el borde izquierdo de la ventana
        boolean respuesta = false; // Inicializa la respuesta como falsa
        for (int columna = 0; columna < 10; columna++) { // Recorre cada columna de aliens
            for (int fila = 0; fila < 5; fila++) { // Recorre cada fila de aliens
                if (this.colmenaDeAliens[fila][columna] != null) { // Verifica si hay un alien en la posici�n actual
                    if (this.colmenaDeAliens[fila][columna].getxPos() < Constantes.margenVentana) { // Verifica si el alien est� en el borde izquierdo
                        respuesta = true; // Si se encuentra un alien en el borde izquierdo, cambia la respuesta a verdadera
                        break; // Sale del bucle interior si se encuentra un alien en el borde izquierdo
                    }
                }
            }
        }
        return respuesta; // Devuelve true si al menos un alien est� en el borde izquierdo, false de lo contrario
    }


    private boolean estaTocandoBordeDerecho() {
        // M�todo que detecta el borde derecho de la ventana.
        boolean respuesta = false; // Inicializa la respuesta como falsa
        for (int columna = 0; columna < 10; columna++) { // Recorre cada columna de aliens
            for (int fila = 0; fila < 5; fila++) { // Recorre cada fila de aliens
                if (this.colmenaDeAliens[fila][columna] != null) { // Verifica si hay un alien en la posici�n actual
                    if (this.colmenaDeAliens[fila][columna].getxPos() >
                            Constantes.anchoVentana - Constantes.anchoAlien - Constantes.cambioEnXDxAlien - Constantes.margenVentana) { // Verifica si el alien est� en el borde derecho
                        respuesta = true; // Si se encuentra un alien en el borde derecho, cambia la respuesta a verdadera
                        break; // Sale del bucle interior si se encuentra un alien en el borde derecho
                    }
                }
            }
        }
        return respuesta; // Devuelve true si al menos un alien est� en el borde derecho, false de lo contrario
    }

    //todo: aqui creo que se implementa la interfaz de cambio de direccion por que en una parte se esta repitiendo codigo
    public void cambiarDireccionYBajarColmena() {
        // M�todo que cambia la direcci�n del movimiento de la colmena de aliens y los mueve hacia abajo.
        // Verificar si la colmena est� tocando el borde derecho de la ventana
        if (this.estaTocandoBordeDerecho() == true) {
            // Si est� tocando el borde derecho, mover todos los aliens hacia abajo
            for (int columna = 0; columna < 10; columna++) {
                for (int fila = 0; fila < 5; fila++) {
                    if (this.colmenaDeAliens[fila][columna] != null) {
                        // Actualizar la posici�n vertical de cada alien para moverlo hacia abajo
                        this.colmenaDeAliens[fila][columna].setyPos(this.colmenaDeAliens[fila][columna].getyPos() + Constantes.cambioEnYDyAlien);
                    }
                }
            }
            // Cambiar la direcci�n de movimiento a izquierda
            this.estaYendoHaciaLaDerecha = false;
            // Aumentar la velocidad, pero no superar el l�mite de velocidad (9)
            if (this.velocidad < 9) {
                this.velocidad++;
            }
        } else {
            // Si no est� tocando el borde derecho, verificar si est� tocando el borde izquierdo
            if (this.estaTocandoBordeIzquierdo() == true) {
                // Si est� tocando el borde izquierdo, mover todos los aliens hacia abajo
                for (int columna = 0; columna < 10; columna++) {
                    for (int fila = 0; fila < 5; fila++) {
                        if (this.colmenaDeAliens[fila][columna] != null) {
                            // Actualizar la posici�n vertical de cada alien para moverlo hacia abajo
                            this.colmenaDeAliens[fila][columna].setyPos(this.colmenaDeAliens[fila][columna].getyPos() + Constantes.cambioEnYDyAlien);
                        }
                    }
                }
                // Cambiar la direcci�n de movimiento a derecha
                this.estaYendoHaciaLaDerecha = true;
                // Aumentar la velocidad, pero no superar el l�mite de velocidad (9)
                if (this.velocidad < 9) {
                    this.velocidad++;
                }
            }
        }
    }


    public void desplazarAliens() {
        // M�todo que gestiona el movimiento de los aliens
        // Primero, verifica si hay un alien muerto que debe eliminarse

        if (this.posicionAlienMuerto[0] != -1) {
            eliminarAlienMuerto(posicionAlienMuerto); // Llama al m�todo para eliminar el alien
            posicionAlienMuerto[0] = -1; // Reinicia la posici�n del alien muerto en el arreglo
        }

        // Determina la direcci�n del movimiento de los aliens
        if (this.estaYendoHaciaLaDerecha == true) { // Movimiento hacia la derecha
            for (int columna = 0; columna < 10; columna++) {
                for (int fila = 0; fila < 5; fila++) {
                    // Verifica si hay un alien en la posici�n actual
                    if (this.colmenaDeAliens[fila][columna] != null) {
                        // Mueve el alien hacia la derecha
                        this.colmenaDeAliens[fila][columna].setxPos(
                                this.colmenaDeAliens[fila][columna].getxPos() + Constantes.cambioEnXDxAlien
                        );
                    }
                }
            }
        } else { // Movimiento hacia la izquierda
            for (int columna = 0; columna < 10; columna++) {
                for (int fila = 0; fila < 5; fila++) {
                    // Verifica si hay un alien en la posici�n actual
                    if (this.colmenaDeAliens[fila][columna] != null) {
                        // Mueve el alien hacia la izquierda
                        this.colmenaDeAliens[fila][columna].setxPos(
                                this.colmenaDeAliens[fila][columna].getxPos() - Constantes.cambioEnXDxAlien
                        );
                    }
                }
            }
        }

        // Los aliens emiten un sonido en cada iteraci�n del m�todo
        this.emitirSonidoAlien();

        // Incrementa el contador de sonido
        this.contadorDeNumeroDeSonidosAliens++;

        // Cambia la imagen del alien para crear un efecto visual
        if (this.esPrimeraImagendelAlien == true) {
            this.esPrimeraImagendelAlien = false; // Cambia a la segunda imagen
        } else {
            this.esPrimeraImagendelAlien = true; // Cambia a la primera imagen
        }

        // Actualiza la direcci�n del movimiento si un alien alcanza el borde de la ventana
        this.cambiarDireccionYBajarColmena();
    }


    public void proyectilImpactaAlien(Proyectil proyectil) {
        // Recorre toda la colmena de aliens para verificar si el proyectil ha tocado alguno
        for (int columna = 0; columna < 10; columna++) {
            for (int fila = 0; fila < 5; fila++) {
                // Verifica si hay un alien en la posici�n actual de la colmena
                if (this.colmenaDeAliens[fila][columna] != null) {
                    // Verifica si el proyectil ha destruido el alien en la posici�n actual
                    if (proyectil.destruirAlien(this.colmenaDeAliens[fila][columna]) == true) {
                        // Marca al alien como destruido
                        this.colmenaDeAliens[fila][columna].estaVivo = false;
                        // Mueve el proyectil fuera de la pantalla (lo destruye)
                        proyectil.yPos = -Constantes.altoProyectil;
                        // Registra la posici�n del alien muerto en el arreglo
                        this.posicionAlienMuerto[0] = fila;
                        this.posicionAlienMuerto[1] = columna;

                        // Sale del bucle una vez que se ha destruido un alien
                        break;
                    }
                }
            }
        }
    }

    private void eliminarAlienMuerto(int[] matrizAliensMuertos) {
        // Elimina al alien muerto de la colmena marcando su posici�n como null
        this.colmenaDeAliens[matrizAliensMuertos[0]][matrizAliensMuertos[1]] = null;
        // Disminuye el n�mero total de aliens en la colmena
        this.numeroTotalAliens--;
        if (this.numeroTotalAliens == 0) {
            colmenaViva = false;
        }
    }


    public int[] elegirPosicionDeAlienParaDisparar() {
        // Devuelve la posici�n de un alien elegido al azar en tabAlien en la parte inferior de su columna (l�nea, columna)
        int[] posicionAlienQueVaADisparar = new int[2]; // Inicializa un array para almacenar la posici�n del alien (x, y)

        // Verifica si a�n quedan aliens vivos
        if (this.numeroTotalAliens != 0) {
            do {
                //todo: en esta parte usa una columna base 10 si se hace un una colmena de un numero distino de aliens toca modificar esto
                // Selecciona aleatoriamente una columna del array de aliens
                int columna = random.nextInt(colmenaDeAliens[0].length);
                // Busca el primer alien vivo comenzando desde la parte inferior
                for (int fila = 4; fila >= 0; fila--) {
                    // Si encuentra un alien en la posici�n actual de la columna y fila
                    if (colmenaDeAliens[fila][columna] != null) {
                        // Guarda la posici�n x del alien
                        posicionAlienQueVaADisparar[0] = this.colmenaDeAliens[fila][columna].getxPos();
                        // Guarda la posici�n y del alien
                        posicionAlienQueVaADisparar[1] = this.colmenaDeAliens[fila][columna].getyPos();
                        break; // Termina la b�squeda en la columna actual
                    }else {
                        posicionAlienQueVaADisparar[0] = -1; // Si no se encuentra un alien v�lido, establece la posici�n x en -1
                    }
                }
            } while (posicionAlienQueVaADisparar[0] == -1); // Repite hasta encontrar un alien v�lido
        }

        return posicionAlienQueVaADisparar; // Devuelve la posici�n del alien encontrado
    }


    private void emitirSonidoAlien() { // Reproduce sonido de alienigena
        int contador = this.contadorDeNumeroDeSonidosAliens % 4;
        if (contador == 0) {
            Audio.playSound("/Sonidos/sonidoAlien1.wav");
        } else if (contador == 1) {
            Audio.playSound("/Sonidos/sonidoAlien2.wav");
        } else if (contador == 2) {
            Audio.playSound("/Sonidos/sonidoAlien3.wav");
        } else {
            Audio.playSound("/Sonidos/sonidoAlien4.wav");
        }
    }

    public int getNumeroTotalAliens() {
        return numeroTotalAliens;
    }

    public int obtenerPosicionAlienigenaMasBajo() {
        // Inicializa la posici�n m�s baja de los alien�genas a 0
        int posicionBaja = 0;
        // Inicializa la posici�n final m�s baja a 0
        int posicionBajaFinal = 0;
        //todo; aqui tambien toca corregir para dar valores mas dinamicos en lugar de estaticos
        // Recorre cada columna de la colmena de alien�genas (del 1 al 9)
        for (int columna = 1; columna < 10; columna++) {
            // Recorre cada fila de abajo hacia arriba (de la fila 4 a la 0)
            for (int fila = 4; fila >= 0; fila--) {
                // Verifica si hay un alien�gena en la posici�n actual
                if (this.colmenaDeAliens[fila][columna] != null) {
                    // Calcula la posici�n m�s baja del alien�gena sumando su posici�n vertical y su altura
                    posicionBaja = this.colmenaDeAliens[fila][columna].getyPos() + this.colmenaDeAliens[fila][columna].getAlto();
                    // Sale del bucle de filas ya que se ha encontrado un alien�gena en esta columna
                    break;
                }
            }
            // Actualiza la posici�n final m�s baja si la posici�n m�s baja encontrada en esta columna es mayor
            if (posicionBaja > posicionBajaFinal) {
                posicionBajaFinal = posicionBaja;
            }
        }
        // Devuelve la posici�n vertical m�s baja de los alien�genas
        return posicionBajaFinal;
    }

    public Alien[][] getColmenaAliens() {
        return colmenaDeAliens;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    @Override
    public void run() {
        while (colmenaViva) {
            proyectilImpactaAlien(nivel.getProyectil());
            desplazarAliens();
            verificarTiempo2();
            obtenerPosicionAlienigenaMasBajo();
        }

    }

    public static void verificarTiempo2() {
        double intervaloDeDibujo = 1000000000 / (Constantes.FPS - 50); //frames por seundo
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
}




