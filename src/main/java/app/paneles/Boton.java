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

    /**
     * Constructor básico de un botón.
     * @param texto Texto que se leerá en el botón.
     * @param x Tamaño X.
     * @param y Tamaño Y.
     * @param accion Acción que realizará el botón.
     */
    public Boton(String texto, double x, double y, Runnable accion) {
        this(texto, x, y, 300, 100, accion); // Reutiliza el nuevo constructor con tamaño por defecto
    }

    /**
     * Constructor de un botón más personalizable.
     * @param texto Texto que se leerá en el botón.
     * @param x Tamaño X.
     * @param y Tmaño Y.
     * @param ancho Ancho del botón.
     * @param alto Alto del botón.
     * @param accion Acción que realizará el botón.
     */
    public Boton(String texto, double x, double y, double ancho, double alto, Runnable accion) {
        this.accion = accion;

        Rectangle fondo = new Rectangle(ancho, alto);
        fondo.setFill(Color.DARKSLATEBLUE);
        fondo.setArcWidth(20);
        fondo.setArcHeight(20);

        Text textoBoton = new Text(texto);
        textoBoton.setFont(Font.font("Arial", 24));
        textoBoton.setStyle("-fx-fill: white; -fx-font-weight: bold;");

        setLayoutX(x);
        setLayoutY(y);
        setAlignment(Pos.CENTER);
        getChildren().addAll(fondo, textoBoton);

        setOnMouseEntered(this::hoverEnter);
        setOnMouseExited(this::hoverExit);
        setOnMouseClicked(e -> accion.run());
    }

    private void hoverEnter(MouseEvent e) {
        ScaleTransition st = new ScaleTransition(Duration.millis(200), this);
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