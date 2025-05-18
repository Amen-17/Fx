package app.enemigo;

import app.paneles.PanelJuego;
import app.personaje.Personaje;
import app.personaje.Vida;
import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public abstract class Enemigo extends Rectangle {

    protected PanelJuego panel;
    protected int vida;
    protected double velocidad;
    protected AnimationTimer t;
    protected Image imagen;
    protected static double dificultad = 0;
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
        super(poX, poY, tamX, tamY);
        this.vida = vida;
        this.velocidad = velocidad;
        panel = PanelJuego.getPanelJuego();
        if (dificultad <= 1) dificultad += 0.01;
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
        Bounds posEneAct = localToScene(getBoundsInLocal());

        double deltaX = Personaje.getPos().getCenterX() - posEneAct.getCenterX();
        double deltaY = Personaje.getPos().getCenterY() - posEneAct.getCenterY();

        double distancia = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));

        double movX = (deltaX / distancia) * velocidad;
        double movY = (deltaY / distancia) * velocidad;

        setLayoutX(getLayoutX() + movX);
        setLayoutY(getLayoutY() + movY);

        comprobarMuerte();
    }

    /**
     * Rota su eje en base a la posición del Personaje
     */
    protected void rotar(){
        Bounds posEneAct = localToScene(getBoundsInLocal());

        double deltaX = Personaje.getPos().getCenterX() - posEneAct.getCenterX();
        double deltaY = Personaje.getPos().getCenterY() - posEneAct.getCenterY();

        double anguloRadianes = Math.atan2(deltaY, deltaX);
        double anguloGrados = Math.toDegrees(anguloRadianes);

        setRotate(anguloGrados);
    }

    public AnimationTimer getAnimation() {
        return t;
    }

    public int getVida() {
        return vida;
    }

    public void reducirVida() {
        vida--;
    }

    public static double getDiff(){
        return dificultad;
    }

    public abstract Bounds getPosc();
}
