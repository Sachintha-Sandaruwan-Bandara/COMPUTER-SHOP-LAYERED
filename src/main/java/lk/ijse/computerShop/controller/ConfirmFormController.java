package lk.ijse.computerShop.controller;
/* 
    @author Sachi_S_Bandara
    @created 11/28/2023 - 12:49 PM 
*/


import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;


public class ConfirmFormController {
    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnDelete;


   public ConfirmFormController() {


   }
    public JFXButton getBtnCancel() {
        return btnCancel;
    }

    public JFXButton getBtnDelete() {
        return btnDelete;
    }
}
