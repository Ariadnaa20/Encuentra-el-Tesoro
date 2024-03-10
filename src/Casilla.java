public class Casilla {
    private boolean esObstaculo;
    private Acertijo acertijo;
    private boolean esTesoro;
    private boolean tieneLlave;

    public Casilla(boolean esObstaculo, Acertijo acertijo) {
        this.esObstaculo = esObstaculo;
        this.acertijo = acertijo;
        this.esTesoro = false;
        this.tieneLlave = false;

    }

    public boolean tieneLlave() {
        return tieneLlave;
    }

    public void setTieneLlave(boolean tieneLlave) {
        this.tieneLlave = tieneLlave;
    }

    // Método getter para esObstaculo
    public boolean esObstaculo() {
        return esObstaculo;
    }

    // Método setter para esObstaculo
    public void setEsObstaculo(boolean esObstaculo) {
        this.esObstaculo = esObstaculo;
    }

    // Métodos getters y setters para acertijo y esTesoro
    public Acertijo getAcertijo() {
        return acertijo;
    }

    public void setAcertijo(Acertijo acertijo) {
        this.acertijo = acertijo;
    }

    public boolean esTesoro() {
        return esTesoro;
    }

    public void setEsTesoro(boolean esTesoro) {
        this.esTesoro = esTesoro;
    }

    @Override
    public String toString() {
        if (tieneLlave) {
            return "K"; // Símbolo para la llave
        } else if (esObstaculo) {
            return "O";
        } else if (acertijo != null) {
            return "?";
        } else if (esTesoro) {
            return "T";
        } else {
            return ".";
        }
    }
}

