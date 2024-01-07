package lk.ijse.computerShop.controller;
/* 
    @author Sachi_S_Bandara
    @created 11/17/2023 - 2:14 AM 
*/

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AlertFormController {
    @FXML
    private Label lblAlert;

    public void setLblAlert(String s){
        lblAlert.setText(s);
    }

}
