public class Ítem {
    private String nombre;
    private String descripción;
    // Puedes añadir más propiedades dependiendo de la complejidad de tu juego,
    // como efectos al usar el ítem, cantidad, etc.

    public Ítem(String nombre, String descripción) {
        this.nombre = nombre;
        this.descripción = descripción;
    }

    // Métodos getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    // Método toString para imprimir detalles del ítem
    @Override
    public String toString() {
        return nombre + ": " + descripción;
    }

    // Aquí puedes añadir más métodos específicos, como "usar" para aplicar el efecto del ítem.
}
