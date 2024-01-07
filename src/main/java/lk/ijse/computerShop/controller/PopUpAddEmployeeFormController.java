package lk.ijse.computerShop.controller;
/* 
    @author Sachi_S_Bandara
    @created 11/5/2023 - 1:46 PM 
*/

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.computerShop.dto.EmployeeDto;
import lk.ijse.computerShop.model.EmployeeModel;

import java.io.*;

public class PopUpAddEmployeeFormController {

    @FXML
    private JFXButton btnLoadImage;
    @FXML
    private JFXButton btnAddImage;
    @FXML
    private ImageView imgView;
    @FXML
    private JFXButton btnSave;

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
    private EmployeeFormController empCon;

    private byte [] imageBytes;

    public void initialize(){
        empCon=EmployeeFormController.employeeFormController;



    }
    @FXML
    void btnCancelOnAction(ActionEvent event) {
        clear();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws IOException {
        EmployeeDto employeeDto = new EmployeeDto(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtEmail.getText(),
                txtMobile.getText(),
                txtPosition.getText(),
                imageBytes
        );

        EmployeeModel employeeModel = new EmployeeModel();

        boolean isSaved= employeeModel.saveEmployee(employeeDto);

        if (isSaved){
            System.out.println("Employee saved!!");
            clear();
            empCon.loadAllEmployees();
            Stage stage = (Stage) btnAddImage.getScene().getWindow();
            stage.close();

        }else {
            System.out.println("Employee not saved!!");
        }
    }

    private void clear(){
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtMobile.clear();
        txtPosition.clear();
        imgView.setImage(null);
        imgView.setVisible(false);


    }

    @FXML
    void btnAddImageOnAction() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG Image","*.jpg"), new FileChooser.ExtensionFilter("PNG Image", "*.png"), new FileChooser.ExtensionFilter("All image files","*.jpg","*.png"));

        Stage stage = (Stage) txtId.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        System.out.println(file.getPath());

// image convert to byte array
         imageBytes = readImageToByteArray(file);
        Image image = new Image(new ByteArrayInputStream(imageBytes));
        imgView.setImage(image);
        Circle clip = new Circle(100,100,100);
        imgView.setClip(clip);
       // imgView.setPreserveRatio(true);
        imgView.setFitWidth(200); // Set the desired width of the circular image
        imgView.setFitHeight(200);


    }
    private byte[] readImageToByteArray(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            return bos.toByteArray();
        }
    }

//byte array convert to image
    @FXML
    void btnLoadImageOnAction(ActionEvent event) {
        EmployeeModel employeeModel = new EmployeeModel();
        EmployeeDto e001 = employeeModel.getEmployee("e001");
        Image image = new Image(new ByteArrayInputStream(e001.getImageBytes()));
        imgView.setImage(image);
    }
    @FXML
    void imgAddOnMouseClicked(MouseEvent event) throws IOException {
        btnAddImageOnAction();

    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) txtId.getScene().getWindow();
        stage.close();
    }
}