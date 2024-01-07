package lk.ijse.computerShop.controller;
/* 
    @author Sachi_S_Bandara
    @created 11/5/2023 - 1:48 PM 
*/

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.computerShop.dto.CustomerDto;
import lk.ijse.computerShop.dto.EmployeeDto;
import lk.ijse.computerShop.model.EmployeeModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PopUpUpdateEmployeeFormController {



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
    private TextField txtPosition;
    private byte[]  imageBytes;

    private EmployeeFormController employeeFormController;

    public void initialize(){
        employeeFormController=EmployeeFormController.employeeFormController;
    }


    @FXML
    void btnCancelOnAction(ActionEvent event) {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtMobile.clear();
        txtEmail.clear();
        txtPosition.clear();
    }

    @FXML
    void btnImageAddOnAction(ActionEvent event) {
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
    void btnSaveOnAction(ActionEvent event) throws IOException {
        var dto= new EmployeeDto(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtEmail.getText(),
                txtMobile.getText(),
                txtPosition.getText(),
                imageBytes

        );
        boolean isUpdated = new EmployeeModel().updateEmployee(dto);
        if (isUpdated){
            System.out.println("customer updated!!");
            EmployeeFormController.employeeFormController.loadAllEmployees();
            Stage stage = (Stage) txtId.getScene().getWindow();
            stage.close();

        }else {
            System.out.println("something went wrong!!");
        }

    }
    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) txtId.getScene().getWindow();
        stage.close();
    }

}