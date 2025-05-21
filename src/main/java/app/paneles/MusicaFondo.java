package app.paneles;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.nio.file.Paths;

public class MusicaFondo {
    private static MediaPlayer mediaPlayer;
    public static void reproducir() {
        try {
            // Carga el archivo de música desde recursos (debe estar en src/main/resources)
            Media media = new Media(MusicaFondo.class.getResource("/butterflies-in-me-belly-177077.mp3").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);  // Repetir indefinidamente
            mediaPlayer.setVolume(0.5); // Volumen al 50%
            mediaPlayer.play();
        } catch (Exception e) {
            System.err.println("ERROR AL REPRODUCIR MÚSICA:");
            e.printStackTrace(); // Aquí veremos si falla por la ruta o el archivo
        }
    }

    public static void detener() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

}
