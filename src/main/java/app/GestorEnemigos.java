package app;

import javafx.animation.AnimationTimer;

import java.util.ArrayList;

public class GestorEnemigos {
    private static AnimationTimer t;
    private static PanelJuego panel;
    private static long tApar = 2_000_000_000;
    private static long tAnter;
    private static ArrayList<Enemigo> lista;

    /**
     * Comienza la generación de enemigos cada 2 segundos, lo agrega al panel y a la Lista.
     */
    public static void comenzar() {
        lista = new ArrayList<>();
        panel = PanelJuego.getPanel();
        t = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (tApar >= 500_000_000){ tApar -= 10_000;} //Reduce el tiempo de aparición de enemigos hasta medio segundo.
                if (l - tAnter >= tApar) {//Hace que aparezca un enemigo cada 2 segundos, comparando el tiempo actual con el anterior.
                    Enemigo e = obtenerEneAle();
                    lista.add(e);//Almacenamos todos los enemigos en una lista.
                    panel.getChildren().add(e);
                    tAnter = l;
                    if (lista.isEmpty()){//Si no hay ningún enemigo almacenado, generará uno
                        Enemigo a = obtenerEneAle();
                        lista.add(a);
                        panel.getChildren().add(a);
                    }
                }
            }
        };
        t.start();
    }

    /**
     * Mediante números aleatorios obtenemos un enemigo aleatorio en una posición aleatoria de las 4 paredes.
     * @return El enemigo a generar.
     */
    private static Enemigo obtenerEneAle(){
        int ale = (int) (Math.random()*4+1);
        int ale2 = (int) (Math.random()*2+1);
        switch (ale){
            case 1:
                if (ale2 == 1){
                    return new EnemigoComun(Math.random()*1150, -50);
                }
                else {
                    return new EnemigoArquero(Math.random()*1150, -50);
                }
            case 2:
                if (ale2 == 1){
                    return new EnemigoComun(Math.random()*1150, 800);
                }
                else {
                    return new EnemigoArquero(Math.random()*1150, 800);
                }
            case 3:
                if (ale2 == 1){
                    return new EnemigoComun(-50, Math.random()*800);
                }
                else {
                    return new EnemigoArquero(-50, Math.random()*800);
                }
            case 4:
                if (ale2 == 1){
                    return new EnemigoComun(1200, Math.random()*800);
                }
                else {
                    return new EnemigoArquero(1200, Math.random()*800);
                }
        }
        return null;
    }

    public static ArrayList<Enemigo> getLista() {
        return lista;
    }


}
