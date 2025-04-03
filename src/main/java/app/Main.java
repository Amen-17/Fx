package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Scene escena = new Scene(new PanelPrincipal(),1200,800);
        stage.setScene(escena);
        stage.show();
        Personaje pj = Personaje.getPers();
        pj.rotacionRaton(escena);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
