package app.paneles;

import app.datos.HistorialPartidas;
import app.datos.Partida;
import app.personaje.Personaje;
import app.personaje.Tiempo;
import app.personaje.Vida;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Menu extends Pane {

    private ArrayList<Boton> botones;

    public Menu(Stage stage) {
        setPrefSize(1200, 800);
        setStyle("-fx-background-color: linear-gradient(to bottom, #1e1e2f, #38384f);");

        botones = new ArrayList<>();

        // Botón que inicia el juego
        Boton botonIniciar = new Boton(
                "Iniciar Juego",
                450, 300,
                () -> {
                    PanelPrincipal panelJuego = new PanelPrincipal();
                    Scene escenaJuego = new Scene(panelJuego, 1200, 800);
                    stage.setScene(escenaJuego);

                    Personaje pj = Personaje.getPers();
                    pj.rotacionRaton(escenaJuego);
                    Tiempo t = Tiempo.getTiempoTotal();
                    t.iniciarCronometro();
                }
        );

        Boton botonSalir = new Boton(
                "Salir",
                450, 420,
                stage::close
        );

        botones.add(botonIniciar);
        botones.add(botonSalir);

        getChildren().addAll(botones);
    }

    public static void GameOver(Stage stage) {
        Pane gameOverPane = new Pane();
        gameOverPane.setPrefSize(1200, 800);
        gameOverPane.setStyle("-fx-background-color: linear-gradient(to bottom, #000000, #4b0000);");

        try {
            HistorialPartidas historial = new HistorialPartidas();
            Partida ultima = historial.ultimaPartida();
            if (ultima != null) {
                Label resultado = new Label("Última Partida: " + ultima.toString());
                resultado.setTextFill(Color.WHITE);
                resultado.setFont(new Font("Arial", 24));
                resultado.setLayoutX(400);
                resultado.setLayoutY(200);
                gameOverPane.getChildren().add(resultado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Boton botonIniciar = new Boton("Volver al Juego", 450, 300, () -> {
            Vida.reiniciarVidas();

            PanelPrincipal panelJuego = new PanelPrincipal();
            Scene escenaJuego = new Scene(panelJuego, 1200, 800);
            stage.setScene(escenaJuego);

            Personaje pj = Personaje.getPers();
            pj.rotacionRaton(escenaJuego);

            Tiempo t = Tiempo.getTiempoTotal();
            t.iniciarCronometro();
        });

        Boton botonSalir = new Boton("Salir", 450, 430, stage::close);

        gameOverPane.getChildren().addAll(botonIniciar, botonSalir);
        stage.setScene(new Scene(gameOverPane));
    }
}