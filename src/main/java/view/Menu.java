package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.Controller;

import java.io.IOException;
import java.net.URL;

public class Menu {

    public static Controller controller;
    public static Menu createAndShow(Controller c, Stage primaryStage) {
        controller=c;
        URL location=Menu.class.getResource("/view/menu.fxml");
        FXMLLoader fxmlLoader=new FXMLLoader(location);
        Parent root = null;

        try {
            root=fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Menu vue = fxmlLoader.getController();
        primaryStage.setTitle("Menu");
        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.show();
        return vue;
    }

    public void goToPlay(MouseEvent mouseEvent) {
        controller.goToPlay();
    }
}
