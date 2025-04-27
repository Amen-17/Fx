package app;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.Pos;

public class Boton extends StackPane {

    private Image imagenNormal;
    private Image imagenHover;
    private ImageView imagenVista;
    private Runnable accion;

    public Boton(String rutaNormal, String rutaHover, String texto, double x, double y, Runnable accion) {
        imagenNormal = new Image(rutaNormal);
        imagenHover = new Image(rutaHover);
        imagenVista = new ImageView(imagenNormal);
        this.accion = accion;

        // --- Ajustes extra para que no tape el texto ---
        imagenVista.setPreserveRatio(true); // Mantener proporción
        imagenVista.setFitWidth(300);        // Ancho fijo (puedes cambiarlo)
        imagenVista.setFitHeight(100);       // Alto fijo (puedes cambiarlo)

        // Texto encima
        Text textoBoton = new Text(texto);
        textoBoton.setFont(Font.font("Arial", 24));
        textoBoton.setStyle("-fx-fill: white; -fx-font-weight: bold;");

        // Configuraciones del StackPane
        setLayoutX(x);
        setLayoutY(y);
        setAlignment(Pos.CENTER); // Centrar todo

        getChildren().addAll(imagenVista, textoBoton);

        // Eventos de ratón
        setOnMouseEntered((MouseEvent e) -> {
            imagenVista.setImage(imagenHover);
        });

        setOnMouseExited((MouseEvent e) -> {
            imagenVista.setImage(imagenNormal);
        });

        setOnMouseClicked((MouseEvent e) -> {
            accion.run();
        });
    }
}