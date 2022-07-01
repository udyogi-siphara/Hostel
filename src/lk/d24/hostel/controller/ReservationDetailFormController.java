/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/30/2022
 * Time        : 10:43 PM
 */

package lk.d24.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;

public class ReservationDetailFormController {

    public JFXTextField txtUpdateStatus;
    public JFXComboBox cmbUpdateSelectStudent;
    public JFXComboBox cmbUpdateSelectRoom;
    public JFXTextField txtReserveID;
    public JFXComboBox cmbSearchRoomId;
    public JFXComboBox cmbRoomType;
    public JFXCheckBox checkPaid;
    public JFXCheckBox checkNonPaid;
    public JFXCheckBox checkOtherPayment;
    public TableView tblReserve;
    public JFXButton btnUpdate;

    public void textFieldValidationOnAction(KeyEvent keyEvent) {
    }

    public void menuEditOnAction(ActionEvent actionEvent) {
    }

    public void menuDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnReserveUpdateOnAction(ActionEvent actionEvent) {
    }

    public void RoomClearOnAction(ActionEvent actionEvent) {
    }

    public void clearSearchOnAction(ActionEvent actionEvent) {
    }
}
