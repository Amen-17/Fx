package app.enemigo;

import app.paneles.PanelJuego;
import app.personaje.Personaje;
import app.personaje.Vida;
import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

// Clase base para todos los enemigos, hereda de Rectangle para tener forma y posición.
public abstract class Enemigo extends Rectangle {

    protected PanelJuego panel;  // Referencia al panel principal donde se dibuja el enemigo.
    protected int vida;
    protected double velocidad;
    protected AnimationTimer t; // Temporizador de animaciones (para moverse o atacar).
    protected Image imagen;
    protected static double dificultad = 0; // Variable compartida por todos los enemigos que aumenta progresivamente.
    protected int puntuacion;

    /**
     * Constructor de Enemigo
     * @param poX Posición X de spawn
     * @param poY Posición Y de spawn
     * @param tamX Tamaño X
     * @param tamY Tamaño Y
     * @param vida Puntos de vida
     * @param velocidad Velocidad de movimiento
     * La dificultad aumenta según la generación de enemigos hasta llegar a 1.
     */
    public Enemigo(double poX, double poY, double tamX, double tamY, int vida, double velocidad) {
        super(poX, poY, tamX, tamY); // Llama al constructor de Rectangle con posición y tamaño.
        this.vida = vida;
        this.velocidad = velocidad;
        panel = PanelJuego.getPanelJuego();  // Obtiene el panel de juego para poder añadir o quitar el enemigo.
        if (dificultad <= 1) dificultad += 0.01;  // Aumenta la dificultad global progresivamente hasta un máximo de 1.
    }

    /**
     * Comprueba si el Enemigo ha muerto mediante colisión contra el Personaje
     */
    protected void comprobarMuerte() {
        if (localToScene(getBoundsInLocal()).intersects(Personaje.getPos())) {
            muerte();
            Vida.reducirVida(); //Nos quitan una vida
        }
    }

    /**
     * El enemigo que llame a este metodo "morirá", desaparecerá de la lista, del panel y se parará su animación.
     */
    protected void muerte(){
        panel.getChildren().remove(this);//Borramos el enemigo
        GestorEnemigos.getLista().remove(this);//Borramos de la lista
        t.stop(); //Paramos su animación
    }

    /**
     * Se mueve hacia la posición del Personaje, calcula la diferencia de posiciones y el trayecto hacía el.
     */
    // Mejorar todas las variables que he hecho
    protected void atacar() {
        Bounds posEneAct = localToScene(getBoundsInLocal()); // Obtiene su posición actual en la escena.

        // Calcula diferencias de posición respecto al personaje.
        double deltaX = Personaje.getPos().getCenterX() - posEneAct.getCenterX();
        double deltaY = Personaje.getPos().getCenterY() - posEneAct.getCenterY();

        // Distancia total a recorrer.
        double distancia = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));

        // Calcula el movimiento proporcional para mantener la velocidad.
        double movX = (deltaX / distancia) * velocidad;
        double movY = (deltaY / distancia) * velocidad;

        // Actualiza posición.
        setLayoutX(getLayoutX() + movX);
        setLayoutY(getLayoutY() + movY);

        comprobarMuerte(); // Verifica si ha alcanzado al personaje.
    }

    /**
     * Rota su eje en base a la posición del Personaje
     */
    protected void rotar(){
        Bounds posEneAct = localToScene(getBoundsInLocal()); // Posición del enemigo.

        // Diferencias de posición.
        double deltaX = Personaje.getPos().getCenterX() - posEneAct.getCenterX();
        double deltaY = Personaje.getPos().getCenterY() - posEneAct.getCenterY();

        // Calcula ángulo en radianes y lo pasa a grados.
        double anguloRadianes = Math.atan2(deltaY, deltaX);
        double anguloGrados = Math.toDegrees(anguloRadianes);

        setRotate(anguloGrados); // Aplica la rotación.
    }

    /**
     * Devuelve el AnimationTimer del enemigo.
     */
    public AnimationTimer getAnimation() {
        return t;
    }

    public int getVida() {
        return vida;
    } //Devuelve la vida actual del enemigo.

    public void reducirVida() {
        vida--;
    } //Resta 1 punto de vida al enemigo.

    public static double getDiff(){
        return dificultad;
    }

    /**
     * Metodo abstracto que obliga a las subclases a devolver su posición (para colisiones, disparos, etc.).
     */
    public abstract Bounds getPosc();
}
