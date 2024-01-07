package lk.ijse.computerShop.controller;
/* 
    @author Sachi_S_Bandara
    @created 11/13/2023 - 10:50 PM 
*/


import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.computerShop.dto.CustomerDto;
import lk.ijse.computerShop.dto.ItemDto;
import lk.ijse.computerShop.dto.SellingOrderDto;
import lk.ijse.computerShop.dto.tm.SellingOrderTm;
import lk.ijse.computerShop.model.CustomerModel;
import lk.ijse.computerShop.model.ItemModel;
import lk.ijse.computerShop.model.SellingOrderModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SellingOrderFormController {
    @FXML
    private TableView<SellingOrderTm> tblOrderCart;
    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;
    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private JFXComboBox<String> cmbItemCode;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblNetTotal;
    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private TextField txtQty;
    private final ObservableList<SellingOrderTm> obList = FXCollections.observableArrayList();
    public void initialize(){
        loadAllCustomerId();
        loadItemCodes();
        setCellValueFactory();
        setDate();
        generateNextOrderId();
    }

    private void loadAllCustomerId() {
        CustomerModel customerModel = new CustomerModel();
        ObservableList<String> obList = FXCollections.observableArrayList();
        ArrayList<CustomerDto> allCustomers = customerModel.getAllCustomers();

        for (CustomerDto dto : allCustomers) {
            obList.add(dto.getId());
        }
        cmbCustomerId.setItems(obList);
    }
    private void loadItemCodes() {
        ItemModel itemModel = new ItemModel();
        ObservableList<String> obList = FXCollections.observableArrayList();

            List<ItemDto> itemList = itemModel.getAllItems();

            for (ItemDto itemDto : itemList) {
                obList.add(itemDto.getItemID());
            }

            cmbItemCode.setItems(obList);

    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) {
        CustomerDto customer = new CustomerModel().getCustomer(cmbCustomerId.getValue());
       lblCustomerName.setText(customer.getName());
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) {
        ArrayList<ItemDto> allItems = new ItemModel().getAllItems();
        for (int i = 0; i < allItems.size(); i++) {
           if (allItems.get(i).getItemID().equals(cmbItemCode.getValue())){
               lblDescription.setText(allItems.get(i).getName());
               lblUnitPrice.setText(String.valueOf(allItems.get(i).getSellingPrice()));
               lblQtyOnHand.setText(String.valueOf(allItems.get(i).getQty()));
           }

        }

    }
    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("tot"));
        //colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }
    private void setDate() {
        String date = String.valueOf(LocalDate.now());
        lblOrderDate.setText(date);
    }
    @FXML
    void txtQtyOnAction(ActionEvent event) {

    }
    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String code = cmbItemCode.getValue();
        String description = lblDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double total = qty * unitPrice;

        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            if (code.equals(colItemCode.getCellData(i))) {
                qty += (int) colQty.getCellData(i);
                total = qty * unitPrice;

                obList.get(i).setQty(qty);
                obList.get(i).setTot(total);
                tblOrderCart.refresh();
                calculateNetTotal();
                txtQty.clear();
                return;
            }
        }

        obList.add(new SellingOrderTm(
                code,
                description,
                qty,
                unitPrice,
                total
        ));
        tblOrderCart.setItems(obList);
        calculateNetTotal();
        txtQty.clear();
    }
    private void calculateNetTotal() {
        double total = 0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            total += (double) colTotal.getCellData(i);
        }
        System.out.println(total);
        lblNetTotal.setText(String.valueOf(total));
    }
    private void generateNextOrderId() {
        SellingOrderModel orderModel = new SellingOrderModel();
        try {
            String orderId = orderModel.generateNextOrderId();
            lblOrderId.setText(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException {
        String orderId=lblOrderId.getText();
        String customerId=cmbCustomerId.getValue();
        String orderDate=lblOrderDate.getText();
        double total= Double.valueOf(lblNetTotal.getText());
        List<SellingOrderTm> tmList = new ArrayList<>();

        for (SellingOrderTm sellingOrderTm: obList) {
            tmList.add(sellingOrderTm);
        }

        SellingOrderDto sellingOrderDto = new SellingOrderDto(orderId, customerId, orderDate,total, tmList);

        boolean isSellingOrderPlaced= new SellingOrderModel().placeSellingOrder(sellingOrderDto);

        if (isSellingOrderPlaced){
            System.out.println("oder Placed");
        }else {
            System.out.println("something went wrong");
        }


    }








}


