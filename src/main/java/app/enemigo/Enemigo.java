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

    protected void muerte(){
        panel.getChildren().remove(this);//Borramos el enemigo
        GestorEnemigos.getLista().remove(this);//Borramos de la lista los que se salen de la pantalla
        t.stop(); //Paramos su animación
    }

    /**
     * Se mueve hacia la posición del Personaje, calcula la diferencia de posiciones y el trayecto hacía el.
     */
    // Mejorar todas las variables que he hecho
    protected void atacar() {
        // Obtener la posición absoluta del personaje en la escena
        Bounds posPJ = Personaje.getPos();
        double personajeX = posPJ.getMinX() + posPJ.getWidth() / 2;
        double personajeY = posPJ.getMinY() + posPJ.getHeight() / 2;

        // Obtener la posición del enemigo
        Bounds posEneAct = localToScene(getBoundsInLocal());
        double enemigoX = posEneAct.getCenterX();
        double enemigoY = posEneAct.getCenterY();

        //Calcula la diferencia entre las coordenanas X e Y
        double deltaX = personajeX - enemigoX;
        double deltaY = personajeY - enemigoY;

        // Calcula la distancia mediante el calculo de la hipotenusa.
        double distancia = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));

        // Normalizar el vector para que la velocidad sea constante
        double movX = (deltaX / distancia) * velocidad;
        double movY = (deltaY / distancia) * velocidad;

        setLayoutX(getLayoutX() + movX);
        setLayoutY(getLayoutY() + movY);

        //Obtiene la posicion del enemigo y comprueba si se choca con el personaje.
        comprobarMuerte();
    }

    /**
     * Rota su eje en base a la posición del Personaje
     */
    // Mejorar todas las variables que he hecho
    protected void rotar(){

        Bounds posPj = Personaje.getPos();
        Bounds posEneAct = localToScene(getBoundsInLocal());
        double personajeX = posPj.getMinX() + posPj.getWidth() / 2;
        double personajeY = posPj.getMinY() + posPj.getHeight() / 2;
        double enemigoX = posEneAct.getCenterX();
        double enemigoY = posEneAct.getCenterY();

        // Calcular la dirección
        double deltaX = personajeX - enemigoX;
        double deltaY = personajeY - enemigoY;
        // Calcular el ángulo de rotación en radianes y convertirlo a grados
        double anguloRadianes = Math.atan2(deltaY, deltaX);
        double anguloGrados = Math.toDegrees(anguloRadianes);

        // Aplicar la rotación al enemigo
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

    public abstract Bounds getPosc();
}
