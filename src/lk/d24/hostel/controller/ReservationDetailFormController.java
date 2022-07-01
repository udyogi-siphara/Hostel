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
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.d24.hostel.bo.BOFactory;
import lk.d24.hostel.bo.custom.ReserveDetailBO;
import lk.d24.hostel.bo.custom.RoomBO;
import lk.d24.hostel.bo.custom.impl.ReserveDetailBOImpl;
import lk.d24.hostel.dto.CustomDTO;
import lk.d24.hostel.view.tdm.ReserveDetailTM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

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

    LocalDate date;
    private final ReserveDetailBO reserveDetailBO = (ReserveDetailBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION_DETAIL);

    private ObservableSet<JFXCheckBox> selectedCheckBox = FXCollections.observableSet();
    private ObservableSet<JFXCheckBox> unSelectedCheckBox = FXCollections.observableSet();

    private IntegerBinding numCheckBoxSelected = Bindings.size(selectedCheckBox);

    private final int maxNumSelected =  1;

    public void initialize() throws IOException {
        loadAllReservation();

        tblReserve.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("resId"));
        tblReserve.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblReserve.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("studentId"));
        tblReserve.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("roomId"));
        tblReserve.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("status"));
        tblReserve.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("remainKeyMoney"));

        numCheckBoxSelected.addListener((obs, oldSelectedCount, newSelectedCount) -> {
            if (newSelectedCount.intValue() >= maxNumSelected){
                unSelectedCheckBox.forEach(cb -> cb.setDisable(true));
            }else{
                unSelectedCheckBox.forEach(cb -> cb.setDisable(false));

                try {
                    loadAllReservation();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                cmbSearchRoomId.setValue(null);
                cmbRoomType.setValue(null);

            }
        });

        txtReserveID.setDisable(true);
        cmbUpdateSelectStudent.setDisable(true);
        cmbUpdateSelectRoom.setDisable(true);
        txtUpdateStatus.setDisable(true);

    }

    private void loadAllReservation() throws IOException {
        tblReserve.getItems().clear();

        ArrayList<CustomDTO> allRes = reserveDetailBO.getAllReservationDetails();

        for (CustomDTO all : allRes) {
           String remain = "";
           String status = all.getStatus();

           if (status.equalsIgnoreCase("Paid")){
               remain = "---";
           }else if(status.equalsIgnoreCase("Non-Paid")){
               remain = String.valueOf(all.getKeyMoney());
           }else{
                if (!status.equals("")){
                    double paid = Double.parseDouble(status);
                    remain = String.valueOf(all.getKeyMoney() - paid);
                }
           }

           tblReserve.getItems().add(new ReserveDetailTM(
                   all.getResId(),
                   all.getDate(),
                   all.getRegStudentId().getStudentId(),
                   all.getRegRoomId().getRoomTypeId(),
                   all.getStatus(),
                   remain
           ));

        }

    }

    public void textFieldValidationOnAction(KeyEvent keyEvent) {
    }

    public void menuEditOnAction(ActionEvent actionEvent) {
        txtReserveID.setDisable(false);
        cmbUpdateSelectStudent.setDisable(false);
        cmbUpdateSelectRoom.setDisable(false);
        txtUpdateStatus.setDisable(false);

        ReserveDetailTM selectedItem = tblReserve.getSelectionModel().getSelectedItem();

        txtReserveID.setText(selectedItem.getResId());
        txtReserveID.setEditable(false);
        date = selectedItem.getDate();
        cmbUpdateSelectStudent.getSelectionModel().select(selectedItem.getStudentId());
        cmbUpdateSelectRoom.getSelectionModel().select(selectedItem.getRoomId());

        txtUpdateStatus.setText(selectedItem.getStatus());
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
