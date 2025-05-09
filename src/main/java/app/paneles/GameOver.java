package app.paneles;

import app.Util;
import app.datos.HistorialPartidas;
import app.datos.Partida;
import app.personaje.Vida;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameOver extends Pane {
    private static GameOver gameOver;

    public GameOver(Stage stage) {
        setPrefSize(1200, 800);
        setStyle("-fx-background-color: linear-gradient(to bottom, #000000, #4b0000);");
        gameOver = this;
        try {
            HistorialPartidas historial = new HistorialPartidas();
            Partida ultima = historial.ultimaPartida();
            if (ultima != null) {
                Label resultado = new Label("Última Partida: " + ultima.toString());
                resultado.setTextFill(Color.WHITE);
                resultado.setFont(new Font("Arial", 24));
                resultado.setLayoutX(280);
                resultado.setLayoutY(200);
                gameOver.getChildren().add(resultado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Boton botonIniciar = new Boton("Volver al Juego", 450, 300, () -> {
            Util.iniciarJuego();
        });

        Boton botonSalir = new Boton("Salir", 450, 430, stage::close);

        getChildren().addAll(botonIniciar, botonSalir);
    }
}
