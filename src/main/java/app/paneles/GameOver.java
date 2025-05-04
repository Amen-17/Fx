package app.paneles;

import app.datos.HistorialPartidas;
import app.datos.Partida;
import app.personaje.Personaje;
import app.personaje.Tiempo;
import app.personaje.Vida;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameOver extends Pane {

    public GameOver(Stage stage) {
        setPrefSize(1200, 800);
        setStyle("-fx-background-color: linear-gradient(to bottom, #000000, #4b0000);");

        // Texto de última partida
        try {
            HistorialPartidas historial = new HistorialPartidas();
            Partida ultima = historial.ultimaPartida();

            if (ultima != null) {
                Label resultado = new Label("Última Partida: " + ultima.toString());
                resultado.setTextFill(Color.WHITE);
                resultado.setFont(new Font("Arial", 24));
                resultado.setLayoutX(400);
                resultado.setLayoutY(250); // más arriba
                getChildren().add(resultado);

                // Botón Iniciar debajo del texto
                Boton botonIniciar = new Boton(
                        "Volver al Juego",
                        525, 320, // centrado
                        200, 50,
                        () -> {
                            Vida.reiniciarVidas();

                            PanelPrincipal panelJuego = new PanelPrincipal();
                            Scene escenaJuego = new Scene(panelJuego, 1200, 800);
                            stage.setScene(escenaJuego);

                            Personaje pj = Personaje.getPers();
                            pj.rotacionRaton(escenaJuego);

                            Tiempo t = Tiempo.getTiempoTotal();
                            t.iniciarCronometro();
                        }
                );

                // Botón Salir debajo del anterior
                Boton botonSalir = new Boton(
                        "Salir",
                        525, 380, //coloca boton
                        200, 50, //su tamaño
                        stage::close
                );

                getChildren().addAll(botonIniciar, botonSalir);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
