public class Ejercicio {
    private int id;
    private String nombre;
    private String descripcion;
    private String dificultad;
    private Musculo musculo;

    public Ejercicio() {}

    public Ejercicio(int id, String nombre, String descripcion, String dificultad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
    }

    public Ejercicio(int id, String nombre, String descripcion, String dificultad, Musculo musculo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.musculo = musculo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getDificultad() { return dificultad; }
    public void setDificultad(String dificultad) { this.dificultad = dificultad; }

    public Musculo getMusculo() { return musculo; }
    public void setMusculo(Musculo musculo) { this.musculo = musculo; }

    @Override
    public String toString() {
        return this.nombre + " [" + this.dificultad + "]";
    }
}