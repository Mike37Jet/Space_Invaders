package logica;

import Recursos.Constantes;

import java.io.Serializable;

public class Nivel implements Serializable {
    private ColmenaDeAliens colmenaDeAliens;
    private NaveEspacial naveEspacial;
    private NaveNodriza naveNodriza;
    private Proyectil proyectil;
    private ProyectilAlien proyectilAlien;
    private Alien alien;
    private Escudo[] arregloEscudos = new Escudo[4];
    private transient Thread hiloLogicoNaveEspacial, hiloLogicoColmenaAlien, hiloLogicoProyectil, hiloLogicoEscudo, hiloLogicoNaveNodriza, hiloLogicoProyectilAlien;

    public Nivel() {
        colmenaDeAliens = new ColmenaDeAliens();
        for (int columna = 0; columna < 4; columna++) {
            this.arregloEscudos[columna] = new Escudo(Constantes.margenVentana + Constantes.posicionXPrimerEscudo + columna * (Constantes.anchoEscudo + Constantes.espacioDeSeparacionEntreEscudos));
        }
        naveEspacial = new NaveEspacial();
        naveNodriza = new NaveNodriza();
        proyectil = new Proyectil();
        proyectilAlien = new ProyectilAlien(this.colmenaDeAliens, arregloEscudos, naveEspacial);
    }

    public void iniciarNivel() {

        naveEspacial.hilo = true;
        naveNodriza.hilo = true;
        proyectil.hilo = true;
        proyectilAlien.hilo = true;


        hiloLogicoNaveEspacial = new Thread(naveEspacial);
        hiloLogicoColmenaAlien = new Thread(colmenaDeAliens);
        hiloLogicoProyectil = new Thread( proyectil);
        hiloLogicoEscudo = new Thread(arregloEscudos[0]);
        hiloLogicoNaveNodriza = new Thread(naveNodriza);
        hiloLogicoProyectilAlien = new Thread(proyectilAlien);

        naveEspacial.setNivel(this);
        naveNodriza.setNivel(this);
        proyectil.setNivel(this);
        proyectilAlien.setNivel(this);
        for (int columna = 0; columna < 4; columna++) {
            arregloEscudos[columna].setNivel(this);
        }
        colmenaDeAliens.setNivel(this);

        hiloLogicoNaveEspacial.start();
        hiloLogicoColmenaAlien.start();
        hiloLogicoProyectil.start();
        hiloLogicoEscudo.start();
        hiloLogicoNaveNodriza.start();
        hiloLogicoProyectilAlien.start();


    }

    public void detener() {
        naveEspacial.detenerSonido();
        //naveEspacial.pausado = true;
    }

    public void terminar() {
        naveEspacial.hilo = false;
        alien.hilo = false;
        naveNodriza.hilo = false;
        proyectil.hilo = false;
        proyectilAlien.hilo = false;

    }

    public Escudo[] getArregloEscudos() {
        return this.arregloEscudos;
    }

    public Alien[][] getColmena() {
        return colmenaDeAliens.getColmenaAliens();
    }

    public ColmenaDeAliens getColmenaDeAliens() {
        return colmenaDeAliens;
    }

    public NaveEspacial getNaveEspacial() {
        return naveEspacial;
    }

    public NaveNodriza getNaveNodriza() {
        return naveNodriza;
    }

    public Proyectil getProyectil() {
        return proyectil;
    }

    public ProyectilAlien getProyectilAlien() {
        return proyectilAlien;
    }

    public Alien getAlien() {
        return alien;
    }

    public Entidad getEscudo(int columna) {
        return arregloEscudos[columna];
    }
}
