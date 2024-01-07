package lk.ijse.computerShop.controller;
/* 
    @author Sachi_S_Bandara
    @created 11/23/2023 - 4:46 PM 
*/


import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.computerShop.dto.ItemDto;
import lk.ijse.computerShop.model.ItemModel;

import java.io.IOException;
import java.util.ArrayList;

public class LowStockFormController {
    @FXML
    private AnchorPane notificationAp;
    @FXML
    private ScrollPane scrollPane1;
    @FXML
    private VBox vBox1;
    @FXML
    private JFXButton btnLowStock;

    @FXML
    private JFXButton btnPreOrders;

    @FXML
    private Pane paneTogleButton;
    @FXML
    private JFXButton btnClose;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vBox;


    public void initialize() throws IOException {
        loadAllLowItems();
        actions();

        scrollPane1.setVisible(false);
        vBox1.setVisible(false);


    }

    private void actions() {
        btnPreOrders.setOnAction(actionEvent -> {
            btnPreOrders.setStyle("-fx-background-color: #16a085;-fx-border-radius: 20;-fx-background-radius: 20");

            btnLowStock.setStyle("-fx-border-radius: 20;-fx-background-radius: 20");
            paneTogleButton.setStyle("-fx-background-color: rgba(255,255,0,0.3);-fx-border-radius: 20;-fx-background-radius: 20");

            scrollPane1.setVisible(true);
            vBox1.setVisible(true);


        });
        btnLowStock.setOnAction(actionEvent -> {
            btnLowStock.setStyle("-fx-background-color: #16a085;-fx-border-radius: 20;-fx-background-radius: 20");
           btnPreOrders.setStyle("-fx-border-radius: 20;-fx-background-radius: 20");
            paneTogleButton.setStyle("-fx-background-color: rgba(255,255,0,0.3);-fx-border-radius: 20;-fx-background-radius: 20");
            scrollPane1.setVisible(false);
            vBox1.setVisible(false);

        });
    }

    private void loadAllLowItems() throws IOException {
        ArrayList<ItemDto> allItems = new ItemModel().getAllItems();
        for (int i = 0; i <allItems.size(); i++) {
            int qty = allItems.get(i).getQty();
            if (qty<10){

                //create new v box to hold records
                VBox vBox1 = new VBox();
                vBox1.setSpacing(20);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/lowStockRow.fxml"));
                //create row controller
                LowStockRowController lowStockRowController = new LowStockRowController();
                //controller set to fxml
                fxmlLoader.setController(lowStockRowController);

                Node node = fxmlLoader.load();

                Pane row = lowStockRowController.getRow();
                Pane colourPane = lowStockRowController.getColourPane();
                JFXButton btnClear = lowStockRowController.getBtnClear();
                JFXButton btnEdit = lowStockRowController.getBtnEdit();
                Label id = lowStockRowController.getId();
                Label name = lowStockRowController.getName();
                Label qty1 = lowStockRowController.getQty();

                id.setText(allItems.get(i).getItemID());
                name.setText(allItems.get(i).getName());
                qty1.setText(String.valueOf(allItems.get(i).getQty()));

                vBox1.getChildren().add(node);
                vBox.getChildren().add(vBox1);


            }
        }
    }
    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();

    }
}
