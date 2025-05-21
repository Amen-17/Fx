package app;

import app.enemigo.Enemigo;
import app.enemigo.GestorEnemigos;
import app.paneles.PanelJuego;
import app.personaje.Personaje;
import app.personaje.Puntuacion;
import app.personaje.Vida;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * Clase que representa un disparo en el juego. Puede ser disparado por el jugador o por un enemigo.
 * Hereda de Rectangle para usar su forma y posición en pantalla.
 */


public class Disparo extends Rectangle {
    private PanelJuego panel;              // Referencia al panel donde se dibuja el disparo
    private AnimationTimer t;              // Timer que controla el movimiento del disparo
    private boolean enPantalla;            // Indica si el disparo está dentro del área visible
    private static int nDisparos = 0;      // Contador de disparos en pantalla del jugador
    private double dirX, dirY;             // Dirección del disparo (calculada en X e Y)
    private int velocidad;                 // Velocidad del disparo
    boolean propietario;                   // True si es del jugador, false si es de un enemigo

    /**
     * Constructor del disparo.
     * @param poX Coordenada X desde donde se lanza
     * @param poY Coordenada Y desde donde se lanza
     * @param angulo Ángulo de rotación para dirigir el disparo
     * @param prop Indica si el disparo pertenece al jugador (true) o a un enemigo (false)
     */
    public Disparo(double poX, double poY, double angulo, boolean prop) {
        super(poX - 2, poY - 15, 2, 15); // Posiciona el disparo (con ajustes) y le da dimensiones
        setRotate(angulo + 90); // Corrige el ángulo, porque 0º en JavaFX apunta a la derecha
        panel = PanelJuego.getPanelJuego();
        propietario = prop;
        enPantalla = true;
        setFill(Color.YELLOW); // Color del disparo
        trayectoria(); // Inicia el movimiento del disparo
        if (prop) nDisparos++; // Solo se cuentan los disparos del jugador
        velocidad = 4;
        // Calcula la dirección del disparo usando trigonometría
        dirX = Math.cos(Math.toRadians(angulo)) * velocidad;
        dirY = Math.sin(Math.toRadians(angulo)) * velocidad;
    }

    /**
     * Controla el movimiento del disparo en pantalla y las colisiones.
     * Se ejecuta constantemente mientras el disparo esté activo.
     */
    private void trayectoria() {
        t = new AnimationTimer() {
            @Override
            public void handle(long l) {
                // Mueve el disparo en cada frame
                setLayoutY(getLayoutY() + dirY);
                setLayoutX(getLayoutX() + dirX);

                // Si el disparo sale de la pantalla, se marca como no visible
                if (getBoundsInParent().getMaxY() <= 0 || getBoundsInParent().getMinY() >= panel.getHeight() ||
                        getBoundsInParent().getMaxX() <= 0 || getBoundsInParent().getMinX() >= panel.getWidth()) {
                    enPantalla = false;
                }

                // Obtiene la lista de enemigos actuales
                ArrayList<Enemigo> lista = GestorEnemigos.getLista();

                // Recorre todos los enemigos y comprueba colisiones
                for (int cont = 0; cont < lista.size(); cont++) {
                    Enemigo e = lista.get(cont);
                    // Solo colisiona si es un disparo del jugador
                    if (getBoundsInParent().intersects(e.getBoundsInParent()) && propietario) {
                        enPantalla = false;      // Marca el disparo como fuera de pantalla (desaparecerá)
                        e.reducirVida();         // Quita vida al enemigo
                        System.out.println("Has dado un disparo");

                        if (e.getVida() <= 0) {  // Si el enemigo muere:
                            e.getAnimation().stop();           // Detiene su movimiento
                            panel.getChildren().remove(e);     // Lo elimina del panel
                            lista.remove(e);                   // Lo elimina de la lista de enemigos
                            Puntuacion.subirPuntuacion(1);     // Aumenta puntuación del jugador
                        }
                    }
                }

                // Si el disparo ya no está activo, lo eliminamos
                if (!enPantalla) {
                    t.stop();                            // Para el movimiento
                    nDisparos--;                         // Reduce el contador
                    panel.getChildren().remove(Disparo.this); // Elimina el disparo del panel
                }

                // Verifica si un disparo enemigo ha alcanzado al jugador
                comprobarColision();
            }
        };
        t.start(); // Inicia la animación
    }

    /**
     * Comprueba si un disparo enemigo ha colisionado con el personaje.
     * Si es así, elimina el disparo, detiene su animación y reduce una vida.
     */
    public void comprobarColision() {
        // Solo verifica si el disparo NO es del jugador
        if (getBoundsInParent().intersects(Personaje.getPos()) && !propietario) {
            this.getAnimation().stop();
            System.out.println("Te ha dado una bala");
            panel.getChildren().remove(Disparo.this);
            Vida.reducirVida(); // Reduce vida del jugador
        }
    }

    /**
     * Devuelve el número de disparos del jugador activos actualmente en pantalla.
     */
    public static int getnDisparos() {
        return nDisparos;
    }

    /**
     * Devuelve la animación del disparo, útil para detenerla desde fuera.
     */
    public AnimationTimer getAnimation() {
        return t;
    }
}
