public class Admin extends Usuario {

    // Constructor vacío
    public Admin() {
        super();              // Llama al constructor de Usuario
        setRol("ADMIN");      // Define el rol como administrador
    }

    // Constructor completo
    public Admin(int id, String nombre, String apellidos, int edad, double altura, String password) {
        super(id, nombre, apellidos, edad, altura, password, "ADMIN");
    }

    // Método específico del administrador
    public void verUsuarios() {
        // En una versión real aquí se mostrarían los usuarios de la base de datos
        System.out.println("El admin puede ver la lista de usuarios.");
    }
    public void añadirUsuarios() {
        // En una versión real aquí se mostrarían los usuarios de la base de datos
        System.out.println("El admin puede añadir usuarios.");
    }
}