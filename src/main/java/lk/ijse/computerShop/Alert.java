package lk.ijse.computerShop;
/* 
    @author Sachi_S_Bandara
    @created 11/17/2023 - 12:39 AM 
*/

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.computerShop.controller.AlertFormController;

import java.io.IOException;

public class Alert {


   public Alert(){}
 public  Alert(String s){

 }

    public Stage createAlert() {

        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/alertForm.fxml"));
            AlertFormController alertFormController = new AlertFormController();
            fxmlLoader.setController(alertFormController);
           Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setAlwaysOnTop(true);
            stage.setTitle("");



            // Set the duration after which the stage should close (in seconds)
            double closeAfterSeconds = 0.5;

            // Create a Timeline to close the stage after a certain duration
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(closeAfterSeconds), event -> {
                stage.close();
            }));

            // Play the timeline
            timeline.play();

            return stage;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
