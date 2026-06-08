import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("No se pudo cargar el diseño visual del sistema.");
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Pantalla_Inicio login = new Pantalla_Inicio();
                login.setVisible(true);
            }
        });
    }
}