package lk.ijse.computerShop.controller;
/* 
    @author Sachi_S_Bandara
    @created 11/11/2023 - 8:28 PM 
*/

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXScrollPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lk.ijse.computerShop.dto.CustomerDto;
import lk.ijse.computerShop.dto.EmployeeDto;
import lk.ijse.computerShop.dto.SupplierDto;
import lk.ijse.computerShop.model.CustomerModel;
import lk.ijse.computerShop.model.EmployeeModel;
import lk.ijse.computerShop.model.SupplierModel;
import lk.ijse.computerShop.navigation.Navigation;
import lk.ijse.computerShop.navigation.Routes;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.util.ArrayList;

public class SupplierFormController {
    @FXML
    private Label lblAddress;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblId;

    @FXML
    private Label lblMobile;

    @FXML
    private Label lblName;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    public VBox vBox;

    @FXML
    private TextField searchTxt;
    public String updateSupplierId;

    public static SupplierFormController supplierFormController;
    ArrayList<Object> rows = new ArrayList<>();
    public void initialize() throws IOException {
        supplierFormController=this;
        loadAllSuppliers();
         scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

    }

    public void loadAllSuppliers() throws IOException {

        //refresh view(clear old records before added new records)
        vBox.getChildren().clear();


        ArrayList<SupplierDto> allSuppliers = new SupplierModel().getAllSuppliers();

        // get dto details to raws
        for (int i = 0; i < allSuppliers.size(); i++) {

            //create new v box to hold records
            VBox vBox1 = new VBox();
            vBox1.setSpacing(20);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/supplierRowForm.fxml"));
            //create row controller
            SupplierRowFormController supplierRowFormController = new SupplierRowFormController();
            //controller set to fxml
            fxmlLoader.setController(supplierRowFormController);

            Node node = fxmlLoader.load();

            //set values to rows
            supplierRowFormController.setId(allSuppliers.get(i).getId());
            supplierRowFormController.setName(allSuppliers.get(i).getName());
            supplierRowFormController.setAddress(allSuppliers.get(i).getAddress());
            supplierRowFormController.setEmail(allSuppliers.get(i).getEmail());
            supplierRowFormController.setMobile(allSuppliers.get(i).getMobile());

            //get row buttons for set action
            JFXButton btnClear = supplierRowFormController.getBtnClear();
            JFXButton btnEdit = supplierRowFormController.getBtnEdit();

            //get Rows for set styles
            Pane row = supplierRowFormController.getRow();
            Pane colourPane = supplierRowFormController.getColourPane();

            //each row has own customer id for row clicked Actions
            String id=allSuppliers.get(i).getId();
            row.setId(id);
            System.out.println(row.getId());

            //set actions to row buttons
            btnClear.setOnAction(actionEvent -> {
                System.out.println("cleard");

                boolean isDeleted= new SupplierModel().deleteSupplier(id);
                vBox1.getChildren().clear();
                if (isDeleted){
                    System.out.println("supplier deleted successfully!!");
                }else {
                    System.out.println("something went wrong!!");
                }
            });

            btnEdit.setOnAction(actionEvent -> {

                    updateSupplierId=id;


                System.out.println("edit clicked");
                try {
                    Navigation.navigatePopUpWindow(Routes.UPDATESUPPLIER);
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
    void addSupplierOnAction(ActionEvent event) throws IOException {
        Navigation.navigatePopUpWindow(Routes.ADDSUPPLIER);


    }
public static void action(ActionEvent event){
    System.out.println("clear");
}
    @FXML
    void searchOnAction(ActionEvent event) {



    }
}

