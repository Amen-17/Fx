package app;

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

    public Disparo(double poX, double poY, double angulo){
        super(poX-2,poY-15,2,15);
        setRotate(angulo+90); //No se porque pero hay que añadirle 90º para que salga 'bien'
        panel = PanelJuego.getPanel();
        enPantalla = true;
        setFill(Color.RED);
        subir();
        nDisparos++;
        velocidad = 4;
        dirX = Math.cos(Math.toRadians(angulo)) * velocidad;
        dirY = Math.sin(Math.toRadians(angulo)) * velocidad;
    }
    private void subir(){
        t = new AnimationTimer() {
            @Override
            public void handle(long l) {
                setLayoutY(getLayoutY()+dirY);
                setLayoutX(getLayoutX()+dirX);
                //Si sale por cualquiera de los 4 bordes
                if (getBoundsInParent().getMaxY() <= 0 || getBoundsInParent().getMinY() >= panel.getHeight() ||
                        getBoundsInParent().getMaxX() <= 0 || getBoundsInParent().getMinX() >= panel.getWidth()) {
                    enPantalla = false;
                }

                ArrayList<Enemigo> lista = GestorEnemigos.getLista();

                for (int cont=0; cont < lista.size();cont++){
                    Enemigo e = lista.get(cont);
                    if (getBoundsInParent().intersects(e.getBoundsInParent())){ //Si las colisiones chocan
                        enPantalla = false;
                        e.reducirVida();
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
            }
        };
        t.start();
    }

    public static int getnDisparos() {
        return nDisparos;
    }
}
