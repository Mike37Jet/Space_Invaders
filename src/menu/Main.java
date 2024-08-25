package menu;

import logica.Nivel;
import logica.SpaceInvaders;

public class Main {
    /**** Metodos ****/
    public static void main(String[] args) {
        SpaceInvaders spaceInvaders = new SpaceInvaders(new Nivel());

        Menu menu = new Menu(spaceInvaders); 

    }
}
