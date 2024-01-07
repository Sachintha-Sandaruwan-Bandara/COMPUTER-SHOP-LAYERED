package lk.ijse.computerShop.controller;
/* 
    @author Sachi_S_Bandara
    @created 11/19/2023 - 3:51 PM 
*/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.computerShop.dto.ItemDto;
import lk.ijse.computerShop.model.ItemModel;

import java.io.*;

public class PopUpAddItemFormController {

    @FXML
    private AnchorPane ap;

    @FXML
    private TextField txtBuyingPrice;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSellingPrice;

    private byte [] imageBytes;

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) txtId.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnIdImageAddOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG Image","*.jpg"), new FileChooser.ExtensionFilter("PNG Image", "*.png"), new FileChooser.ExtensionFilter("All image files","*.jpg","*.png"));

        Stage stage = (Stage) txtId.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        System.out.println(file.getPath());

// image convert to byte array
        imageBytes = readImageToByteArray(file);
    }

    private byte[] readImageToByteArray(File file) {
        try (FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            return bos.toByteArray();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws IOException {
        ItemDto itemDto = new ItemDto(
                txtId.getText(),
                txtName.getText(),
                Integer.parseInt(txtBuyingPrice.getText()),
                Integer.parseInt(txtSellingPrice.getText()),
                Integer.parseInt(txtQty.getText()),
                imageBytes


        );
        boolean isSaved= new ItemModel().saveItem(itemDto);

        if (isSaved){
            System.out.println("item saved");
            Stage stage = (Stage) txtName.getScene().getWindow();
            stage.close();

            ItemFormController.itemFormController.loadAllItems();


        }else {
            System.out.println("item not saved!!");
        }
    }

}
