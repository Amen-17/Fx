package app;

import javafx.animation.AnimationTimer;

import java.util.ArrayList;

public class GestorEnemigos {
    private static AnimationTimer t;
    private static PanelJuego panel;
    private static long tApar = 2_000_000_000;
    private static long tAnter;
    private static ArrayList<Enemigo> lista;


    public static void comenzar(){
        lista = new ArrayList<>();
        panel = PanelJuego.getPanel();
        t = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (tApar >= 500_000_000) tApar -= 10_000;
                if (l - tAnter >= tApar){//Hace que aparezca un enemigo cada 2 segundos, comparando el tiempo actual con el anterior.
                    Enemigo e = new EnemigoComun();
                    lista.add(e);//Almacenamos todos los enemigos en una lista.
                    panel.getChildren().add(e);
                    tAnter = l;
                }
            }
        };
        t.start();
    }

   public static ArrayList<Enemigo> getLista(){
        return lista;
   }



}
