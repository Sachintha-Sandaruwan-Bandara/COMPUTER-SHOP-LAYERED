package lk.ijse.computerShop.controller;
/* 
    @author Sachi_S_Bandara
    @created 11/19/2023 - 3:16 PM 
*/

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;

public class ItemBoxFormController {

    @FXML
    private JFXButton btnItemAction;

    @FXML
    private ImageView imgView;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblItemId;

    @FXML
    private Label lblName;

    @FXML
    private Label lblQty;

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImgView(byte[] imageBytes) {
        Image image = new Image(new ByteArrayInputStream(imageBytes));
        this.imgView.setImage(image);
        imgView.setPreserveRatio(false);
    }

    public void setLblPrice(String lablePrice) {
        this.lblPrice.setText(lablePrice);
    }

    public void setLblItemId(String lblItemId) {
        this.lblItemId.setText(lblItemId);
    }

    public void setLblName(String lblName) {
        this.lblName.setText(lblName);
    }

    public void setLblQty(String lblQty) {
        this.lblQty .setText(lblQty);
    }

    public JFXButton getBtnItemAction() {
        return btnItemAction;
    }
}

