package app.paneles;

import app.personaje.Personaje;
import app.personaje.Tiempo;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
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
}