package main;

import javafx.stage.Stage;
import view.Game;
import view.Menu;


// attributes
public class Controller {
    private Stage stage;
    private Menu menu;
    private Game game;



    public Controller (Stage primaryStage) {
        this.stage=primaryStage;
        menu=Menu.createAndShow(this,primaryStage);
    }

    public void goToMenu() {
        Game.reinitialize();
        menu=Menu.createAndShow(this,stage);
    }


    public void goToPlay() {

        game=Game.createAndShow(this,stage);
    }
}
