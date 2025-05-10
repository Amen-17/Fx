package app.paneles;
import app.Util;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Escenas extends Pane {

    private Stage stage;
    private Scene panelPri;
    private Scene gameOver;
    private Scene menu;
    private Scene act;
    private static Escenas e;

    private Escenas(){
        stage = Util.getStage();
        panelPri = new Scene(new PanelPrincipal(),1200,800);
        gameOver = new Scene(new GameOver(stage),1200,800);
        menu = new Scene(new Menu(stage),1200,800);
    }

    public void setPanelPri(){
        stage.setScene(panelPri);
        act = panelPri;
    }

    public void setMenu(){
        stage.setScene(menu);
        act = menu;
    }

    public void setGameOver(){
        stage.setScene(gameOver);
        act = gameOver;
        Util.pararJuego();
    }

    public Scene getAct(){
        return act;
    }

    public static Escenas getEscena(){
        if (e == null){
            e = new Escenas();
        }
        return e;
    }


}
