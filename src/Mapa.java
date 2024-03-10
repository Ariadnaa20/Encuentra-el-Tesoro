import java.util.Random;

public class Mapa {
    private Casilla[][] casillas;
    private int tamaño;

    public Mapa(int tamaño) {
        this.tamaño = tamaño;
        casillas = new Casilla[tamaño][tamaño];
        inicializarMapa();
        colocarAcertijo(); //per a que hi hagi sempre un acertijo al mapa
    }


    public void imprimirMapa(Jugador jugador) {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if (i == jugador.getPosX() && j == jugador.getPosY()) {
                    System.out.print("X "); // Marca la posición del jugador con una X
                } else {
                    System.out.print(casillas[i][j] + " "); // Usa el método toString() de Casilla
                }
            }
            System.out.println(); // Nueva línea al final de cada fila del mapa
        }
    }

    void inicializarMapa() {
        Random random = new Random();
        // Inicializa todas las casillas como no obstáculos y sin acertijos para empezar.
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                casillas[i][j] = new Casilla(false, null);
            }
        }

        // Coloca obstáculos de manera aleatoria, pero deja espacio para al menos un acertijo y un tesoro.
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if (random.nextBoolean()) { // Decidir aleatoriamente si poner un obstáculo
                    casillas[i][j].setEsObstaculo(true);
                }
            }
        }

        // Asegurar al menos un acertijo en una casilla que no es un obstáculo ni el tesoro.
        colocarAcertijo(); //un acertijo
    }

    void colocarAcertijo() { //2ndo acertijo
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(tamaño);
            y = random.nextInt(tamaño);
        } while (casillas[x][y].esObstaculo() || casillas[x][y].esTesoro()); // Evita colocar un acertijo en un obstáculo o en el tesoro.

        casillas[x][y].setAcertijo(new Acertijo("¿Cuál es el resultado de 2+2?", "4")); // Asigna el acertijo.
    }

    public boolean esPosicionValida(int x, int y) {
        // Verifica si las coordenadas están dentro de los límites del mapa
        if (x < 0 || x >= tamaño || y < 0 || y >= tamaño) {
            // Está fuera de los límites del mapa
            return false;
        } else {
            // Está dentro de los límites, ahora verifica si hay un obstáculo
            return !casillas[x][y].esObstaculo();
        }
    }


    void colocarTesoro() {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(tamaño);
            y = random.nextInt(tamaño);
        } while (casillas[x][y].esObstaculo()); // Repite hasta que encuentre una casilla que no es un obstáculo.

        casillas[x][y].setEsTesoro(true); // Esta casilla es ahora el tesoro.
    }

    public Casilla obtenerCasilla(int x, int y) {
        if (x >= 0 && y >= 0 && x < tamaño && y < tamaño) {
            return casillas[x][y];
        } else {
            System.out.println("Intento de acceso fuera de los límites del mapa.");
            return null;
        }
    }


    public boolean casillaTieneObstaculo(int x, int y) {
        return casillas[x][y].esObstaculo();
    }



}
