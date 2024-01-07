package lk.ijse.computerShop.controller;
/* 
    @author Sachi_S_Bandara
    @created 11/3/2023 - 7:40 PM 
*/

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXToggleButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lk.ijse.computerShop.dto.AttendenceDto;
import lk.ijse.computerShop.dto.ItemDto;
import lk.ijse.computerShop.model.AttendenceModel;
import lk.ijse.computerShop.model.ItemModel;
import lk.ijse.computerShop.navigation.Navigation;
import lk.ijse.computerShop.navigation.Routes;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DashboardFormController {

    @FXML
    private ImageView notifyIcon;
    @FXML
    private JFXButton btnNotification;
    @FXML
    private TextField txtEmpID;
    @FXML
    private Label attendenceTime;
    @FXML
    private DatePicker datePicker;
    @FXML
    private JFXButton btnAssign;

    @FXML
    private JFXToggleButton darkMode;
    @FXML
    private Pane detail1;

    @FXML
    private Pane detail2;
    @FXML
    private Pane bgclrPane;
    @FXML
    private Pane detail3;

    @FXML
    private Pane detail4;

    @FXML
    private Pane detail5;
    @FXML
    private JFXComboBox<String> orderComb;
    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblTimeMini;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private AnchorPane subAnchorPane;

    public void initialize() {
        attendence();
        generateRealTime();
        ObservableList<String> options = FXCollections.observableArrayList(
                "SELLING ORDER",
                "BUYING ORDER"
        );
        orderComb.setItems(options);
        Image image = new Image("/icons/icons8-notification-50.png");
        notifyIcon.setImage(image);
        notification();

    }



    private void notification() {
        btnNotification.setOnAction(actionEvent -> {
            try {
                Navigation.navigatePopUpWindow(Routes.LOWSTOCK);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        ArrayList<ItemDto> allItems = new ItemModel().getAllItems();
        for (int i = 0; i <allItems.size(); i++) {
            int qty = allItems.get(i).getQty();
            if (qty<10){

                Image image = new Image("/icons/icons8-notification.gif");
                notifyIcon.setImage(image);
            }
        }
    }

    private void attendence() {



txtEmpID.setOnAction(Event -> {
    boolean b = new AttendenceModel().checkIsAdmited(txtEmpID.getText(), Date.valueOf(datePicker.getValue()));

    if (b){

        btnAssign.setText("sign out");
        btnAssign.setVisible(true);
                btnAssign.setOnAction(actionEvent1 -> {
                    boolean isUpdated= new AttendenceModel().updateOutTime(txtEmpID.getText(), Date.valueOf(datePicker.getValue()), Time.valueOf(attendenceTime.getText()));

                    if (isUpdated){
                        System.out.println("updated out time");
                        btnAssign.setVisible(false);
                        new AttendenceModel().setWorkingHours(txtEmpID.getText(), Date.valueOf(datePicker.getValue()));
                        try {
                            AttendanceFormController.attendanceFormController.loadAllAttendence();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        txtEmpID.clear();
                    }else {
                        System.out.println("not updated out time");



                    }
                });

    }else {
        btnAssign.setText("admit");
        btnAssign.setVisible(true);
        btnAssign.setOnAction(actionEvent -> {
            String id = new AttendenceModel().genarateId();
            AttendenceDto attendenceDto = new AttendenceDto(
                    id,
                    Date.valueOf(datePicker.getValue()),
                     Time.valueOf(attendenceTime.getText()),
                    Time.valueOf("00:00:00"),
                    0,
                    txtEmpID.getText()

            );

            boolean isSaved= new AttendenceModel().saveAttendence(attendenceDto);

            if (isSaved){
                System.out.println("attendance saved!!");
                btnAssign.setVisible(false);

                try {
                    AttendanceFormController.attendanceFormController.loadAllAttendence();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }else {
                System.out.println("attendance not saved!!");
            }

        });
    }


});

    }

    @FXML
    void btnAttendenceOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.ATTENDANCE, subAnchorPane);
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.CUSTOMER, subAnchorPane);
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.DASHBOARD, mainAnchorPane);
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.EMPLOYEE, subAnchorPane);
    }

    @FXML
    void btnItemOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.ITEM, subAnchorPane);
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.LOGIN, mainAnchorPane);
    }

    @FXML
    void combOrderOnAction(ActionEvent event) throws IOException {
        if (orderComb.getValue().equals("BUYING ORDER")) {
            Navigation.navigate(Routes.BUYINGORDER, subAnchorPane);

        } else if (orderComb.getValue().equals("SELLING ORDER")) {
            Navigation.navigate(Routes.SELLINGORDER, subAnchorPane);

        }
    }

    @FXML
    void btnSalaryOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.SALARY, subAnchorPane);
    }

    @FXML
    void btnServiceOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.SERVICE, subAnchorPane);
    }

    @FXML
    void btnSettingOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.SETTING, subAnchorPane);
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.SUPPLIER, subAnchorPane);
    }

    /*-----DATE AND TIME GENERATE------*/
    private void generateRealTime() {
        datePicker.setValue(LocalDate.now());
        lblDate.setText(LocalDate.now().toString());
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
            DateTimeFormatter formatter1=DateTimeFormatter.ofPattern("hh:mm:ss");
            lblTimeMini.setText(LocalDateTime.now().format(formatter));
            lblTime.setText(LocalDateTime.now().format(formatter));
            attendenceTime.setText(LocalDateTime.now().format(formatter1));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        detail1.setOnMouseEntered(event -> {
            bgclrPane.setStyle("-fx-background-color: rgba(250,177,160,0.25);");


        });

        // Add mouse exited event handler
        detail1.setOnMouseExited(event -> {
            //       subAnchorPane.setStyle("-fx-background-color: #ffffff;");
        });
        detail2.setOnMouseEntered(event -> {
            bgclrPane.setStyle("-fx-background-color: rgba(85,239,196,0.26);");
        });

        // Add mouse exited event handler
        detail2.setOnMouseExited(event -> {
            //      subAnchorPane.setStyle("-fx-background-color: #ffffff;");
        });
        detail3.setOnMouseEntered(event -> {
            bgclrPane.setStyle("-fx-background-color: rgba(129,236,236,0.25);");
        });

        // Add mouse exited event handler
        detail3.setOnMouseExited(event -> {
            //     subAnchorPane.setStyle("-fx-background-color: #ffffff;");
        });
        detail4.setOnMouseEntered(event -> {
            bgclrPane.setStyle("-fx-background-color: rgba(116,185,255,0.26);");
        });

        // Add mouse exited event handler
        detail4.setOnMouseExited(event -> {
            //      subAnchorPane.setStyle("-fx-background-color: #ffffff;");
        });
        detail5.setOnMouseEntered(event -> {
            bgclrPane.setStyle("-fx-background-color: rgba(162,155,254,0.22);");
        });

        // Add mouse exited event handler
        detail5.setOnMouseExited(event -> {
            //     subAnchorPane.setStyle("-fx-background-color: #ffffff;");
        });
        darkMode.setOnAction(event -> {
            if (darkMode.isSelected()) {
                System.out.println("Toggle button is ON");
                subAnchorPane.setStyle("-fx-background-color: rgb(44,62,80);");
                lblTimeMini.setStyle("-fx-text-fill:white;");

            } else {
                System.out.println("Toggle button is OFF");
                subAnchorPane.setStyle("-fx-background-color: rgb(255,255,255);");
                lblTimeMini.setStyle("-fx-text-fill:black;");
            }
        });
    }
    @FXML
    void btnDashBoardPlaceOrderOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.SELLINGORDER, subAnchorPane);
    }
    @FXML
    void btnCloseOnAction(ActionEvent event) {
            System.exit(0);
    }
}

