package app;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Puntuacion extends Text {

    private static int puntuacion = 0;
    private static Puntuacion p;

    private Puntuacion(){
       super(String.valueOf(puntuacion));
       setFont(new Font("Roboto",40));//Le cambio la fuente y su tamaño
       setFill(Color.ALICEBLUE);
    }

    public static void subirPuntuacion(int punto){
        puntuacion += punto; //Sumamos los puntos
        p.setText(String.valueOf(puntuacion)); //Actualizamos los puntos que se muestran por pantalla
    }

    public static Puntuacion getPuntuacion(){ //Si no existe p lo crea y lo devuelve, si no solo lo devuelve
        if (p == null){
            p = new Puntuacion();
        }
        return p;
    }

}
