import java.util.Scanner;

public class ModoUnJugador {
    public static void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce tu nombre:");
        String nombreJugador = scanner.nextLine(); // El jugador introduce su nombre

        Mapa mapa = new Mapa(5); // Ajusta el tamaño del mapa según sea necesario.
        mapa.colocarTesoro();
        mapa.colocarAcertijos(); // Asegura que se implementen los acertijos en la clase Mapa.
        mapa.colocarLlave(); // Asegura que se coloque una llave en el mapa.

        Jugador jugador = new Jugador(nombreJugador, 0, 0); // Usa directamente el nombre proporcionado.

        System.out.println("Mapa Inicial:");
        System.out.println("Bienvenido al juego, " + nombreJugador);
        mapa.imprimirMapa(jugador);

        boolean juegoTerminado = false;
        while (!juegoTerminado) {
            System.out.println("¿En qué dirección te gustaría mover? (N/S/E/O)");
            String direccion = scanner.nextLine().toUpperCase();

            if (!jugador.mover(direccion.charAt(0), mapa)) {
                System.out.println("Movimiento no válido. Intenta otra dirección.");
                continue;
            }

            Casilla casillaActual = mapa.obtenerCasilla(jugador.getPosX(), jugador.getPosY());
            if (casillaActual != null) {
                if (casillaActual.tieneLlave()) {
                    System.out.println("¡Has encontrado una llave mágica! Se eliminarán dos obstáculos del mapa.");
                    mapa.eliminarObstaculos(2); // Asume que este método elimina aleatoriamente dos obstáculos.
                    casillaActual.setTieneLlave(false); // Consume la llave al ser encontrada.
                } else if (casillaActual.esObstaculo()) {
                    System.out.println(nombreJugador + ", te has encontrado con un obstáculo.");
                } else if (casillaActual.getAcertijo() != null) {
                    System.out.println("Has encontrado un acertijo: " + casillaActual.getAcertijo().getPregunta());
                    System.out.println("Ingresa tu respuesta:");
                    String respuesta = scanner.nextLine();
                    if (!casillaActual.getAcertijo().resolver(respuesta)) {
                        System.out.println("Respuesta incorrecta. No puedes avanzar hasta resolver el acertijo.");
                        continue;
                    }
                } else if (casillaActual.esTesoro()) {
                    System.out.println("¡Has encontrado el tesoro, " + nombreJugador + "! ¡Felicidades!");
                    juegoTerminado = true;
                }
            } else {
                System.out.println("Algo salió mal. Casilla actual no encontrada.");
            }

            System.out.println("\nEstado actual del mapa:");
            mapa.imprimirMapa(jugador);
            System.out.println("Tu posición actual es: (" + jugador.getPosX() + ", " + jugador.getPosY() + ")");
        }

        scanner.close();
    }
}
