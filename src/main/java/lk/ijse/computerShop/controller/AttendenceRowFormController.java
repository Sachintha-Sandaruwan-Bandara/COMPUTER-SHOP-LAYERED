package lk.ijse.computerShop.controller;
/* 
    @author Sachi_S_Bandara
    @created 11/20/2023 - 11:13 AM 
*/
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
public class AttendenceRowFormController {
    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private Pane colourPane;

    @FXML
    private Label date;

    @FXML
    private Label empId;

    @FXML
    private Label id;

    @FXML
    private Label inTime;

    @FXML
    private Label otHours;

    @FXML
    private Label outTime;

    @FXML
    private Pane row;

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

    public void setDate(String date) {
        this.date.setText(date);
    }

    public void setEmpId(String empId) {
        this.empId.setText(empId);
    }

    public void setId(String id) {
        this.id.setText(id);
    }

    public void setInTime(String inTime) {
        this.inTime.setText(inTime);
    }

    public void setOtHours(String otHours) {
        this.otHours.setText(otHours);
    }

    public void setOutTime(String outTime) {
        this.outTime.setText(outTime);
    }
}
