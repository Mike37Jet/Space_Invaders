package logica;

import Recursos.Constantes;

public class Alien extends Entidad {

    /**** Constructor ****/

    public Alien(int xPos, int yPos) {
        super(Constantes.anchoAlien, Constantes.alturaAlien, xPos,yPos,0,0);
    }


    /**** Metodos ****/


    @Override
    public void actualizar() {
        // TODO Auto-generated method stub
    }

    public boolean getHilo() {
        return this.hilo;
    }
}
