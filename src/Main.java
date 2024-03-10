import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al juego de búsqueda del tesoro.");
        System.out.println("Selecciona el modo de juego:");
        System.out.println("1. Un jugador");
        System.out.println("2. Dos jugadores");
        System.out.print("Introduce tu elección: ");
        int eleccion = scanner.nextInt();
        scanner.nextLine(); // Consume el salto de línea

        switch (eleccion) {
            case 1:
                ModoUnJugador.start();
                break;
            case 2:
                ModoDosJugadores.start();
                break;
            default:
                System.out.println("Opción no válida. Por favor, elige 1 o 2.");
                break;
        }

        scanner.close();
    }
}


