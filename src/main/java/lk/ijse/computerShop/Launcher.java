package lk.ijse.computerShop;
/* 
    @author Sachi_S_Bandara
    @created 11/3/2023 - 6:54 PM 
*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.awt.*;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Launcher.class.getResource("/view/loginPageForm.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setFullScreen(true);
        stage.setScene(scene);
        Image icon= new Image("images/logo.png");
        stage.getIcons().add(icon);
        stage.setTitle("");
        stage.show();
    }
}
