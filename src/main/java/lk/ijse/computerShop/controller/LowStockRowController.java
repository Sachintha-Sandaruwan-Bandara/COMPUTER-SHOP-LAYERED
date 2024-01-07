package lk.ijse.computerShop.controller;
/* 
    @author Sachi_S_Bandara
    @created 11/24/2023 - 12:11 PM 
*/

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

    public class LowStockRowController {

        @FXML
        private JFXButton btnClear;

        @FXML
        private JFXButton btnEdit;

        @FXML
        private Pane colourPane;

        @FXML
        private Label id;

        @FXML
        private Label name;

        @FXML
        private Label qty;

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

        public Label getId() {
            return id;
        }

        public Label getName() {
            return name;
        }

        public Label getQty() {
            return qty;
        }

        public Pane getRow() {
            return row;
        }
    }

