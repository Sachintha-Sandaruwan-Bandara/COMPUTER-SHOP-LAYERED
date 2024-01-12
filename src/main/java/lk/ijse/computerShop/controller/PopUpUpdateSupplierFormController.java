package lk.ijse.computerShop.controller;
/* 
    @author Sachi_S_Bandara
    @created 11/18/2023 - 9:01 PM 
*/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.computerShop.dto.SupplierDto;
import lk.ijse.computerShop.model.SupplierModel;

import java.io.IOException;

public class PopUpUpdateSupplierFormController {

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

    private String updateSupplierId;

    public void initialize(){
        updateSupplierId = SupplierFormController.supplierFormController.updateSupplierId;
        System.out.println(updateSupplierId);
        loadSupplier(updateSupplierId);
    }

    private void loadSupplier(String updateSupplierId) {
        SupplierDto getsupplier = new SupplierModel().getsupplier(updateSupplierId);
        txtId.setText(getsupplier.getId());
        txtId.setEditable(false);
        txtName.setText(getsupplier.getName());
        txtAddress.setText(getsupplier.getAddress());
        txtEmail.setText(getsupplier.getEmail());
        txtMobile.setText(getsupplier.getMobile());

    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws IOException {
        var dto= new SupplierDto(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtEmail.getText(),
                txtMobile.getText()


        );
       boolean isUpdated=new SupplierModel().updateSupplier(dto);

        if (isUpdated){
            System.out.println("customer Updated Successfully!!!");
           SupplierFormController.supplierFormController.loadAllSuppliers();
            Stage stage = (Stage) txtName.getScene().getWindow();
            stage.close();
        }else{
            System.out.println("something went wrong!!");
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        clear();
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) txtName.getScene().getWindow();
        stage.close();
    }
public void clear(){
    txtName.clear();
    txtAddress.clear();
    txtEmail.clear();
    txtMobile.clear();
}

}

