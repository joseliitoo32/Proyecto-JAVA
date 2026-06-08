public class Musculo {

    // ======================
    // ATRIBUTOS
    // ======================
    private int id;          // Identificador del músculo
    private String nombre;   // Nombre del músculo (pecho, espalda, etc.)

    // Constructor vacío
    public Musculo() {
    }

    // Constructor con parámetros
    public Musculo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}