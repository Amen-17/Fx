package app;

import javafx.scene.layout.Pane;

public class PanelJuego extends Pane {
    private static PanelJuego panel; //Para guardar el objeto que se cree en el constructor
    private Personaje pj;

    public PanelJuego(){
        panel = this; //Guardamos dentro de esta variable el objeto que se guarde
        pj = new Personaje();
        montarEscena();
        GestorEnemigos.comenzar();
    }

    private void montarEscena() {
        getChildren().add(pj); //Para añadir el personaje al panel
    }

    public static PanelJuego getPanel(){
        return panel;
    }
}

