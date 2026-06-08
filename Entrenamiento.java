import java.util.ArrayList;
import java.util.List;

public class Entrenamiento {

    // ======================
    // ATRIBUTOS
    // ======================
    private int id;  // Identificador del entrenamiento

    // Lista de ejercicios del entrenamiento
    private List<Ejercicio> ejercicios;

    // Constructor vacío
    public Entrenamiento() {
        // Se inicializa la lista para evitar errores
        this.ejercicios = new ArrayList<>();
    }

    // Getter y setter del id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Devuelve la lista de ejercicios
    public List<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    // Añadir ejercicio al entrenamiento
    public void añadirEjercicio(Ejercicio e) {
        ejercicios.add(e);
    }

    // Eliminar ejercicio del entrenamiento
    public void eliminarEjercicio(Ejercicio e) {
        ejercicios.remove(e);
    }
}