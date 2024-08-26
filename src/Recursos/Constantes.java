package Recursos;

public abstract class Constantes {
	
	/************************************* Ventana *************************************/
	// Dimensiones de la ventana
	public static final int anchoVentana = 600;
	public static final int alturaVentana = 600;
	public static final int margenVentana = 50;
	
	/************************************* NaveEspacial *************************************/
	// Dimensiones de la nave
	public static final int anchoNave = 32;
	public static final int altoNave = 24;
	
	// Posicion Inicial de la Nave
	public final static int xPosicionInicialNave = (anchoVentana - anchoNave)/ 2;
	public final static int yPosicionInicialNave = 490;
	
	// Numero de bloques por desplazamiento
	public final static int espacioDesplazamientoXNaveEspacial = 2;
	
	// Limite de desplazamiento
	public final static int limiteIzquierdoNave = 60;
	public final static int limiteDerechoNave = 500;
	
	/************************************* Alien ***************************************/
	// Dimensiones de los aliens
	public static final int anchoAlien = 30;  // Ancho de cada alien en píxeles
	public static final int alturaAlien = 30;  // Altura de cada alien en píxeles

	// Posición Inicial Alienígenas
	public final static int alturaInicialAlienigena = 120;  // Altura inicial de los alienígenas desde la parte superior de la pantalla
	public final static int posicionInicialEnXAlienigenas = 29 + margenVentana;  // Posición X inicial de los alienígenas sumando un margen
	public final static int espacioVerticalEntreFilasAlienigenas = 40;  // Espacio vertical entre filas de alienígenas
	public final static int espacioHorizontalEntreColumnasAlienigenas = 10;  // Espacio horizontal entre columnas de alienígenas

	// Unidad de desplazamiento del alien
	public final static int cambioEnXDxAlien = 1;  // Desplazamiento horizontal de los alienígenas en píxeles
	public final static int cambioEnYDyAlien = 10;  // Desplazamiento vertical de los alienígenas en píxeles
	public final static int velocidadAlienigenas = 2;  // Velocidad de los alienígenas

	// Numero total de aliens
	public final static int numeroTotalAliens = 50; //Numero de aliens
	//todo: revisar como modificar el numero de aliens que se generan de manera automatica
	
	/************************************ Proyectil **********************************/	
	// Dimensiones del Proyectil
	public static final int anchoProyectil = 3;
	public static final int altoProyectil = 13;
	
	// Unidades de desplazamiento del proyectil
	public final static int unidadesDeDesplazamientos = 4;
	
	/************************************* Escudos *************************************/
	// Dimensiones de los bloques que conponen al escudo
	public static final int dimensionesDeBloqueDeEscudo = 2;
	
	// Dimension total de los escudos
	public static final int anchoEscudo = 72;
	public static final int altoEscudo = 54;

	public final static int posicionYEscudo = 400;
	public final static int posicionXPrimerEscudo = 39;
	public final static int espacioDeSeparacionEntreEscudos = 42;
	
	/************************************ Disparo Alienigena ************************************/

	public static final int anchoDisparoAlien = 5;
	public static final int altoDisparoAlien = 15;
	
	public final static int espacioDeDesplazamientoEnYDisparoAlien = 3;

	/************************************* NaveNodriza *************************************/

	public static final int anchoNaveNodriza = 42;
	public static final int altoNaveNodriza = 22;

	public final static int posXInicialNaveNodriza = anchoVentana;
	public final static int posYNaveNodriza = 50;

	public final static int espacioDeDesplazamientoEnYNaveNodriza = 6;
	
	/************************************* Puntos *************************************/
	// Puntajes por la destruccion de los aliens y la nave Nodriza
	public static final int puntajeAlien3 = 50;
	public static final int puntajeAlien2 = 40;
	public static final int puntajeAlienChita = 20;
	public static final int puntajeNaveNodriza = 100;

	public static final int FPS = 60;
}


