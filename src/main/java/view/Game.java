package view;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    public static Controller c;
    public static int speed=5;
    public static int width=20;
    public static int height = 20;
    public static int foodX=0;
    public static int foodY=0;
    public static int cornerSize=25;
    public static List<Body>snake=new ArrayList<>();
    public static Direction direction=Direction.left;
    public static boolean gameover=false;
    public static Random rand = new Random();
    public static Canvas canvas;
    public static GraphicsContext gc;
    public static Button btnReplay;
    public static Animation animation;

    public static Game createAndShow(Controller controller, Stage stage) {
        c=controller;
        controller=c;
        URL location=Game.class.getResource("/view/game.fxml");
        FXMLLoader fxmlLoader=new FXMLLoader(location);
        VBox root = null;

        try {
            root=fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnReplay = new Button();
        btnReplay.setText("Play Again");
        btnReplay.setVisible(false);
        Controller finalController = controller;
        btnReplay.setOnMouseClicked(event -> {
            finalController.goToMenu();
        });

        root.getChildren().add(btnReplay);
        root.setAlignment(Pos.CENTER);

        canvas=new Canvas(width*cornerSize, height*cornerSize);
        gc= canvas.getGraphicsContext2D();
        snake.add(new Body(width/2,height/2));
        snake.add(new Body(width/2,height/2));
        snake.add(new Body(width/2,height/2));




        Drawer.draw(gc);


        root.getChildren().add(canvas);




        Game vue = fxmlLoader.getController();
        stage.setTitle("Venom");
        Scene scene = (new Scene(root, width*cornerSize, height*cornerSize+25));
        scene.addEventFilter(KeyEvent.KEY_PRESSED,key->{
            if(key.getCode()== KeyCode.W){
                direction=Direction.up;
            }

            if(key.getCode()== KeyCode.S){
                direction=Direction.down;
            }

            if(key.getCode()== KeyCode.A){
                direction=Direction.left;
            }

            if(key.getCode()== KeyCode.D){
                direction=Direction.right;
            }
        });
        stage.setScene(scene);

        animation=new Animation();
        animation.start();

        stage.show();
        return vue;
    }

    public static void newFood() {
        boolean collision = true;
        while(collision){
            foodX=rand.nextInt(width);
            foodY=rand.nextInt(height);
            collision=false;
            for(Body c:snake){
                if(c.getX()==foodX && c.getY()==foodY){
                    collision = true;
                }
            }
        }
        speed++;
    }

    public static void displayButton() {
        btnReplay.setVisible(true);
    }

    public static void reinitialize() {
        speed=5;
        foodX=0;
        foodY=0;
       snake=new ArrayList<>();
       direction=Direction.left;
       gameover=false;
    }
}
