package lk.ijse.computerShop.controller;
/* 
    @author Sachi_S_Bandara
    @created 11/3/2023 - 7:50 PM 
*/
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.computerShop.dto.CustomerDto;
import lk.ijse.computerShop.model.CustomerModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PopUpUpdateCustomerFormController {
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
        private byte[]  imageBytes;

        private CustomerFormController customerFormController;


        public void initialize(){
            this.customerFormController=CustomerFormController.customerFormController;
           loadCustomerDetails(customerFormController.updateCustomerId);

        }

        private void loadCustomerDetails(String  updateCustomerId) {
                CustomerModel customerModel = new CustomerModel();
            CustomerDto customer = customerModel.getCustomer(updateCustomerId);
            txtId.setText(customer.getId());
            txtId.setEditable(false);
            txtName.setText(customer.getName());
            txtAddress.setText(customer.getAddress());
            txtEmail.setText(customer.getEmail());
            txtMobile.setText(String.valueOf(customer.getMobile()));
        }

        @FXML
        void btnCancelOnAction(ActionEvent event) {
                     clear();
        }

        @FXML
        void btnSaveOnAction(ActionEvent event) throws IOException {
           var dto= new CustomerDto(
                    txtId.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    txtEmail.getText(),
                   txtMobile.getText(),
                   imageBytes

            );
            CustomerModel customerModel = new CustomerModel();
            boolean isUpdated = customerModel.updateCustomer(dto);
            if (isUpdated){
                System.out.println("customer Updated Successfully!!!");
                CustomerFormController.customerFormController.loadAllCustomers();
                Stage stage = (Stage) txtName.getScene().getWindow();
                stage.close();
            }else{
                System.out.println("something went wrong!!");
            }
        }

        private void clear(){
            txtName.clear();
            txtAddress.clear();
            txtEmail.clear();
            txtMobile.clear();
        }
    @FXML
    void btnIdImageAddOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG Image","*.jpg"), new FileChooser.ExtensionFilter("PNG Image", "*.png"), new FileChooser.ExtensionFilter("All image files","*.jpg","*.png"));

        Stage stage = (Stage) txtId.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        System.out.println(file.getPath());

// image convert to byte array
        imageBytes = readImageToByteArray(file);

    }

    private byte[] readImageToByteArray(File file) {
        try (FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) txtName.getScene().getWindow();
        stage.close();
    }

}
