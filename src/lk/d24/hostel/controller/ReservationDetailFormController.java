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
import lk.d24.hostel.bo.BOFactory;
import lk.d24.hostel.bo.custom.ReserveDetailBO;
import lk.d24.hostel.bo.custom.RoomBO;
import lk.d24.hostel.bo.custom.impl.ReserveDetailBOImpl;
import lk.d24.hostel.view.tdm.ReserveDetailTM;

public class ReservationDetailFormController {

    public JFXTextField txtUpdateStatus;
    public JFXComboBox<String>cmbUpdateSelectStudent;
    public JFXComboBox<String>cmbUpdateSelectRoom;
    public JFXTextField txtReserveID;
    public JFXComboBox<String>cmbSearchRoomId;
    public JFXComboBox<String>cmbRoomType;
    public JFXCheckBox checkPaid;
    public JFXCheckBox checkNonPaid;
    public JFXCheckBox checkOtherPayment;
    public TableView<ReserveDetailTM>tblReserve;
    public JFXButton btnUpdate;

    private final ReserveDetailBO reserveDetailBO = (ReserveDetailBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION_DETAIL);

    public void initialize(){

    }

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
