public class Acertijo {
    private String pregunta;
    private String respuesta;

    public Acertijo(String pregunta, String respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    // Métodos para interactuar con el acertijo
    public boolean resolver(String respuestaUsuario) {
        return respuestaUsuario.equalsIgnoreCase(respuesta);
    }

    public String getPregunta() {
        return pregunta;
    }
}
