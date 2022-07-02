/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/25/2022
 * Time        : 11:04 AM
 */

package lk.d24.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.d24.hostel.util.ValidationUtil;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class LoginFormController {

    public JFXTextField txtUserName;
    public JFXPasswordField pwdPassword;
    public JFXTextField txtPassword;
    public FontAwesomeIconView icnEye;
    public JFXButton btnLogin;
    public AnchorPane apnMain;

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern usernamePattern = Pattern.compile("^[A-z0-9]{3,10}$");
    LinkedHashMap<JFXPasswordField, Pattern> map1 = new LinkedHashMap<>();
    Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$");

    public void initialize(){
        storeValidation();
    }
    private void storeValidation() {
        map.put(txtUserName, usernamePattern);
        map1.put(pwdPassword, passwordPattern);

    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
    }

    public void lordWindow() throws IOException {

        Stage stage = (Stage) apnMain.getScene().getWindow();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/lk/d24/hostel/view/HomeForm.fxml"));

        Parent root1 = loader1.load();
        Scene scene1 = new Scene(root1);

        stage.setScene(scene1);

        HomeFormController controller = loader1.getController();
        controller.getAllData(txtUserName.getText());

        stage.centerOnScreen();

    }

    public void textFieldValidationOnAction(KeyEvent keyEvent) {
        Object response = ValidationUtil.validateJFXTextFields(map, btnLogin);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }
    }

    public void passwordFieldValidationOnAction(KeyEvent keyEvent) {
        Object response = ValidationUtil.validateJFXPasswordField(map1, btnLogin);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXPasswordField) {
                JFXPasswordField errorText = (JFXPasswordField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }
        }
    }

    public void eyeClickOnAction(MouseEvent mouseEvent) {
        if(icnEye.getGlyphName().equals("EYE_SLASH")){ // must show password
            icnEye.setGlyphName("EYE");

            txtPassword.setText(pwdPassword.getText()); //copy PwdPassword data to  txtPW
            pwdPassword.setVisible(false);  //PWField hidden
            txtPassword.setVisible(true);   //txtField Shown

        }else if(icnEye.getGlyphName().equals("EYE")){  // must hide  password
            icnEye.setGlyphName("EYE_SLASH");

            pwdPassword.setText(txtPassword.getText());
            txtPassword.setVisible(false); //txtField hide
            pwdPassword.setVisible(true);  //PWField shown

        }
    }
}
