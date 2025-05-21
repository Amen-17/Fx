package app.paneles;

import app.Util;
import app.personaje.Personaje;
import javafx.scene.layout.Pane;

public class PanelJuego extends Pane {
    // Instancia estática para implementar el patrón singleton y que sólo haya un panel
    private static PanelJuego panel; //Para guardar el objeto que se cree en el constructor
    private Personaje pj;  // Referencia al personaje que se moverá dentro del panel

    /**
     * Constructor del panel, agrega el personaje al panel y comienza la generación de enemigos.
     */
    private PanelJuego(){
        panel = this; //Guardamos dentro de esta variable el objeto que se guarde
        setPrefSize(1200, 800);
        pj = Personaje.getPers();  // Obtiene el personaje único
        montarEscena();  // Agrega elementos gráficos y personajes al panel
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
     * Metodo privado que monta la escena principal del juego.
     * Añade el suelo con la ayuda de la clase Util y agrega el personaje al panel.
     */
    private void montarEscena() {
        Util.pintarSuelo(this,1200,800);  // Dibuja el suelo de fondo
        getChildren().add(pj);  // Añade el personaje al panel para que se muestre y pueda interactuar
    }

}

