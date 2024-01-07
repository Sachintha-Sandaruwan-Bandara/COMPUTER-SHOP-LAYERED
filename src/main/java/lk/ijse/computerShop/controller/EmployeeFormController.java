package lk.ijse.computerShop.controller;
/* 
    @author Sachi_S_Bandara
    @created 11/5/2023 - 1:32 PM 
*/

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import lk.ijse.computerShop.dto.EmployeeDto;
import lk.ijse.computerShop.model.EmployeeModel;
import lk.ijse.computerShop.navigation.Navigation;
import lk.ijse.computerShop.navigation.Routes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeFormController {


    @FXML
    private Text address;
    @FXML
    private Text email;

    @FXML
    private Text id;

    @FXML
    private ImageView imgView;

    @FXML
    private Text mobile;

    @FXML
    private Text name;

    @FXML
    private TextField searchId;

    @FXML
    public JFXButton btnAddEmployee;


    @FXML
    private VBox vBox;
    @FXML


    public static EmployeeFormController employeeFormController;

    public  String updateEmployeeId;

    public void initialize() throws IOException {
        employeeFormController=this;
        loadAllEmployees();


    }




    public void loadAllEmployees() throws IOException {
        //refresh view(clear old records before added new records)
        vBox.getChildren().clear();


        ArrayList<EmployeeDto> allEmployees = new EmployeeModel().getAllEmployees();
        // get dto details to raws
        for (int i = 0; i < allEmployees.size(); i++) {

            //create new v box to hold records
            VBox vBox1 = new VBox();
            vBox1.setSpacing(20);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/employeeRowForm.fxml"));
            //create row controller
            EmployeeRowFormController employeeRowFormController = new EmployeeRowFormController();
            //controller set to fxml
            fxmlLoader.setController(employeeRowFormController);

            Node node = fxmlLoader.load();

            //set values to rows
            employeeRowFormController.setId(allEmployees.get(i).getId());
            employeeRowFormController.setName(allEmployees.get(i).getName());
            employeeRowFormController.setAddress(allEmployees.get(i).getAddress());
            employeeRowFormController.setEmail(allEmployees.get(i).getEmail());
            employeeRowFormController.setMobile(allEmployees.get(i).getMobile());
            employeeRowFormController.setPosition(allEmployees.get(i).getPosition());

            //get row buttons for set action
            JFXButton btnClear = employeeRowFormController.getBtnClear();
            JFXButton btnEdit = employeeRowFormController.getBtnEdit();

            //get Rows for set styles
            Pane row = employeeRowFormController.getRow();
            Pane colourPane = employeeRowFormController.getColourPane();

            //each row has own customer id for row clicked Actions
            String id=allEmployees.get(i).getId();
            row.setId(id);
            System.out.println(id);
            //set actions to row buttons
            btnClear.setOnAction(actionEvent -> {
                System.out.println("cleard");

                new EmployeeModel().deleteEmployee(id);
                vBox1.getChildren().clear();
            });

            btnEdit.setOnAction(actionEvent -> {
                try {
                    updateEmployeeId=id;
                    System.out.println(id);
                    Navigation.navigatePopUpWindow(Routes.UPDATEEMPLOYEE);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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

    @FXML
    void btnAddEmployeeOnAction(ActionEvent event) throws IOException {
        Navigation.navigatePopUpWindow(Routes.ADDEMPLOYEE);
    }
    @FXML
    void btnSearchOnAction(ActionEvent event) {
        clear();
        EmployeeModel employeeModel = new EmployeeModel();
        EmployeeDto employee = employeeModel.getEmployee(searchId.getText());

        id.setText(employee.getId());
        name.setText(employee.getName());
        address.setText(employee.getAddress());
        email.setText(employee.getEmail());
        mobile.setText(String.valueOf(employee.getMobile()));

        Image image = new Image(new ByteArrayInputStream(employee.getImageBytes()));
        imgView.setImage(image);
        imgView.fitHeightProperty();
        imgView.fitWidthProperty();
        Circle clip = new Circle(100,100,100);
        imgView.setClip(clip);
        imgView.setFitWidth(200); // Set the desired width of the circular image
        imgView.setFitHeight(200);

    }
    private void clear() {
        id.setText(null);
        name.setText(null);
        address.setText(null);
        email.setText(null);
        mobile.setText(null);
        imgView.setImage(null);
    }

    @FXML
    void searchIdOnAction(ActionEvent event) {
        btnSearchOnAction(event);
    }
}