package app.paneles;

import app.personaje.Tiempo;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Pausa extends Pane {


    /**
     * Constructor del panel de pausa. Muestra el título y dos botones: continuar y salir.
     * @param stage La ventana principal del juego (no se usa directamente aquí, pero podría usarse para más funcionalidades).
     */
    public Pausa(Stage stage) {
        // Establece el tamaño del panel
        setPrefSize(1200, 800);

        // Fondo semitransparente para indicar que el juego está pausado
        setStyle("-fx-background-color: rgba(0,0,0,0.05);");

        // Título de la pantalla de pausa
        Label titulo = new Label("PAUSA");
        titulo.setStyle("-fx-font-size: 48px; -fx-text-fill: #e04040;");
        titulo.setLayoutX(500);
        titulo.setLayoutY(150);

        // Botón para continuar el juego
        Boton continuar = new Boton("Continuar", 450, 250, () -> {
            Escenas.getEscena().setPanelPri(); // Vuelve al panel principal del juego
        });

        // Botón para salir del juego
        Boton salir = new Boton("Salir", 450, 350, () -> {
            Escenas.getStage().close(); // Cierra completamente el juego
            // Alternativa: volver al menú principal si lo tuvieras implementado
            // Escenas.getEscena().setMenuPrincipal();
        });

        // Añade todos los elementos al panel de pausa
        getChildren().addAll(titulo, continuar, salir);
    }

}
