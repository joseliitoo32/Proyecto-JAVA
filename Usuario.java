public class Usuario {
    private int id;
    private String nombre;
    private String apellidos;
    private int edad;
    private double altura;
    private String password;
    private String rol; // "USER" o "ADMIN"

    public Usuario() {}

    public Usuario(int id, String nombre, String apellidos, int edad, double altura, String password, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.altura = altura;
        this.password = password;
        this.rol = rol;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public boolean validarPassword(String pass) {
        return this.password != null && this.password.equals(pass);
    }

    @Override
    public String toString() {
        return this.nombre + " " + this.apellidos + " (" + this.rol + ")";
    }
}