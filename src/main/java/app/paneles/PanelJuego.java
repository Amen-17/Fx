package app.paneles;

import app.Util;
import app.personaje.Personaje;
import app.enemigo.GestorEnemigos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Scanner;

public class PanelJuego extends Pane {
    private static PanelJuego panel; //Para guardar el objeto que se cree en el constructor
    private Personaje pj;

    /**
     * Constructor del panel, agrega el personaje al panel y comienza la generación de enemigos.
     */
    private PanelJuego(){
        panel = this; //Guardamos dentro de esta variable el objeto que se guarde
        setPrefSize(1200, 800);
        pj = Personaje.getPers();
        montarEscena();
    }

    public static PanelJuego getPanelJuego(){
        if (panel == null){
            panel = new PanelJuego();
        }
        return panel;
    }

    private void montarEscena() {
        Util.llenarFondoConBaldosas(this,1200,800);
        getChildren().add(pj); //Para añadir el personaje al panel
    }

}

