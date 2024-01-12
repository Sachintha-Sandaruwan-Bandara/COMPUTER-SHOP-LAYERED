package lk.ijse.computerShop.controller;
/* 
    @author Sachi_S_Bandara
    @created 11/3/2023 - 7:48 PM 
*/

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lk.ijse.computerShop.bo.BOFactory;
import lk.ijse.computerShop.bo.custom.CustomerBo;
import lk.ijse.computerShop.dto.CustomerDto;

import lk.ijse.computerShop.entity.Customer;
import lk.ijse.computerShop.navigation.Navigation;
import lk.ijse.computerShop.navigation.Routes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerFormController {

    @FXML
    private JFXButton btnSearch;
    @FXML
    private VBox vBox;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    public JFXButton btnAddCustomer;



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
    private TextField txtSearchBar;

    @FXML
    private AnchorPane anchorPane;

    public static CustomerFormController customerFormController;
    public static CustomerDto dto;


    public String updateCustomerId;

    CustomerBo customerBo= (CustomerBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    public void initialize() throws IOException, SQLException {
        customerFormController = this;
        loadAllCustomers();
        searchCustomer();


        //hide scroll pane side bars
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);



    }



    private void searchCustomer() {
        txtSearchBar.setOnKeyReleased(keyEvent -> {
            Customer customer = null;
            try {
                customer = customerBo.getCustomer(txtSearchBar.getText());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            lblId.setText(customer.getId());
            lblName.setText(customer.getName());
            lblAddress.setText(customer.getAddress());
            lblEmail.setText(customer.getEmail());
            lblMobile.setText(customer.getMobile());

        });
    }

    public void loadAllCustomers() throws IOException, SQLException {
       //refresh view(clear old records before added new records)
        vBox.getChildren().clear();


        ArrayList<Customer> allCustomers = customerBo.getAllCustomers();

        // get dto details to raws
        for (int i = 0; i < allCustomers.size(); i++) {

            //create new v box to hold records
            VBox vBox1 = new VBox();
            vBox1.setSpacing(20);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/cusromerRowForm.fxml"));
            //create row controller
            CusromerRowFormController cusromerRowFormController = new CusromerRowFormController();
            //controller set to fxml
            fxmlLoader.setController(cusromerRowFormController);

                Node node = fxmlLoader.load();

            //set values to rows
            cusromerRowFormController.setId(allCustomers.get(i).getId());
            cusromerRowFormController.setName(allCustomers.get(i).getName());
            cusromerRowFormController.setAddress(allCustomers.get(i).getAddress());
            cusromerRowFormController.setEmail(allCustomers.get(i).getEmail());
            cusromerRowFormController.setMobile(allCustomers.get(i).getMobile());

            //get row buttons for set action
            JFXButton btnClear = cusromerRowFormController.getBtnClear();
            JFXButton btnEdit = cusromerRowFormController.getBtnEdit();

            //get Rows for set styles
            Pane row = cusromerRowFormController.getRow();
            Pane colourPane = cusromerRowFormController.getColourPane();

            //each row has own customer id for row clicked Actions
            String id=allCustomers.get(i).getId();
            row.setId(id);
            System.out.println(id);
            //set actions to row buttons
            btnClear.setOnAction(actionEvent -> {


                try {
                    customerBo.deleteCustomer(id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                vBox1.getChildren().clear();
                    System.out.println("cleard");




            });

            btnEdit.setOnAction(actionEvent -> {
                try {
                    updateCustomerId=id;
                    Navigation.navigatePopUpWindow(Routes.UPDATECUSTOMER);
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
    void btnAddCustomerOnAction(ActionEvent event) throws IOException {
        Navigation.navigatePopUpWindow(Routes.ADDCUSTOMER);
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) throws SQLException {
        Customer customer = customerBo.getCustomer(txtSearchBar.getText());
        lblId.setText(customer.getId());
        lblName.setText(customer.getName());
        lblAddress.setText(customer.getAddress());
        lblMobile.setText(customer.getMobile());
        lblEmail.setText(customer.getEmail());

    }

    @FXML
    void btnVIewIdImageOnAction(ActionEvent event) throws SQLException {
        Customer customer = customerBo.getCustomer(txtSearchBar.getText());

        Image image = new Image(new ByteArrayInputStream(customer.getImageBytes()));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("ID IMAGE");

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        ImageView imageView = new ImageView();
        imageView.setFitWidth(600);
        imageView.setFitHeight(400);
        imageView.setImage(image);
        root.getChildren().add(imageView);

        primaryStage.show();

    }

    @FXML
    void txtSearchBarOnAction(ActionEvent event) throws SQLException {
        btnSearchOnAction(event);



    }


}
