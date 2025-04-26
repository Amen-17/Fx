package app;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Personaje extends Rectangle {
    private PanelJuego panel;
    private static Personaje pers;
    private boolean movIzq, movDch, movArr, movAbj;
    private static Bounds posPj;
    private int cargador;
    private String nombre;

    public Personaje(String nombre) {
        super(550, 350, 20, 20); //Posicion X, Posicion Y, Tamaño X, Tamaño Y
        this.nombre=nombre;
        panel = PanelJuego.getPanel(); //Para enlazar el jugador con el panel
        setFill(Color.CHOCOLATE); //Color
        ponerEnAccion();
        moverPj();
        pers = this;
        cargador = 5;
    }
    public Personaje() {
        super(550, 350, 20, 20); //Posicion X, Posicion Y, Tamaño X, Tamaño Y
        panel = PanelJuego.getPanel(); //Para enlazar el jugador con el panel
        setFill(Color.CHOCOLATE); //Color
        ponerEnAccion();
        moverPj();
        pers = this;
        cargador = 5;
    }

    public String getNombre(){
        return nombre;
    }

    public static Personaje getPers(){
        return pers;
    }

    /**
     * Pone al personaje en foco y captura las teclas que se pulsan y se dejan de pulsar.
     */
    private void ponerEnAccion() {
        sceneProperty().addListener((observar, antigua, nueva) //Pone el foco a esta clase
                -> {
            if (nueva != null) { //no se como funciona
                requestFocus();
            }
        });
        this.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) {
                movIzq = true;
            }
            if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT) {
                movDch = true;
            }
            if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP) {
                movArr = true;
            }
            if (e.getCode() == KeyCode.S || e.getCode() == KeyCode.DOWN) {
                movAbj = true;
            }
        });
        this.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.A || e.getCode() == KeyCode.LEFT) {
                movIzq = false;
            }
            if (e.getCode() == KeyCode.D || e.getCode() == KeyCode.RIGHT) {
                movDch = false;
            }
            if (e.getCode() == KeyCode.W || e.getCode() == KeyCode.UP) {
                movArr = false;
            }
            if (e.getCode() == KeyCode.S || e.getCode() == KeyCode.DOWN) {
                movAbj = false;
            }
            if (e.getCode()== KeyCode.SPACE){
                disparar();
            }
        });
    }

    /**
     *Captura la posición del puntero del raton y calcula en angulo para así rotar al Personaje en ese angulo.
     * @param scene La escena en la que se encuentra el Personaje.
     */
    public void rotacionRaton(Scene scene) {
        scene.setOnMouseMoved(e -> {
            double personajeX = posPj.getMinX() + posPj.getWidth() / 2;
            double personajeY = posPj.getMinY() + posPj.getHeight() / 2;

            // Obtener la posición del ratón
            double mouseX = e.getX();
            double mouseY = e.getY();

            // Calcular el ángulo en radianes y convertirlo a grados
            double angle = Math.toDegrees(Math.atan2(mouseY - personajeY, mouseX - personajeX));

            // Aplicar la rotación al personaje
            this.setRotate(angle);
        });
        scene.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                disparar();
                System.out.println("Pium");
            }
        });
    }

    /**
     * Dispara una bala en la misma dirección en la que mira el Personaje, está limitado.
     */
    private void disparar(){
        if (Disparo.getnDisparos() < cargador){ //Limitamos el numero de disparos según el cargador.
            //Angulo actual
            double angulo = getRotate();
            double radio = posPj.getHeight() / 2; // Distancia desde el centro a la parte superior
            double anguloRad = Math.toRadians(angulo);
            //Posicion X
            double disparoX = posPj.getCenterX() + Math.cos(anguloRad) * radio;
            //Posicion Y
            double disparoY = posPj.getCenterY() + Math.sin(anguloRad) * radio;
            Disparo d = new Disparo(disparoX,disparoY,angulo,true);
            //Agregar el disparo a la pantalla
            panel.getChildren().add(d);
        }
    }

    /**
     * Mueve el personaje según las teclas que se han pulsado y si está dentro de los limites del panel
     */
    private void movimiento() {
        if (movIzq && getBoundsInParent().getMinX() > 0) {
            setLayoutX(getLayoutX() - 3);
        }
        if (movDch && getBoundsInParent().getMaxX() < panel.getWidth()) {
            setLayoutX(getLayoutX() + 3);
        }
        if (movAbj && getBoundsInParent().getMaxY() < panel.getHeight()) {
            setLayoutY(getLayoutY() + 3);
        }
        if (movArr && getBoundsInParent().getMinY() > 0) {
            setLayoutY(getLayoutY() - 3);
        }
    }

    /**
     * Llama al método de movimiento() y actualiza su variable con su posición actualizada.
     */
    private void moverPj() {
        AnimationTimer t = new AnimationTimer() { //Para
            @Override
            public void handle(long l) {
                movimiento();
                posPj = Personaje.this.localToScene(Personaje.this.getBoundsInLocal());
            }
        };
        t.start(); // Siempre que haga un AnimationTimer he de empezarlo, si no no hace caso.
    }

    public static Bounds getPos(){
        return posPj;
    }
}
