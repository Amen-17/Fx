package app.paneles;

import app.Util;
import app.datos.HistorialPartidas;
import app.datos.Partida;
import app.personaje.Personaje;
import app.personaje.Tiempo;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class Menu extends Pane {

    private ArrayList<Boton> botones;
    private static Pane menu;
    private static Pane gameOver;
    private static PanelPrincipal panelPrin;

    /**
     * crea la primera ventana que se ve, permite dar opcionde iniciar el juego llamando al util, o salir
     * @param stage
     */
    public Menu(Stage stage) {
        setPrefSize(1200, 800);
        setStyle("-fx-background-color: linear-gradient(to bottom, #1e1e2f, #38384f);");

        botones = new ArrayList<>();

        // Botón que inicia el juego
        Boton botonIniciar = new Boton("Iniciar Juego",450, 300,() -> {
                    Escenas.getEscena().setPanelPri();
                    Util.iniciarJuego();
                    Personaje.getPers().rotacionRaton(Escenas.getEscena().getAct());
                    Tiempo.getTiempoTotal();
                }
        );

        Boton botonSalir = new Boton("Salir",450, 420,stage::close);

        botones.add(botonIniciar);
        botones.add(botonSalir);
        getChildren().addAll(botones);
        HistorialPartidas.top3Partidas();

        try {
            List<Partida> top3 = HistorialPartidas.top3Partidas(); // o new HistorialPartidas().top3Partidas()

            VBox topBox = new VBox(10); // 10px de espacio entre elementos
            topBox.setLayoutX(450);
            topBox.setLayoutY(100);

            Label titulo = new Label("TOP 3 PARTIDAS");
            titulo.setTextFill(Color.WHITE);
            titulo.setFont(new Font("Arial", 20));
            topBox.getChildren().add(titulo);

            for (Partida partida : top3) {
                Label label = new Label(partida.toString());
                label.setTextFill(Color.WHITE);
                label.setFont(new Font("Arial", 16));
                topBox.getChildren().add(label);
            }

            getChildren().add(topBox);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}