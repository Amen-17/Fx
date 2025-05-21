package app.personaje;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Clase que gestiona la puntuación del jugador durante el juego.
 * Muestra los puntos en pantalla, permite aumentarlos y reiniciarlos.
 */

public class Puntuacion extends Text {

    private static int puntuacion = 0;
    private static Puntuacion p;

    /**
     * Constructor privado: impide que otras clases creen objetos de tipo Puntuacion directamente.
     * Solo se puede acceder a través del metodo getPuntuacion().
     * Inicializa el texto con la puntuación actual, fuente y color.
     */
    private Puntuacion(){
       super(String.valueOf(puntuacion));
       setFont(new Font("Roboto",40));//Le cambio la fuente y su tamaño
       setFill(Color.ALICEBLUE);
    }

    /**
     * Incrementa la puntuación del jugador en la cantidad indicada.
     * Luego actualiza el texto en pantalla.
     * @param punto cantidad de puntos a sumar
     */
    public static void subirPuntuacion(int punto){
        puntuacion += punto; //Sumamos los puntos
        p.setText(String.valueOf(puntuacion)); //Actualizamos los puntos que se muestran por pantalla
    }

    /*
     * Cuando necesitemos crear una instancia de este objeto llamaremos este metodo para evitar crear instancias de más.
     * @return Devuelve el único objeto Puntuación que permitimos crear.
     */
    public static Puntuacion getPuntuacion(){ //Si no existe p lo crea y lo devuelve, si no solo lo devuelve
        if (p == null){
            p = new Puntuacion(); // Solo se crea una vez
        }
        return p;
    }

    /*
     * devuelve los puntos a 0
     *  @return número de puntos acumulados
     */
    public static int getPuntos() {
        return puntuacion;
    }



    /*
     * Reinicia la puntuación a cero y actualiza el texto mostrado.
     * Se usa, por ejemplo, al comenzar una nueva partida.
     */
    public static void resetearPuntuacion(){
        puntuacion=0;
        p.setText(String.valueOf(puntuacion));
    }
}
