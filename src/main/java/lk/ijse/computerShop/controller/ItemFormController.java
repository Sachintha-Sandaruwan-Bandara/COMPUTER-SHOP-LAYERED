package lk.ijse.computerShop.controller;
/* 
    @author Sachi_S_Bandara
    @created 11/11/2023 - 8:32 PM 
*/

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lk.ijse.computerShop.dto.ItemDto;
import lk.ijse.computerShop.model.ItemModel;
import lk.ijse.computerShop.navigation.Navigation;
import lk.ijse.computerShop.navigation.Routes;

import java.io.IOException;
import java.util.ArrayList;

public class ItemFormController {

    @FXML
    private TextField txtSearchBar;

    @FXML
    private VBox vbox1;

    @FXML
    private VBox vbox2;

    @FXML
    private VBox vbox3;

    @FXML
    private VBox vbox4;
public static ItemFormController itemFormController;
    public void initialize() throws IOException {
        itemFormController=this;
        loadAllItems();
    }

    public void loadAllItems() throws IOException {
        ArrayList<ItemDto> allItems = new ItemModel().getAllItems();
            vbox1.getChildren().clear();
            vbox2.getChildren().clear();
            vbox3.getChildren().clear();
            vbox4.getChildren().clear();

        for (int i = 0; i < allItems.size(); i++) {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/itemBoxForm.fxml"));

            ItemBoxFormController itemBoxFormController = new ItemBoxFormController();

            fxmlLoader.setController(itemBoxFormController);

            Node node = fxmlLoader.load();

            itemBoxFormController.setLblItemId(allItems.get(i).getItemID());
            itemBoxFormController.setId(allItems.get(i).getItemID());
            itemBoxFormController.setLblName(allItems.get(i).getName());
            itemBoxFormController.setLblPrice(String.valueOf(allItems.get(i).getSellingPrice()));
            itemBoxFormController.setImgView(allItems.get(i).getImageBytes());
            itemBoxFormController.setLblQty(String.valueOf(allItems.get(i).getQty()));

            JFXButton btnItemAction = itemBoxFormController.getBtnItemAction();

            String id = allItems.get(i).getItemID();

            btnItemAction.setOnAction(actionEvent -> {
                System.out.println(id + "btn clicked");
            });


            if (i % 4 == 0) {
                setVBoxAndAction(vbox1, node, itemBoxFormController);

            } else if (i % 4 == 1) {
                setVBoxAndAction(vbox2, node, itemBoxFormController);
            } else if (i % 4 == 2) {
                setVBoxAndAction(vbox3, node, itemBoxFormController);
            } else if (i % 4 == 3) {
                setVBoxAndAction(vbox4, node, itemBoxFormController);
            }


        }

    }

    private void setVBoxAndAction(VBox vbox, Node node, ItemBoxFormController itemBoxFormController) {
        JFXButton btnItemAction = itemBoxFormController.getBtnItemAction();
        btnItemAction.setOnAction(actionEvent -> {
            System.out.println("btn clicked");
            System.out.println(itemBoxFormController.getId());
            boolean isDeleted = new ItemModel().deleteItem(itemBoxFormController.getId());
            if (isDeleted){
                try {
                    loadAllItems();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {
                System.out.println("item  deleted");
            }

        });
        VBox vBox1 = new VBox(node);
        vbox.getChildren().add(vBox1);
        vbox.setSpacing(20);

    }


    @FXML
    void btnAddItemOnAction(ActionEvent event) throws IOException {
        Navigation.navigatePopUpWindow(Routes.ADDITEM);
    }
}