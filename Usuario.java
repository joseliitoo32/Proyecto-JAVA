public class Usuario {

    // ======================
    // ATRIBUTOS DEL USUARIO
    // ======================
    private int id;                 // Identificador único del usuario
    private String nombre;          // Nombre del usuario
    private String apellidos;       // Apellidos del usuario
    private int edad;               // Edad del usuario
    private double altura;          // Altura del usuario en cm o metros
    private String password;        // Contraseña del usuario
    private String rol;             // Rol: USER o ADMIN

    // ======================
    // CONSTRUCTOR VACÍO
    // ======================
    public Usuario() {
        // Se usa cuando no se quieren dar datos al crear el objeto
    }

    // ======================
    // CONSTRUCTOR COMPLETO
    // ======================
    public Usuario(int id, String nombre, String apellidos, int edad, double altura, String password, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.altura = altura;
        this.password = password;
        this.rol = rol;
    }

    // ======================
    // GETTERS Y SETTERS
    // ======================
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // ======================
    // MÉTODOS PROPIOS
    // ======================

    // Comprueba si la contraseña es correcta
    public boolean validarPassword(String pass) {
        return this.password.equals(pass);
    }

    // Devuelve nombre completo del usuario
    public String getNombreCompleto() {
        return nombre + " " + apellidos;
    }
}