package app.paneles;

import app.Util;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Escenas extends Pane {

    private Stage stage;
    private Scene panelPri;
    private Scene gameOver;
    private Scene menu;
    private Scene act;
    private static Escenas e;
    private Scene pausa;

    /**
     * Constructor del gestor de Scenes.
     */
    private Escenas() {
        stage = Util.getStage();
        menu = new Scene(new Menu(stage), 1200, 800);
        pausa = new Scene(new Pausa(stage), 1200, 800);
    }

    /**
     * Crea y enseña la escena principal.
     */
    public void setPanelPri() {
        panelPri = new Scene(new PanelPrincipal(), 1200, 800);
        stage.setScene(panelPri);
        act = panelPri;
    }

    /**
     * Crea y enseña la escena de menú.
     */
    public void setMenu() {
        stage.setScene(menu);
        act = menu;
    }

    /**
     * Crea y enseña la escena de game over y para el juego.
     */
    public void setGameOver() {
        gameOver = new Scene(new GameOver(stage), 1200, 800);
        stage.setScene(gameOver);
        act = gameOver;
        Util.pararJuego();
    }

    /**
     * Crea y enseña la escena de pausa.
     */
    public void setPausa() {
        stage.setScene(pausa);
        act = panelPri;
    }

    public Scene getAct() {
        return act;
    }

    /**
     * Devuelve el gestor, si es null lo crea antes.
     */
    public static Escenas getEscena() {
        if (e == null) {
            e = new Escenas();
        }
        return e;
    }


}
