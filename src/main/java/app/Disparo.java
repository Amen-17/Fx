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

public class Disparo extends Rectangle {
    private PanelJuego panel;
    private AnimationTimer t;
    private boolean enPantalla;
    private static int nDisparos=0;
    private double dirX,dirY;
    private int velocidad;
    boolean propietario; //True si es del personaje, false si son de enemigos.

    /**
     * crea la accion de disparar, su diseño
     * @param poX
     * @param poY
     * @param angulo
     * @param prop
     */
    public Disparo(double poX, double poY, double angulo,boolean prop){
        super(poX-2,poY-15,2,15);
        setRotate(angulo+90); //Hay que añadirle 90º debido a que el angulo 0º es hacia la derecha.
        panel = PanelJuego.getPanelJuego();
        propietario = prop;
        enPantalla = true;
        setFill(Color.YELLOW);
        trayectoria();
        if (prop) nDisparos++; //Solo contamos los disparos si son del Personaje
        velocidad = 4;
        dirX = Math.cos(Math.toRadians(angulo)) * velocidad;
        dirY = Math.sin(Math.toRadians(angulo)) * velocidad;
    }

    /**
     * Primero de todo, no se asusten.
     * Este método lo único que hace es:
     * > Mover la bala
     * > Comprobar si la bala choca contra cualquier enemigo almacenado en la lista.
     * > Si se sale la bala de la pantalla la elimina
     */
    private void trayectoria(){
        t = new AnimationTimer() {
            @Override
            public void handle(long l) {
                setLayoutY(getLayoutY()+dirY);
                setLayoutX(getLayoutX()+dirX);
                //Si sale por cualquiera de los 4 bordes.
                if (getBoundsInParent().getMaxY() <= 0 || getBoundsInParent().getMinY() >= panel.getHeight() ||
                        getBoundsInParent().getMaxX() <= 0 || getBoundsInParent().getMinX() >= panel.getWidth()) {
                    enPantalla = false;
                }

                ArrayList<Enemigo> lista = GestorEnemigos.getLista();

                for (int cont=0; cont < lista.size();cont++){
                    Enemigo e = lista.get(cont);
                    if (getBoundsInParent().intersects(e.getBoundsInParent()) && propietario){ //Si las colisiones chocan y la bala es lanzada por el usuario
                        enPantalla = false;
                        e.reducirVida();
                        System.out.println("Has dado un disparo");
                        if (e.getVida() <=0){ //Si el enemigo se queda sin vida se para su animación, se borra del panel y del array list
                            e.getAnimation().stop();
                            panel.getChildren().remove(e);
                            lista.remove(e);
                            Puntuacion.subirPuntuacion(1);
                        }
                    }
                }
                if (!enPantalla){ //si sale de la pantalla se para la animacion y borra el disparo
                    t.stop();
                    nDisparos--;
                    panel.getChildren().remove(Disparo.this); //Para especificar que queremos borrar el objeto, no el AnimationTimer.
                }
                comprobarColision();
            }
        };
        t.start();
    }
    //Comprueba que la bala enemiga impacta en mí, pero no se bien como implementarla ni donde.

    /**
     * comprueba si las balas chocaron con el personaje
     */
    public void comprobarColision(){
        if (getBoundsInParent().intersects(Personaje.getPos()) && !propietario){
            this.getAnimation().stop();
            System.out.println("Te ha dado una bala");
            panel.getChildren().remove(Disparo.this);
            Vida.reducirVida();
        }
    }

    public static int getnDisparos() {
        return nDisparos;
    }

    public AnimationTimer getAnimation(){
        return t;
    }
}
