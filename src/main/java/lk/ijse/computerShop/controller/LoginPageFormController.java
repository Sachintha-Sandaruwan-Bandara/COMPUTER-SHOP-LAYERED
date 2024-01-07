package lk.ijse.computerShop.controller;
/* 
    @author Sachi_S_Bandara
    @created 11/7/2023 - 12:52 AM 
*/

import animatefx.animation.ZoomIn;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import lk.ijse.computerShop.dto.UserDto;
import lk.ijse.computerShop.model.UserModel;
import lk.ijse.computerShop.navigation.Navigation;
import lk.ijse.computerShop.navigation.Routes;

import java.io.IOException;

public class LoginPageFormController {

    @FXML
    private ImageView miniLogoImg;
    @FXML
    private ImageView imgLogo;
    @FXML
    private AnchorPane loginPageAnchorPane;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private Text wrongPw;


    public void initialize() {

        introImg();
        wrongPw.setVisible(false);
    }

    private void introImg() {
        ZoomIn zoomIn = new ZoomIn(imgLogo);
        zoomIn.setSpeed(0.2);
        zoomIn.play();

        RotateTransition rotate = new RotateTransition();
        rotate.setNode(miniLogoImg);
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(360);
        rotate.setAxis(Rotate.Y_AXIS);
        rotate.play();


    }


    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        UserDto user = new UserModel().getUser(txtUserName.getText());
        if(txtUserName.getText().equals(null)||txtPassword.getText().equals(null)) {
            wrongPw.setText("User name or password can't be empty !!");
            wrongPw.setVisible(true);
        }
        if (user.getPassword().equals(txtPassword.getText())) {
            Navigation.navigate(Routes.DASHBOARD, loginPageAnchorPane);
        } else {
            wrongPw.setVisible(true);
        }


    }

    @FXML
    void fogetPasswordOnMouseClicked(MouseEvent event) {


    }

    @FXML
    void forgetPasswordOnMouseClicked(MouseEvent event) {

    }
    @FXML
    void btnCloseOnAction(ActionEvent event) {
        System.exit(0);
    }

}