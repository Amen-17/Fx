package app;

import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Menu extends Pane {

    private ArrayList<Boton> botones;

    public Menu(Stage stage) {
        setPrefSize(1200, 800);
        setStyle("-fx-background-color: BLACK;");

        botones = new ArrayList<>();

        Boton botonIniciar = new Boton(
                "file:./Fx/src/main/java/app/imgs/boton1",
                "file:./Fx/src/main/java/app/imgs/boton2",
                "Iniciar Juego",
                500, 300,
                () -> {
                    // Cambiar a la escena del juego
                    PanelPrincipal panelJuego = new PanelPrincipal();
                    Scene escenaJuego = new Scene(panelJuego, 1200, 800);
                    stage.setScene(escenaJuego);

                    Personaje pj = Personaje.getPers();
                    pj.rotacionRaton(escenaJuego);
                }
        );

        Boton botonSalir = new Boton(
                "file:src/main/java/app/imgs/boton1.png",
                "file:src/main/java/app/imgs/boton2.png",
                "Salir",
                500, 400,
                () -> {
                    stage.close();
                }
        );

        botones.add(botonIniciar);
        botones.add(botonSalir);

        getChildren().addAll(botonIniciar, botonSalir);
    }
}