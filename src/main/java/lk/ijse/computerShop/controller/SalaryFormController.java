package lk.ijse.computerShop.controller;
/* 
    @author Sachi_S_Bandara
    @created 11/11/2023 - 8:36 PM 
*/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.computerShop.dto.SalaryDto;
import lk.ijse.computerShop.model.SalaryModel;

import java.sql.Date;
import java.time.LocalDate;

public class SalaryFormController {

    @FXML
    private Label lblDate;

    @FXML
    private Label lblOtHours;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblWorkingHours;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtMoneyPerHOur;

    @FXML
    private TextField txtMoneyPerOtHour;

    int perHourMoney = 0;
    int otHourMoney = 0;
    int total = 0;

    public void initialize() {
        onActions();
        lblDate.setText(String.valueOf(LocalDate.now()));

    }

    private void onActions() {


        txtEmployeeId.setOnAction(actionEvent -> {
            String empWorkingHours = new SalaryModel().getEmpWorkingHours(txtEmployeeId.getText());

            int wHours = Integer.parseInt(empWorkingHours);
            if (wHours > 20) {
                int otHours = (wHours - 20);
                lblOtHours.setText(String.valueOf(otHours));
                int absoluteWhours = (wHours - otHours);
                lblWorkingHours.setText(String.valueOf(absoluteWhours));
            }

        });

        txtMoneyPerHOur.setOnAction(keyEvent -> {


            int wHours = Integer.parseInt(lblWorkingHours.getText());
            int moneyPerHour = Integer.parseInt(txtMoneyPerHOur.getText());

            perHourMoney = (wHours * moneyPerHour);

            setTotal();

        });
        txtMoneyPerOtHour.setOnAction(keyEvent -> {


            int otHours = Integer.parseInt(lblOtHours.getText());
            int moneyPerOtHour = Integer.parseInt(txtMoneyPerOtHour.getText());

            otHourMoney = (otHours * moneyPerOtHour);

            setTotal();


        });

    }

    private void setTotal() {

        total = (perHourMoney + otHourMoney);
        lblTotal.setText(String.valueOf(total));
        System.out.println("TOTAL" + total);
    }

    @FXML
    void btnPayOnAction(ActionEvent event) {
        String id = new SalaryModel().generateSalaryId();
        SalaryDto salaryDto = new SalaryDto(
                id,
                Integer.parseInt(lblTotal.getText()),
                Date.valueOf(lblDate.getText()),
                txtEmployeeId.getText()
        );

        boolean isSaved = new SalaryModel().saveSalaryPayment(salaryDto);
        if (isSaved) {
            System.out.println("salary saved!!");
            clear();
        } else {
            System.out.println("salary not saved !!!");
        }

    }

    private void clear() {
        txtEmployeeId.clear();
        txtMoneyPerHOur.clear();
        txtMoneyPerOtHour.clear();
        lblTotal.setText("");
        lblWorkingHours.setText("");
        lblOtHours.setText("");
    }
}
