package lk.ijse.computerShop.controller;
/* 
    @author Sachi_S_Bandara
    @created 11/17/2023 - 11:32 AM 
*/
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
public class EmployeeRowFormController {
    @FXML
    private Label address;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private Pane colourPane;

    @FXML
    private Label email;

    @FXML
    private Label id;

    @FXML
    private Label mobile;

    @FXML
    private Label name;

    @FXML
    private Label position;

    @FXML
    private Pane row;

    public void setAddress(String address) {
        this.address.setText(address);

    }

    public void setEmail(String email) {
        this.email.setText(email);

    }

    public void setId(String id) {
        this.id.setText(id);

    }

    public void setMobile(String mobile) {
        this.mobile.setText(mobile);

    }

    public void setName(String name) {
        this.name.setText(name);

    }

    public void setPosition(String position) {
        this.position.setText(position);

    }

    public JFXButton getBtnClear() {
        return btnClear;
    }

    public JFXButton getBtnEdit() {
        return btnEdit;
    }

    public Pane getColourPane() {
        return colourPane;
    }

    public Pane getRow() {
        return row;
    }
}
