package app.enemigo;

import app.Disparo;
import app.personaje.Personaje;
import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;

public class EnemigoArquero extends Enemigo {

    private static Bounds posEnArc;
    private static long tDisp = 800_000_000;
    private long tAnter; //Al ser diferentes objetos no puede ser static ya que cada uno necesita su variable separada.

    public EnemigoArquero() {
        super(Math.random() * 1150, -50, 50, 50, 1, 2);
        atacar();
        setFill(Color.BLUE);
    }

    /**
     * Constructor de la clase EnemigoArquero que nos permite que aparezca de forma aleatoria por los 4 lados de la pantalla.
     *
     * @param poX Posición aleatoria X
     * @param poY Posición aleatoria Y
     */
    public EnemigoArquero(double poX, double poY) {
        super(poX, poY, 50, 50, 1, 3);
        atacar();
        setFill(Color.BLUE);
        System.out.println("Soy un arquero spawneando en: " + poX + " " + poY);
    }

    /**
     * Comprueba la distancia a la que se encuentra según el personaje y si está cerca dispara, si no se acerca.
     */
    @Override
    protected void atacar() {
        t = new AnimationTimer() {
            @Override
            public void handle(long l) { //La variable 'l' almacena cuantos nanosegundos han pasado.
                posEnArc = EnemigoArquero.this.localToScene(EnemigoArquero.this.getBoundsInLocal()); //Almacena su nueva posición
                EnemigoArquero.super.rotar(); //Hace que el Enemigo esté constantemente rotando según la posición del personaje.
                if (comprobarCerca(getPosc(), Personaje.getPos())) { // Si el personaje se acerca mucho el enemigo huye un poco
                    if (Personaje.getPos().getCenterX() > getPosc().getCenterX()) {
                        setLayoutX(getLayoutX() - 1);
                    } else {
                        setLayoutX(getLayoutX() + 1);
                    }
                    if (Personaje.getPos().getCenterY() > getPosc().getCenterY()) {
                        setLayoutY(getLayoutY() - 1);
                    } else {
                        setLayoutY(getLayoutY() + 1);
                    }
                } else {
                    if (comprobarDistancia(getPosc(), Personaje.getPos())) {
                        if (l - tAnter > tDisp) { //Si los nanosegundos totales - los nanosegundos del último disparo son mayores que la variable tDisp.
                            if (getPosc().getMinX() > 0 || getPosc().getMinY() > 0)
                                disparar();
                            tAnter = l; // Almacena los nanosegundos actuales en el momento del disparo.
                        }
                    } else {
//                        if (!GestorEnemigos.comprobarColisiones(EnemigoArquero.this)){
                            EnemigoArquero.super.atacar();
//                            System.out.println("Si me quiero mover");
//                        }else System.out.println("No me quiero mover");

                    }
                }
                EnemigoArquero.super.comprobarMuerte();
            }
        };
        t.start();
    }

    /**
     * Dispara una bala desde la posición actual del EnemigoArquero, agregandole la rotación actual del mismo.
     */
    private void disparar() {
        //Angulo actual
        double angulo = getRotate();
        double radio = getBoundsInParent().getHeight() / 2; // Distancia desde el centro del objeto a la parte superior
        double anguloRad = Math.toRadians(angulo);
        //Le suma a la posición central la dirección horizontal del angulo multiplicado por la mitad de la altura del objeto.
        //Para resumir, para que el disparo salga por el borde exacto, según la rotación y movimiento, del objeto.
        double disparoX = getBoundsInParent().getCenterX() + Math.cos(anguloRad) * radio;
        //Le suma a la posición central la dirección vertical del angulo multiplicado por la mitad de la altura del objeto.
        //Para resumir, para que el disparo salga por el borde exacto, según la rotación y movimiento, del objeto.
        double disparoY = getBoundsInParent().getCenterY() + Math.sin(anguloRad) * radio;
        Disparo d = new Disparo(disparoX, disparoY, angulo, false);
        panel.getChildren().add(d);
    }

    /**
     * Comprueba si el EnemigoArquero está a una distancia de 180 píxeles del personaje.
     *
     * @param ene Posicion del EnemigoArquero
     * @param pj  Posición del Personaje principal.
     * @return True si está cerca, false si no
     */
    private boolean comprobarDistancia(Bounds ene, Bounds pj) {
        if (Math.abs(ene.getCenterX() - pj.getCenterX()) < 180 && Math.abs(ene.getCenterY() - pj.getCenterY()) < 180) {
            return true;
        }
        return false;
    }

    /**
     * Comprueba si el Personaje se encuentra a una distancia menos de 100 píxeles.
     * @param ene Posición del enemigo.
     * @param pj Posición del personaje.
     * @return True si está cerca, false si no.
     */
    public boolean comprobarCerca(Bounds ene, Bounds pj) {
        if (Math.abs(ene.getCenterX() - pj.getCenterX()) < 100 && Math.abs(ene.getCenterY() - pj.getCenterY()) < 100) {
            return true;
        }
        return false;
    }

    public Bounds getPosc() {
        return posEnArc;
    }


}
