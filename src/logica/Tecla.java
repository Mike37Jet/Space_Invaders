package logica;

public enum Tecla {
    DERECHA(100),
    IZQUIERDA(97),
    DISPARAR(32),
    PAUSAR(112);

    private final int codigo;

    Tecla(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
}
