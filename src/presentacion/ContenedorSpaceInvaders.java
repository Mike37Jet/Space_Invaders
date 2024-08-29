package presentacion;

import Recursos.Constantes;
import logica.Nivel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContenedorSpaceInvaders extends JPanel {
    private final Thread hiloGrafico;
    private InterfazGraficaDeNaveEspacial interfazGraficaNaveEspacial;
    private Timer timer;
    private InterfazGraficaDeColmena interfazGraficaColmena;
    private InterfazGraficaDeNaveNodriza interfazGraficaNaveNodriza;
    private InterfazGraficaDeProyectil interfazGraficaProyectil;
    private InterfazGraficaDeProyectilAlien interfazGraficaProyectilAlien;
    private InterfazGraficaDeAlien interfazGraficaAlienBuho, interfazGraficaAlienBuldog, interfazGraficaAlienChita, interfazGraficaAlienLobo;
    private InterfazGraficaDeEscudo[] interfazGraficaEscudos = new InterfazGraficaDeEscudo[4];
    private Nivel nivel;
    public int score = 0;
    private DetectorTeclas controles;
    private Font fuentePuntuacion = new Font("Arial", Font.PLAIN, 20);
    private Font fuenteTexto = new Font("Arial", Font.PLAIN, 40);

    public ContenedorSpaceInvaders() {
        setFocusable(true);
        requestFocusInWindow();
        controles = new DetectorTeclas();
        addKeyListener(controles);
        interfazGraficaNaveEspacial = new InterfazGraficaDeNaveEspacial(this);
        interfazGraficaNaveNodriza = new InterfazGraficaDeNaveNodriza();

        this.hiloGrafico = new Thread(interfazGraficaNaveEspacial);
        interfazGraficaAlienBuho = new InterfazGraficaDeAlien("alienBuho");
        interfazGraficaAlienBuldog = new InterfazGraficaDeAlien("alienBuldog");
        interfazGraficaAlienChita = new InterfazGraficaDeAlien("alienChita");
        interfazGraficaAlienLobo = new InterfazGraficaDeAlien("alienLobo");

        interfazGraficaColmena = new InterfazGraficaDeColmena(interfazGraficaAlienBuho, interfazGraficaAlienBuldog, interfazGraficaAlienChita, interfazGraficaAlienLobo);
        interfazGraficaNaveNodriza = new InterfazGraficaDeNaveNodriza();
        interfazGraficaProyectil = new InterfazGraficaDeProyectil();
        interfazGraficaProyectilAlien = new InterfazGraficaDeProyectilAlien();

        for (int columna = 0; columna < 4; columna++) {
            interfazGraficaEscudos[columna] = new InterfazGraficaDeEscudo();

        }
        hiloGrafico.start();

    }

    private void actualizarJuego() {
        //interfazGraficaAlien.actualizar();
        interfazGraficaNaveEspacial.actualizar();
    }

    public void iniciar(Nivel nivel) {
        this.nivel = nivel;

        interfazGraficaColmena.setAliens(nivel.getColmena());
        interfazGraficaNaveEspacial.setEntidad(nivel.getNaveEspacial());
        interfazGraficaNaveNodriza.setEntidad(nivel.getNaveNodriza());
        interfazGraficaProyectil.setEntidad(nivel.getProyectil());
        interfazGraficaProyectilAlien.setEntidad(nivel.getProyectilAlien());

        for (int columna = 0; columna < 4; columna++) {
            interfazGraficaEscudos[columna].setEntidad(nivel.getEscudo(columna));
            interfazGraficaEscudos[columna].setEscudos(nivel.getArregloEscudos());
        }
        nivel.iniciarNivel(controles);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK); // Establece el color de dibujo a negro
        g2.fillRect(0, 0, Constantes.anchoVentana, Constantes.alturaVentana);

        g2.setColor(Color.CYAN); // Establece el color de dibujo a verde
        g2.fillRect(30, 530, 535, 5); // Dibuja un rectángulo verde (una línea) en la parte inferior de la ventana

        // Dibujar la puntuacion
        g.setFont(fuentePuntuacion);
        g.drawString("SCORE : " + score, 400, 25);

        interfazGraficaNaveEspacial.dibujar(g2);
        interfazGraficaColmena.dibujar(g2);
        interfazGraficaNaveNodriza.dibujar(g2);
        interfazGraficaProyectil.dibujar(g2);
        interfazGraficaProyectilAlien.dibujar(g2);
        for (int columna = 0; columna < 4; columna++) {
            interfazGraficaEscudos[columna].dibujar(g2);
        }
    }

    public Nivel getNivel() {
        return nivel;
    }
}
