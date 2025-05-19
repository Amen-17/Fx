package app.paneles;

import app.Util;
import app.personaje.Personaje;
import javafx.scene.layout.Pane;

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

    /**
     * si no esta crado el panel lo crea, y si ya existe lo devuelve
     * @return
     */
    public static PanelJuego getPanelJuego(){
        if (panel == null){
            panel = new PanelJuego();
        }
        return panel;
    }

    /**
     * monta la escena
     */
    private void montarEscena() {
        Util.pintarSuelo(this,1200,800);
        getChildren().add(pj); //Para añadir el personaje al panel
    }

}

