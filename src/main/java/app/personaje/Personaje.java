package app.personaje;

import app.Disparo;
import app.paneles.Escenas;
import app.paneles.PanelJuego;
import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Personaje extends Rectangle {
    private PanelJuego panel;
    private static Personaje pers;
    private boolean movIzq, movDch, movArr, movAbj;  // Booleans para movimiento en cada dirección
    private static Bounds posPj;   // Guarda la posición del personaje en la escena
    private int cargador;
    private String nombre;
    private static AnimationTimer t;  // Temporizador para mover al personaje
    private Image img = new Image("file:src/main/java/app/imgs/PosPj.png");


    /**
     * Constructor básico del Personaje.
     */
    public Personaje() {
        super(550, 350, 70, 70); //Posicion X, Posicion Y, Tamaño X, Tamaño Y
        pers = this;
        panel = PanelJuego.getPanelJuego(); //Para enlazar el jugador con el panel
        moverPj(); // Inicia movimiento
        ponerEnAccion();  // Captura teclas y ratón
        cargador = 5; // Máximo de disparos activos
        setFill(new ImagePattern(img));
    }

    /**
     * Devuelve el personaje, si es null lo crea antes.
     * @return El personaje
     * Evita que se creen múltiples personajes. Solo existe uno durante la partida.
     */
    public static Personaje getPers(){
        if (pers == null){
            pers = new Personaje();
        }
        return pers;
    }

    /**
     * Reinicia el personaje
     */
    public void reiniciarPJ(){
        Vida.reiniciarVidas();
        setRotate(90);   // Pone dirección inicial
        setLayoutY(0);   // Coloca en la esquina superior
        setLayoutX(0);
        getPers();  // Se asegura de que exista
        getAni().start();    // Inicia el movimiento de nuevo
        movAbj = false; // detiene el movimiento
        movArr = false;
        movDch = false;
        movIzq = false;
    }

    public String getNombre(){
        return nombre;
    }

    /**
     * Pone al personaje en foco y captura las teclas que se pulsan y se dejan de pulsar.
     */
    private void ponerEnAccion() {
        sceneProperty().addListener((observar, antigua, nueva) //Pone el foco a esta clase
                -> {
            if (nueva != null) { //no se como funciona
                requestFocus(); // Captura foco para recibir eventos
            }
        });
        this.setOnKeyPressed(e -> {  // Marca los flags según la tecla pulsada
                                    // Incluye movimiento y pausa
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
            if (e.getCode() == KeyCode.P || e.getCode() == KeyCode.DOWN) {
                Escenas.getEscena().setPausa();
            }
        });
        this.setOnKeyReleased(e -> {    // Desactiva movimiento cuando se suelta tecla
                                       // También lanza disparo al soltar barra espaciadora
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
            // Calcula ángulo entre el personaje y el ratón
            // y gira el personaje en esa dirección

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
                disparar();  // Lanza disparo al hacer clic izquierdo
                System.out.println("Pium");
            }
        });
    }

    /**
     * Dispara una bala en la misma dirección en la que mira el Personaje, está limitado.
     */
    private void disparar(){
        // Calcula posición de disparo basada en ángulo
        // Crea una nueva bala (Disparo) y la añade al panel

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
        // Mueve el personaje en la dirección indicada
        // Solo si no se sale del panel
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
     * Llama al metodo de movimiento() y actualiza su variable con su posición actualizada.
     */
    private void moverPj() {
        t = new AnimationTimer() { //Para
            @Override
            public void handle(long l) {
                movimiento(); // Aplica movimiento según teclas pulsadas
                posPj = Personaje.this.localToScene(Personaje.this.getBoundsInLocal()); // Guarda posición actual
            }
        };
        t.start(); // Siempre que haga un AnimationTimer he de empezarlo, si no no hace caso.
    }

    public static AnimationTimer getAni(){
        return t; // Devuelve el temporizador, por si hace falta pararlo o reiniciarlo
    }

    public static Bounds getPos(){
        return posPj; // Devuelve la posición actual del personaje
    }
}
