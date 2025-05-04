package app.paneles;

import javafx.animation.ScaleTransition;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.util.Duration;

public class Boton extends StackPane {

    private Runnable accion;

    public Boton(String texto, double x, double y, Runnable accion) {
        this.accion = accion;

        Rectangle fondo = new Rectangle(300, 100);
        fondo.setFill(Color.DARKSLATEBLUE); // Color base del botón
        fondo.setArcWidth(20); // Bordes redondeados
        fondo.setArcHeight(20);

        Text textoBoton = new Text(texto);
        textoBoton.setFont(Font.font("Arial", 24));
        textoBoton.setStyle("-fx-fill: white; -fx-font-weight: bold;");

        setLayoutX(x);
        setLayoutY(y);
        setAlignment(Pos.CENTER);
        getChildren().addAll(fondo, textoBoton);

        // lo que  le puede pasar al ratón
        setOnMouseEntered(this::hoverEnter);
        setOnMouseExited(this::hoverExit);
        setOnMouseClicked(e -> accion.run());
    }

    private void hoverEnter(MouseEvent e) {
        ScaleTransition st = new ScaleTransition(Duration.millis(200), this); //permite que el boton aumente
        st.setToX(1.1);
        st.setToY(1.1);
        st.play();
    }

    private void hoverExit(MouseEvent e) {
        ScaleTransition st = new ScaleTransition(Duration.millis(200), this);
        st.setToX(1.0);
        st.setToY(1.0);
        st.play();
    }
}