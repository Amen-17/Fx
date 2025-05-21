package app.paneles;

import app.personaje.Tiempo;
import javafx.scene.layout.BorderPane;

public class PanelPrincipal extends BorderPane {

    // Panel inferior estático para poder acceder a él desde otras clases
    private static PanelInf panelinf;

    /**
     * crea el panel en el que se juega llamando a los metodos que conforman tambien este panel
     * Constructor que crea el panel principal del juego.
     * Este panel contiene el panel del juego en el centro y el panel de información en la parte inferior.
     * También configura el panel para que pueda recibir eventos de teclado (como ESC para pausar).
     */
    public PanelPrincipal(){
        setCenter(PanelJuego.getPanelJuego());  // Coloca el panel del juego en el centro de la pantalla
        setBottom(PanelInf.getPanelinf()); // Coloca el panel de información en la parte inferior
        panelinf = PanelInf.getPanelinf();   // Guarda la referencia al panel inferior



        // IMPORTANTE: Permite que este panel reciba eventos de teclado
        setFocusTraversable(true);

        // Evento de teclado
        // Detecta si se pulsa la tecla ESCAPE para pausar el juego
        setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ESCAPE:
                    pausarJuego();
                    break;
            }
        });
    }

    /**
     * llama al panelinf
     * Devuelve el panel inferior que muestra información del juego como vidas, puntuación, etc.
     * @return panelinf (panel de información)
     */
    public static PanelInf getPanelinf(){
        return panelinf;
    }

    /**
     * Metodo que pausa el juego deteniendo el cronómetro y cambiando la vista a la pantalla de pausa.
     */
    private void pausarJuego() {
        Tiempo.detenerCronometro(); //  detiene el cronometro
        Escenas.getEscena().setRoot(new Pausa(Escenas.getStage()));  // Muestra la escena de pausa
    }

}
