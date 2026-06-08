public class Ejercicio {

    // ======================
    // ATRIBUTOS
    // ======================
    private int id;              // Identificador del ejercicio
    private String nombre;       // Nombre del ejercicio
    private String descripcion;  // Explicación del ejercicio
    private String dificultad;   // Nivel (fácil, medio, difícil)

    // Constructor vacío
    public Ejercicio() {
    }

    // Constructor completo
    public Ejercicio(int id, String nombre, String descripcion, String dificultad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }
}