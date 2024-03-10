import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private int posX; // Posición X actual del jugador en el mapa
    private int posY; // Posición Y actual del jugador en el mapa
    private List<Ítem> inventario; // Inventario de ítems recolectados
    private int monedas;

    public Jugador(String nombre, int posX, int posY) {
        this.nombre = nombre;
        this.posX = posX;
        this.posY = posY;
        this.inventario = new ArrayList<>();
        this.monedas=10;
    }

    public boolean mover(char direccion, Mapa mapa) {
        int nuevaPosX = posX;
        int nuevaPosY = posY;

        switch (direccion) {
            case 'N': nuevaPosY--; break;
            case 'S': nuevaPosY++; break;
            case 'E': nuevaPosX++; break;
            case 'O': nuevaPosX--; break;
            default: System.out.println("Dirección no válida."); return false;
        }

        // Comprobación de límites del mapa primero
        if (nuevaPosX < 0 || nuevaPosX >= mapa.getTamaño() || nuevaPosY < 0 || nuevaPosY >= mapa.getTamaño()) {
            System.out.println("No puedes salirte del mapa. Intenta otra dirección.");
            return false;
        }

        // Si está dentro de los límites, verificar si hay un obstáculo
        else if (mapa.casillaTieneObstaculo(nuevaPosX, nuevaPosY)) {
            // Si tiene monedas, puede superar el obstáculo
            if (this.monedas > 0) {
                perderMoneda();
                System.out.println("Has gastado una moneda para superar el obstáculo. Monedas restantes: " + this.monedas);
                posX = nuevaPosX;
                posY = nuevaPosY;
                return true;
            } else {
                System.out.println("Hay un obstáculo en tu camino y no tienes monedas para superarlo.");
                return false;
            }
        }

        // Si no hay obstáculos y está dentro de los límites, el jugador se mueve
        else {
            posX = nuevaPosX;
            posY = nuevaPosY;
            System.out.println("Movimiento realizado a la posición (" + posX + ", " + posY + ")");
            return true;
        }
    }








    // Método para que el jugador pierda una moneda
    public void perderMoneda() {
        if (this.monedas > 0) {
            this.monedas--;
            System.out.println("Has perdido una moneda. Monedas restantes: " + this.monedas);
        } else {
            System.out.println("¡No tienes más monedas!");
        }
    }

    public int getMonedas() {
        return this.monedas;
    }




    // Método para recoger un ítem del mapa y agregarlo al inventario
    public void recogerÍtem(Ítem item) {
        inventario.add(item);
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public List<Ítem> getInventario() {
        return inventario;
    }

    // Método para imprimir la posición actual e inventario del jugador
    public void imprimirEstado() {
        System.out.println(nombre + " está en la posición (" + posX + ", " + posY + ")");
        System.out.println("Inventario:");
        for (Ítem item : inventario) {
            System.out.println("- " + item);
        }
    }

    // Añade aquí otros métodos según necesites, por ejemplo, para resolver acertijos.
}

