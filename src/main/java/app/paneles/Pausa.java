package app.paneles;

import app.Util;
import app.datos.Partida;
import app.personaje.Tiempo;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Pausa extends Pane {

    public Pausa(Stage stage){
        setPrefSize(1200, 800);
        setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");



        Boton continuar = new Boton("Continuar", 450, 300, () ->{
        Escenas.getEscena().setPanelPri();
        Tiempo.iniciarCronometro();
        });

        Boton nombreBoton = new Boton("no se", 450, 350, () ->{
            Escenas.getEscena().setPanelPri();
        });
    }



}
