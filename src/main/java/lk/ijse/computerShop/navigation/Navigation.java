package lk.ijse.computerShop.navigation;
/* 
    @author Sachi_S_Bandara
    @created 11/3/2023 - 8:31 PM 
*/

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Navigation {
    public static AnchorPane anchorPane;



    public static void navigate(Routes route, AnchorPane anchorPane) throws IOException {
        Navigation.anchorPane = anchorPane;
        Navigation.anchorPane.getChildren().clear();
        Stage window = (Stage) Navigation.anchorPane.getScene().getWindow();


        switch (route) {
            case LOGIN:
                initUI("loginPageForm.fxml");
                window.setTitle("customer");
                break;
            case DASHBOARD:
                initUI("dashboardForm.fxml");
                window.setTitle("customer");
                break;
            case CUSTOMER:
                initUI("customerForm.fxml");
                window.setTitle("customer");
                break;
            case  EMPLOYEE:
                initUI("employeeForm.fxml");
                window.setTitle("employee");
                break;
            case  BUYINGORDER:
                initUI("buyingOrderForm.fxml");
                window.setTitle("buying order");
                break;
            case  SELLINGORDER:
                initUI("sellingOrderForm.fxml");
                window.setTitle("selling order");
                break;
            case  SERVICE:
                initUI("serviceForm.fxml");
                window.setTitle("service");
                break;
            case  SUPPLIER:
                initUI("supplierForm.fxml");
                window.setTitle("supplier");
                break;
            case  ITEM:
                initUI("itemForm.fxml");
                window.setTitle("item");
                break;
            case  ATTENDANCE:
                initUI("attendanceForm.fxml");
                window.setTitle("attendance");
                break;
            case  SALARY:
                initUI("salaryForm.fxml");
                window.setTitle("salary");
                break;
            case  SETTING:
                initUI("settingForm.fxml");
                window.setTitle("setting");
                break;


            default:
                new Alert(Alert.AlertType.ERROR, "Not suitable UI found!").show();
        }
    }

    private static void initUI(String location) throws IOException {
        Navigation.anchorPane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/view/" + location)));
    }


    public static void navigatePopUpWindow(Routes route) throws IOException {
        switch (route) {
            case ADDCUSTOMER:
                initPopUp("popUpAddCustomerForm");
                break;
            case UPDATECUSTOMER:
                initPopUp("popUpUpdateCustomerForm");
                break;
            case ADDEMPLOYEE:
                initPopUp("popUpAddEmployeeForm");
                break;
            case UPDATEEMPLOYEE:
                initPopUp("popUpUpdateEmployeeForm");
                break;
            case PLACEORDERSELECT:
                initPopUp("placeOrderSelectForm");
                break;

            case ADDSUPPLIER:
                initPopUp("popUpAddSupplierForm");
                break;
            case UPDATESUPPLIER:
                initPopUp("popUpUpdateSupplierForm");
                break;
            case ADDITEM:
                initPopUp("popUpAddItemForm");
                break;
            case LOWSTOCK:
                initPopUp("lowStockForm");
                break;
        }


    }

    private static void initPopUp(String url) throws IOException {

        Parent root = FXMLLoader.load(Navigation.class.getResource("/view/" + url+".fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);

        stage.setTitle("");
      //  stage.show();
        stage.showAndWait();


    }
}
