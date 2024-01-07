package lk.ijse.computerShop.controller;
/* 
    @author Sachi_S_Bandara
    @created 11/18/2023 - 7:08 PM 
*/


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.computerShop.Alert;
import lk.ijse.computerShop.dto.SupplierDto;
import lk.ijse.computerShop.model.SupplierModel;

import java.io.IOException;

public class PopUpAddSupplierFormController {

    @FXML
    private AnchorPane ap;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtName;

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        clear();
    }

    public void clear() {

                txtId.clear();
                txtName.clear();
                txtAddress.clear();
                txtEmail.clear();
                txtMobile.clear();
    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) txtId.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws IOException {
        SupplierDto supplierDto = new SupplierDto(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtEmail.getText(),
                txtMobile.getText()
        );

        boolean isSaved = new SupplierModel().saveSupplier(supplierDto);

        if (isSaved) {
            SupplierFormController.supplierFormController.loadAllSuppliers();
            new Alert().createAlert().show();

            Stage stage = (Stage) txtId.getScene().getWindow();
            stage.close();
        } else {
            System.out.println("something went wrong!!");
        }

    }

}

