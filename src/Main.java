import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creación del mapa y asegurando que siempre haya al menos un acertijo.
        Mapa mapa = new Mapa(5); // Ajusta el tamaño del mapa según sea necesario.
        mapa.colocarTesoro();
        mapa.colocarAcertijos(); // Asegúrate de implementar este método en la clase Mapa.

        // Inicialización del jugador en la posición inicial.
        Jugador jugador = new Jugador("Explorador", 0, 0);

        // Imprime el estado inicial del mapa.
        System.out.println("Mapa Inicial:");
        mapa.imprimirMapa(jugador); // Asegúrate de que el método imprimirMapa() de Mapa no requiera un argumento jugador.

        boolean juegoTerminado = false;
        while (!juegoTerminado) {
            System.out.println("¿En qué dirección te gustaría mover? (N/S/E/O)");
            String direccion = scanner.nextLine().toUpperCase();

            // Mover al jugador según la dirección ingresada.
            if (!jugador.mover(direccion.charAt(0), mapa)) {
                System.out.println("Movimiento no válido. Intenta otra dirección.");
                continue;
            }

            Casilla casillaActual = mapa.obtenerCasilla(jugador.getPosX(), jugador.getPosY());
            if (casillaActual != null) {
                if (casillaActual.esObstaculo()) {
                    System.out.println("Te has encontrado con un obstáculo.");
                } else if (casillaActual.getAcertijo() != null) {
                    System.out.println("Has encontrado un acertijo: " + casillaActual.getAcertijo().getPregunta());
                    System.out.println("Ingresa tu respuesta:");
                    String respuesta = scanner.nextLine();
                    if (!casillaActual.getAcertijo().resolver(respuesta)) {
                        System.out.println("Respuesta incorrecta. No puedes avanzar hasta resolver el acertijo.");
                        continue; // Obliga al jugador a resolver correctamente el acertijo para avanzar.
                    }
                } else if (casillaActual.esTesoro()) {
                    System.out.println("¡Has encontrado el tesoro! ¡Felicidades!");
                    juegoTerminado = true;
                }
            } else {
                System.out.println("Algo salió mal. Casilla actual no encontrada.");
            }

            // Imprime el estado actual del mapa y la posición del jugador después de cada acción.
            System.out.println("\nEstado actual del mapa:");
            mapa.imprimirMapa(jugador); // Asegúrate de que este método refleje la posición actual del jugador si es necesario.
            System.out.println("Tu posición actual es: (" + jugador.getPosX() + ", " + jugador.getPosY() + ")");
        }

        scanner.close();
    }
}


