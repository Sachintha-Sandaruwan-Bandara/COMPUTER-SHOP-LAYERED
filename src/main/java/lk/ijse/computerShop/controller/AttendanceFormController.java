package lk.ijse.computerShop.controller;
/* 
    @author Sachi_S_Bandara
    @created 11/11/2023 - 8:33 PM 
*/

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lk.ijse.computerShop.dto.AttendenceDto;
import lk.ijse.computerShop.model.AttendenceModel;
import lk.ijse.computerShop.model.CustomerModel;
import lk.ijse.computerShop.navigation.Navigation;
import lk.ijse.computerShop.navigation.Routes;


import java.io.IOException;
import java.util.ArrayList;

public class AttendanceFormController {
    @FXML
    private VBox vBox;
    public static AttendanceFormController attendanceFormController;
    public void initialize() throws IOException {
        attendanceFormController=this;
        loadAllAttendence();
    }

    public void loadAllAttendence() throws IOException {
        //vBox.getChildren().clear();

        ArrayList<AttendenceDto> allAttendence = new AttendenceModel().getAllAttendence();

        for (int i = 0; i < allAttendence.size(); i++) {

            //create new v box to hold records
            VBox vBox1 = new VBox();
            vBox1.setSpacing(20);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/attendenceRowForm.fxml"));
            //create row controller
            AttendenceRowFormController attendenceRowFormController = new AttendenceRowFormController();
            //controller set to fxml
            fxmlLoader.setController(attendenceRowFormController);


            Node node = fxmlLoader.load();

            attendenceRowFormController.setId(allAttendence.get(i).getAttendenceID());
            attendenceRowFormController.setEmpId(allAttendence.get(i).getEmpID());
            attendenceRowFormController.setInTime(String.valueOf(allAttendence.get(i).getInTime()));
            attendenceRowFormController.setOutTime(String.valueOf(allAttendence.get(i).getOutTime()));
            attendenceRowFormController.setDate(String.valueOf(allAttendence.get(i).getDate()));
            attendenceRowFormController.setOtHours(String.valueOf(allAttendence.get(i).getWorkingHours()));

            //get row buttons for set action
            JFXButton btnClear = attendenceRowFormController.getBtnClear();
            JFXButton btnEdit = attendenceRowFormController.getBtnEdit();

            //get Rows for set styles
            Pane row = attendenceRowFormController.getRow();
            Pane colourPane = attendenceRowFormController.getColourPane();

            String id=allAttendence.get(i).getAttendenceID();
            row.setId(id);
            System.out.println(id);
            //set actions to row buttons
            btnClear.setOnAction(actionEvent -> {
                System.out.println("cleard");

            });

            btnEdit.setOnAction(actionEvent -> {
                System.out.println("edit clicked");
            });



            vBox1.getChildren().clear();
            vBox1.getChildren().add(node);
            vBox.getChildren().add(vBox1);

            colourPane.setOnMouseEntered(mouseEvent -> {
                colourPane.setStyle("-fx-border-color: #16a085;-fx-border-radius: 20;-fx-background-color: rgba(22,160,133,0.18);-fx-background-radius: 20;");
            });
            colourPane.setOnMouseClicked(mouseEvent -> {
                colourPane.setStyle("-fx-border-color: #16a085;-fx-border-radius: 20;-fx-background-color: rgba(22,160,133,0.18);-fx-background-radius: 20;");

            });
            colourPane.setOnMouseExited(mouseEvent -> {
                colourPane.setStyle("");
            });

        }

    }

}
