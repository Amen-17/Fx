package app.paneles;

import app.personaje.Tiempo;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Pausa extends Pane {

    /**
     * detiene el juego
     * @param stage el scenario
     */
    public Pausa(Stage stage){
        setPrefSize(1200, 800);
        setStyle("-fx-background-color: rgba(0,0,0,0.02);");

        Boton continuar = new Boton("Continuar", 450, 300, () ->{
        Escenas.getEscena().setPanelPri();
        Tiempo.iniciarCronometro();
        });

        Boton nombreBoton = new Boton("no se", 450, 350, () ->{
            Escenas.getEscena().setPanelPri();
        });
    }



}
