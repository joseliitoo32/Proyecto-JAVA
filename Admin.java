public class Admin extends Usuario {

    public Admin() {
        super();
        setRol("ADMIN");
    }

    public Admin(int id, String nombre, String apellidos, int edad, double altura, String password) {
        super(id, nombre, apellidos, edad, altura, password, "ADMIN");
    }

    public void verUsuarios() {
        System.out.println("El admin " + getNombre() + " está consultando la lista de usuarios.");
    }

    public void añadirUsuarios() {
        System.out.println("El admin está añadiendo un nuevo usuario administrador.");
    }
}