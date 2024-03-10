import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mapa {
    private Casilla[][] casillas;
    private int tamaño;
    private List<Acertijo> acertijosDisponibles= new ArrayList<>();

    public Mapa(int tamaño) {
        this.tamaño = tamaño;
        casillas = new Casilla[tamaño][tamaño];
        inicializarMapa();
        inicializarAcertijos();
        colocarTesoro();
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
        int llaveX = random.nextInt(tamaño);
        int llaveY = random.nextInt(tamaño);
        casillas[llaveX][llaveY].setTieneLlave(true);

        // Coloca obstáculos de manera aleatoria, pero deja espacio para al menos un acertijo y un tesoro.
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if (random.nextBoolean()) { // Decidir aleatoriamente si poner un obstáculo
                    casillas[i][j].setEsObstaculo(true);
                }
            }
        }

        // Asegurar al menos un acertijo en una casilla que no es un obstáculo ni el tesoro.
        colocarAcertijos();
    }

    private void inicializarAcertijos() {
        // Aquí puedes añadir todos los acertijos que quieras
        acertijosDisponibles.add(new Acertijo("¿Cuánto es 2 + 2?", "4"));
        acertijosDisponibles.add(new Acertijo("¿Cuál es el color del caballo blanco de Santiago?", "blanco"));
        acertijosDisponibles.add(new Acertijo("¿Cuál es la única planta que no da ni flor, ni fruta, ni hojas?", "La planta del pié"));

    }

    public void colocarAcertijos() {
        Random random = new Random();
        int acertijosAColocar = 3; // Deseamos colocar 3 acertijos
        List<Acertijo> acertijosSeleccionados = new ArrayList<>();

        // Seleccionar aleatoriamente 3 acertijos diferentes
        while (acertijosSeleccionados.size() < acertijosAColocar && acertijosDisponibles.size() >= acertijosAColocar) {
            Acertijo acertijoAleatorio = acertijosDisponibles.get(random.nextInt(acertijosDisponibles.size()));
            if (!acertijosSeleccionados.contains(acertijoAleatorio)) {
                acertijosSeleccionados.add(acertijoAleatorio);
            }
        }

        // Colocar los 3 acertijos seleccionados en el mapa
        for (Acertijo acertijo : acertijosSeleccionados) {
            int x, y;
            do {
                x = random.nextInt(tamaño);
                y = random.nextInt(tamaño);
            } while (casillas[x][y].esObstaculo() || casillas[x][y].getAcertijo() != null); // Asegura no sobrescribir obstáculos o acertijos existentes

            casillas[x][y].setAcertijo(acertijo);
        }
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
        boolean tesoroColocado = false;

        while (!tesoroColocado) {
            int x = random.nextInt(tamaño);
            int y = random.nextInt(tamaño);

            // Verifica que la casilla seleccionada no sea un obstáculo y no tenga un acertijo
            if (!casillas[x][y].esObstaculo() && casillas[x][y].getAcertijo() == null) {
                casillas[x][y].setEsTesoro(true);
                tesoroColocado = true;
            }
        }
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

    public void eliminarObstaculos(int cantidad) {
        Random random = new Random();
        int eliminados = 0;

        while (eliminados < cantidad) {
            int x = random.nextInt(tamaño);
            int y = random.nextInt(tamaño);

            // Verifica si la casilla seleccionada aleatoriamente es un obstáculo y no contiene un tesoro ni un acertijo
            if (casillas[x][y].esObstaculo() && !casillas[x][y].esTesoro() && casillas[x][y].getAcertijo() == null) {
                casillas[x][y].setEsObstaculo(false); // Elimina el obstáculo
                eliminados++;
            }
        }
    }

    public int getTamaño() {
        return tamaño;
    }

    public void colocarLlave() {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(tamaño);
            y = random.nextInt(tamaño);
        } while (casillas[x][y].esObstaculo() || casillas[x][y].esTesoro() || casillas[x][y].tieneLlave());
        casillas[x][y].setTieneLlave(true);
    }






}
