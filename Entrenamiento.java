import java.util.ArrayList;
import java.util.List;

public class Entrenamiento {
    private int id;
    private List<Ejercicio> ejercicios;

    public Entrenamiento() {
        this.ejercicios = new ArrayList<>();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public List<Ejercicio> getEjercicios() { return ejercicios; }

    public void añadirEjercicio(Ejercicio e) {
        if (e != null) {
            this.ejercicios.add(e);
        }
    }

    public void eliminarEjercicio(Ejercicio e) {
        this.ejercicios.remove(e);
    }
}