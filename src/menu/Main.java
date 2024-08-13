package menu;

import javax.swing.*;

import logica.Nivel;
import Recursos.Constantes;
import logica.SpaceInvaders;
import presentacion.ContenedorSpaceInvaders;

import java.awt.*;

public class Main {



    /**** Metodos ****/
    public static void main(String[] args) {
        SpaceInvaders spaceInvaders = new SpaceInvaders(new Nivel());

        Menu menu = new Menu(spaceInvaders);

    }

}
