package app.enemigo;

import app.paneles.PanelJuego;
import javafx.animation.AnimationTimer;
import java.util.ArrayList;

public class GestorEnemigos {
    private static AnimationTimer t;  // Temporizador que controla la generación de enemigos en tiempo real
    private static PanelJuego panel; // Referencia al panel donde se dibujan los enemigos
    private static long tApar = 2_000_000_000;
    private static long tAnter;
    private static ArrayList<Enemigo> lista;

    /**
     * Comienza la generación de enemigos cada 2 segundos, lo agrega al panel y a la Lista.
     */
    public static void comenzar() {
        lista = new ArrayList<>();
        panel = PanelJuego.getPanelJuego();
        t = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (tApar >= 500_000_000){ tApar -= 10_000;} //Reduce el tiempo de aparición de enemigos hasta medio segundo.
                if (l - tAnter >= tApar) {//Hace que aparezca un enemigo cada 2 segundos, comparando el tiempo actual con el anterior.
                    Enemigo e = obtenerEneAle(); // Genera un enemigo aleatorio
                    lista.add(e);//Almacenamos todos los enemigos en una lista.
                    panel.getChildren().add(e);
                    tAnter = l; // Actualiza el tiempo de última aparición
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
        int ale = (int) (Math.random()*4+1);  // Selecciona un lado (1: arriba, 2: abajo, 3: izquierda, 4: derecha)
        int ale2 = (int) (Math.random()*2+1);  // Selecciona el tipo de enemigo (1: común, 2: arquero)
        switch (ale){
            case 1: // Parte superior
                if (ale2 == 1){
                    return new EnemigoComun(Math.random()*1150, -50);
                }
                else {
                    return new EnemigoArquero(Math.random()*1150, -50);
                }
            case 2: // Parte inferior
                if (ale2 == 1){
                    return new EnemigoComun(Math.random()*1150, 800);
                }
                else {
                    return new EnemigoArquero(Math.random()*1150, 800);
                }
            case 3:   // Lado izquierdo
                if (ale2 == 1){
                    return new EnemigoComun(-50, Math.random()*800);
                }
                else {
                    return new EnemigoArquero(-50, Math.random()*800);
                }
            case 4: // Lado derecho
                if (ale2 == 1){
                    return new EnemigoComun(1200, Math.random()*800);
                }
                else {
                    return new EnemigoArquero(1200, Math.random()*800);
                }
        }
        return null; // Por defecto (aunque no debería pasar)
    }

    /**
     * Devuelve la lista actual de enemigos activos.
     * @return lista de enemigos.
     */
    public static ArrayList<Enemigo> getLista() {
        return lista;
    }

    /**
     * Detiene la generación de enemigos.
     */
    public static void detener(){
        matarEnemigos(); // Elimina todos los enemigos de la lista y de la pantalla
        t.stop();
    }

    /**
     * Metodo que recorre la lista de enemigos y los "mata".
     * Se usa un bucle for con índice en lugar de for-each para evitar ConcurrentModificationException.
     */
    private static void matarEnemigos(){
        //Si esto fuese un for each daría una excepción, no entiendo nada :O
        for (int cont = 0 ; cont <lista.size();cont++){
            lista.get(cont).muerte();  // Llama al metodo muerte de cada enemigo
        }
    }
}
