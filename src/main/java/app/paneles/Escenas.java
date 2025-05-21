package app.paneles;

import app.Util;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Escenas extends Pane {

    private Stage stage;  // La ventana principal donde se muestran las escenas
    private Scene panelPri;  // Escena del juego principal (donde se juega)
    private Scene gameOver;   // Escena que se muestra cuando el juego termina
    private Scene menu;
    private Scene act;  // Escena activa actualmente (la que se está mostrando)
    private static Escenas e;  // Instancia singleton de esta clase (única para toda la aplicación)
    private Scene pausa;

    /**
     * Constructor del gestor de Scenes.
     * Constructor privado (patrón singleton)
     * Se inicializan las escenas que no cambian (menú y pausa),
     * y se obtiene la ventana principal (Stage).
     */
    private Escenas() {
        stage = Util.getStage();   // Obtener la ventana principal del programa
        menu = new Scene(new Menu(stage), 1200, 800);  // Crear escena menú (con tamaño fijo)
        pausa = new Scene(new Pausa(stage), 1200, 800);
    }



    public static Stage getStage() {
        return getEscena().stage;
    }

    /**
     * Configura y muestra la escena principal de juego.
     * Crea la escena con el panel principal, la asigna al stage,
     * y actualiza el campo de escena activa.
     */
    public void setPanelPri() {
        panelPri = new Scene(new PanelPrincipal(), 1200, 800);
        stage.setScene(panelPri);  // Cambia la escena mostrada en la ventana
        act = panelPri;      // Actualiza la escena activa
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
     *  Además, detiene el juego a través de Util.
     */
    public void setGameOver() {
        gameOver = new Scene(new GameOver(stage), 1200, 800);
        stage.setScene(gameOver);
        act = gameOver;
        Util.pararJuego(); // Lógica para detener timers, animaciones, etc.
    }

    /**
     * Crea y enseña la escena de pausa.
     */
    public void setPausa() {
        stage.setScene(pausa);
        act = pausa;
    }

    /**
     * Devuelve la escena actualmente activa.
     */
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


    /**
     * Metodo sin implementar para cambiar la raíz de la escena de pausa.
     * Probablemente debería cambiar el contenido mostrado dentro de la escena de pausa,
     * por ejemplo, para actualizar la interfaz de pausa.
     */
    public void setRoot(Pausa pausa) {
        // Pendiente de implementar.
        this.pausa.setRoot(pausa);
    }
}
