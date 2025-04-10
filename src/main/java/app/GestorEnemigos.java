package app;

import javafx.animation.AnimationTimer;

import java.util.ArrayList;

public class GestorEnemigos {
    private static AnimationTimer t;
    private static PanelJuego panel;
    private static long tApar = 2_000_000_000;
    private static long tAnter;
    private static ArrayList<Enemigo> lista;

    public static void comenzar() {
        lista = new ArrayList<>();
        panel = PanelJuego.getPanel();
        t = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (tApar >= 500_000_000){ tApar -= 10_000;}
                if (l - tAnter >= tApar) {//Hace que aparezca un enemigo cada 2 segundos, comparando el tiempo actual con el anterior.
                    Enemigo e = obtenerEneAle();
                    //Hay que cambiar la frecuencia según la dificultad
                    if (l % 2 == 0){
                        Enemigo a = new EnemigoArquero();
                        lista.add(a);//Almacenamos todos los enemigos en una lista.
                        panel.getChildren().add(a);
                    }
                    lista.add(e);//Almacenamos todos los enemigos en una lista.
                    panel.getChildren().add(e);
                    tAnter = l;
                }
            }
        };
        t.start();
    }
    //Esto es una prueba, no creo que continue
    public static void comenzar(int a){
        lista = new ArrayList<>();
        panel = PanelJuego.getPanel();
        Enemigo e = new EnemigoArquero();
        lista.add(e);//Almacenamos todos los enemigos en una lista.
        panel.getChildren().add(e);
    }

    private static Enemigo obtenerEneAle(){
        int ale = (int) (Math.random()*4+1);
        switch (ale){
            case 1:
                return new EnemigoComun(Math.random()*1150, -50);
            case 2:
                return new EnemigoComun(Math.random()*1150, 800);
            case 3:
                return new EnemigoComun(-50, Math.random()*800);
            case 4:
                return new EnemigoComun(1200, Math.random()*800);
        }
        return null;
    }

    public static ArrayList<Enemigo> getLista() {
        return lista;
    }


}
