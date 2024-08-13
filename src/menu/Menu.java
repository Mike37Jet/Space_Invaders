package menu;

import Recursos.Constantes;
import logica.Nivel;
import logica.SpaceInvaders;
import presentacion.ContenedorSpaceInvaders;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {
    private SpaceInvaders spaceInvaders;
    public  boolean juego = true;
    public  ContenedorSpaceInvaders contenedorSpaceInvaders;

    public Menu(SpaceInvaders spaceInvaders) {


        this.spaceInvaders = spaceInvaders;
        JFrame frame = new JFrame("Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Cargar la imagen de fondo desde el paquete 'Imagenes'
        ImageIcon backgroundImageIcon = new ImageIcon(Main.class.getResource("/imagenes/menu/polinvaders-fondo4.png"));

        // Verificar si la imagen de fondo se carga correctamente
        if (backgroundImageIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("La imagen de fondo no se pudo cargar");
            return;
        }

        // Crear un JLayeredPane para manejar capas
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new java.awt.Dimension(backgroundImageIcon.getIconWidth(), backgroundImageIcon.getIconHeight()));

        // Crear un JLabel para la imagen de fondo
        JLabel imageLabel = new JLabel(backgroundImageIcon);
        imageLabel.setBounds(0, 0, backgroundImageIcon.getIconWidth(), backgroundImageIcon.getIconHeight());
        layeredPane.add(imageLabel, JLayeredPane.DEFAULT_LAYER);

        // Cargar la imagen del título
        ImageIcon titleImageIcon = new ImageIcon(Main.class.getResource("/imagenes/menu/logo-polinvaders.png")); // Cambia esto a la ruta de tu imagen de título

        // Crear un JLabel para la imagen del título
        JLabel titleLabel = new JLabel(titleImageIcon);

        // Ajustar tamaño y posición del título
        int titleWidth = titleImageIcon.getIconWidth();
        int titleHeight = titleImageIcon.getIconHeight();
        int titleX = (backgroundImageIcon.getIconWidth() - titleWidth) / 2; // Centrar horizontalmente
        int titleY = 50; // Posicionar más arriba del centro (ajusta este valor según necesites)

        titleLabel.setBounds(titleX, titleY, titleWidth, titleHeight); // Ajusta el tamaño y la posición
        layeredPane.add(titleLabel, JLayeredPane.PALETTE_LAYER);

        // Cargar la imagen para el botón "Play"
        ImageIcon playButtonIcon = new ImageIcon(Main.class.getResource("/imagenes/menu/Boton_Play.png")); // Cambia esto a la ruta de tu imagen de botón

        // Crear el botón "Play" con la imagen
        JButton playButton = new JButton(playButtonIcon);

        // Ajustar tamaño y posición del botón
        int buttonWidth = 300;  // Ancho del botón
        int buttonHeight = 100;  // Alto del botón
        int buttonX = (backgroundImageIcon.getIconWidth() - buttonWidth) / 2; // Centrar horizontalmente
        int buttonY = backgroundImageIcon.getIconHeight() - buttonHeight - 80; // Más abajo del centro

        playButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight); // Ajusta el tamaño y la posición
        layeredPane.add(playButton, JLayeredPane.PALETTE_LAYER);

        // Añadir un listener para el botón "Play"
        playButton.addActionListener(e -> {
            // Ejecutar el código del programa
            ejecutarPrograma();
            // Cerrar la ventana del menú
            frame.dispose();
        });

        // Añadir el JLayeredPane al JFrame
        frame.add(layeredPane);
        frame.pack(); // Ajustar la ventana al tamaño de sus componentes
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        frame.setVisible(true);

    }

    private void ejecutarPrograma() {
        // Código de tu programa aqui
        JFrame ventana = new JFrame("Juego Space Invaders"); // Crea una instancia de JFrame con el t?tulo "Juego Space Invaders"
        ventana.setSize(Constantes.anchoVentana, Constantes.alturaVentana); // Establece el tama?o de la ventana utilizando valores definidos en la clase Constantes para el ancho y la altura
        ventana.setResizable(false); // Hace que la ventana no sea redimensionable por el usuario
        ventana.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define que la operaci?n por defecto al cerrar la ventana es terminar el codigo
        ventana.setAlwaysOnTop(true); // Hace que la ventana siempre se muestre por encima de otras ventanas
        this.contenedorSpaceInvaders = new ContenedorSpaceInvaders();

        contenedorSpaceInvaders.iniciar(spaceInvaders.getNivel());

        ventana.setContentPane(contenedorSpaceInvaders); // Establece el panel scene como el contenido principal de la ventana
        ventana.setVisible(true); // Hace visible la ventana


    }

}
