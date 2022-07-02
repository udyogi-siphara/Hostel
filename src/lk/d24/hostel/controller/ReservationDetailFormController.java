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
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.d24.hostel.bo.BOFactory;
import lk.d24.hostel.bo.custom.ReserveDetailBO;
import lk.d24.hostel.bo.custom.RoomBO;
import lk.d24.hostel.bo.custom.impl.ReserveDetailBOImpl;
import lk.d24.hostel.dto.CustomDTO;
import lk.d24.hostel.dto.ReserveDTO;
import lk.d24.hostel.dto.RoomDTO;
import lk.d24.hostel.dto.StudentDTO;
import lk.d24.hostel.entity.Room;
import lk.d24.hostel.entity.Student;
import lk.d24.hostel.view.tdm.ReserveDetailTM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

        loadCmbData();
        loadSearchReserve();
        addCheckBoxListener();

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

        configureCheckBox(checkPaid);
        configureCheckBox(checkNonPaid);
        configureCheckBox(checkOtherPayment);

        txtReserveID.setDisable(true);
        cmbUpdateSelectStudent.setDisable(true);
        cmbUpdateSelectRoom.setDisable(true);
        txtUpdateStatus.setDisable(true);

    }

    private void configureCheckBox(JFXCheckBox checkBox) {
        if (checkBox.isSelected()){
            selectedCheckBox.add(checkBox);
        }else{
            unSelectedCheckBox.add(checkBox);
        }

        checkBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected){
                unSelectedCheckBox.remove(checkBox);
                selectedCheckBox.add(checkBox);
            }else{
                unSelectedCheckBox.remove(checkBox);
                selectedCheckBox.add(checkBox);
            }
        });
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

    private void loadCmbData() throws IOException {
        for (RoomDTO roomDTO : reserveDetailBO.getAllRoom()) {
            cmbUpdateSelectRoom.getItems().add(roomDTO.getRoomTypeId());
            cmbSearchRoomId.getItems().add(roomDTO.getRoomTypeId());
            cmbRoomType.getItems().add(roomDTO.getType());
        }

        for (StudentDTO dto : reserveDetailBO.getAllStudent()) {
            cmbUpdateSelectRoom.getItems().add(dto.getStudentId());
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

    private void loadSearchReserve(){
        cmbSearchRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                tblReserve.getItems().clear();
                try {
                    List<ReserveDTO> reserveDTOS = reserveDetailBO.searchReservedRoomById(newValue);

                    checkPaid.selectedProperty().setValue(false);
                    checkNonPaid.selectedProperty().setValue(false);
                    checkOtherPayment.selectedProperty().setValue(false);

                    for (ReserveDTO reserveDTO : reserveDTOS) {
                        cmbRoomType.getSelectionModel().select(reserveDTO.getRoomId().getType());

                        String remain ="";
                        String status = reserveDTO.getStatus();

                        if (status.equalsIgnoreCase("Paid")){
                            remain = "---";
                        }else if (status.equalsIgnoreCase("Non-Paid")){
                            remain = String.valueOf(reserveDTO.getRoomId().getKeyMoney());
                        }else {
                            if(!status.equals("")){
                                double paid = Double.parseDouble(status);
                                remain = String.valueOf(reserveDTO.getRoomId().getKeyMoney()-paid);
                            }
                        }

                        tblReserve.getItems().add(new ReserveDetailTM(
                                reserveDTO.getResId(),
                                reserveDTO.getDate(),
                                reserveDTO.getResId(),
                                reserveDTO.getStudentId().getStudentId(),
                                reserveDTO.getRoomId().getRoomTypeId(),
                                remain
                        ));

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void addCheckBoxListener(){
        checkPaid.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                if (cmbSearchRoomId.getSelectionModel().isEmpty()){
                    try {
                        ArrayList<CustomDTO> allReservationDetails = reserveDetailBO.getAllReservationDetails();
                        tblReserve.getItems().clear();

                        for (CustomDTO all : allReservationDetails) {

                            if (all.getStatus().equalsIgnoreCase("Paid")){
                                String remain = "";
                                String status = all.getStatus();

                                if (status.equalsIgnoreCase("Paid")){
                                    remain = "---";
                                }else if (status.equalsIgnoreCase("Non-Paid")){
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
                                        all.getStudentId(),
                                        all.getRoomTypeId(),
                                        all.getStatus(),
                                        remain
                                ));

                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        ArrayList<CustomDTO> allResevervationDetails = reserveDetailBO.getAllReservationDetails();

                        tblReserve.getItems().clear();

                        for (CustomDTO all : allResevervationDetails) {
                            if (all.getStatus().equalsIgnoreCase("Paid") && all.getRegRoomId().getRoomTypeId().equals(cmbSearchRoomId.getValue())){
                                String remain = "";
                                String status = all.getStatus();

                                if (status.equalsIgnoreCase("Paid")){
                                    remain = "---";
                                }else if(status.equalsIgnoreCase("Non-Paid")){
                                    remain = String.valueOf(all.getKeyMoney());
                                }else{
                                    if (!status.equals("")){
                                        double paid = Double.parseDouble(status);
                                        remain = String.valueOf(all.getKeyMoney()-paid);
                                    }
                                }

                                tblReserve.getItems().add(new ReserveDetailTM(
                                        all.getResId(),
                                        all.getDate(),
                                        all.getStudentId(),
                                        all.getRoomTypeId(),
                                        all.getStatus(),
                                        remain
                                ));
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        checkNonPaid.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue !=null){
                if (cmbSearchRoomId.getSelectionModel().isEmpty()){
                    try {
                        ArrayList<CustomDTO> allReservationDetails = reserveDetailBO.getAllReservationDetails();
                        tblReserve.getItems().clear();
                        for (CustomDTO all : allReservationDetails) {
                            if (all.getStatus().equalsIgnoreCase("Non-Paid")){
                                String remain = "---";
                                String status = all.getStatus();

                                if (status.equalsIgnoreCase("Paid")){
                                    remain = "---";
                                }else if (status.equalsIgnoreCase("Non-Paid")){
                                    remain = String.valueOf(all.getKeyMoney());
                                }else{
                                    if (!status.equals("")){
                                        double paid = Double.parseDouble(status);
                                        remain=String.valueOf(all.getKeyMoney()-paid);
                                    }
                                }
                                tblReserve.getItems().add(new ReserveDetailTM(
                                        all.getResId(),
                                        all.getDate(),
                                        all.getStudentId(),
                                        all.getRoomTypeId(),
                                        all.getStatus(),
                                        remain
                                ));
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        ArrayList<CustomDTO> allResevervationDetails = reserveDetailBO.getAllReservationDetails();
                        tblReserve.getItems().clear();

                        for (CustomDTO all : allResevervationDetails) {
                            if (all.getStatus().equalsIgnoreCase("Non-Paid")&& all.getRegRoomId().getRoomTypeId().equals(cmbSearchRoomId.getValue())){
                                String remain = "";
                                String status = all.getStatus();

                                if (status.equalsIgnoreCase("Paid")){
                                    remain = "---";
                                }else if(status.equalsIgnoreCase("Non-Paid")){
                                    remain = String.valueOf(all.getKeyMoney());
                                }else {
                                    if (!status.equals("")){
                                        double paid = Double.parseDouble(status);
                                        remain = String.valueOf(all.getKeyMoney()-paid);
                                    }
                                }
                                tblReserve.getItems().add(new ReserveDetailTM(
                                        all.getResId(),
                                        all.getDate(),
                                        all.getStudentId(),
                                        all.getRoomTypeId(),
                                        all.getStatus(),
                                        remain
                                ));
                            }

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        checkOtherPayment.selectedProperty().addListener((observable, oldValue, newValue) -> {
           if (newValue!=null){
               if (cmbSearchRoomId.getSelectionModel().isEmpty()){
                   try {
                       ArrayList<CustomDTO> allReservationDetails = reserveDetailBO.getAllReservationDetails();
                       tblReserve.getItems().clear();

                       for (CustomDTO all : allReservationDetails) {
                           if (!all.getStatus().equalsIgnoreCase("Paid") && !all.getStatus().equalsIgnoreCase("Non-Paid")){
                                String remain = "";
                                String status = all.getStatus();

                                if (status.equalsIgnoreCase("Paid")){
                                    remain = "";
                                }else if (status.equalsIgnoreCase("Non-Paid")){
                                    remain = String.valueOf(all.getKeyMoney());
                                }else {
                                    if (!status.equals("")) {
                                        double paid = Double.parseDouble(status);
                                        remain = String.valueOf(all.getKeyMoney()-paid);
                                    }
                                }
                               tblReserve.getItems().add(new ReserveDetailTM(
                                       all.getResId(),
                                       all.getDate(),
                                       all.getStudentId(),
                                       all.getRoomTypeId(),
                                       all.getStatus(),
                                       remain
                               ));
                           }
                       }

                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }else{
                   try {
                       ArrayList<CustomDTO> allReservationDetails = reserveDetailBO.getAllReservationDetails();
                       tblReserve.getItems().clear();

                       for (CustomDTO all : allReservationDetails) {
                           if (!all.getStatus().equalsIgnoreCase("Paid") && !all.getStatus().equalsIgnoreCase("Non-Paid")){
                               String remain = "";
                               String status = all.getStatus();

                               if (status.equalsIgnoreCase("paid")){
                                   remain = "---";
                               }else if (status.equalsIgnoreCase("Non-Paid")){
                                   remain = String.valueOf(all.getKeyMoney());
                               }else{
                                   if (!status.equals("")) {
                                       double paid = Double.parseDouble(status);
                                       remain = String.valueOf(all.getKeyMoney() - paid);
                                   }
                               }
                               tblReserve.getItems().add(new ReserveDetailTM(
                                       all.getResId(),
                                       all.getDate(),
                                       all.getStudentId(),
                                       all.getRoomTypeId(),
                                       all.getStatus(),
                                       remain
                               ));
                           }
                       }

                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           }
        });
    }

    public void menuDeleteOnAction(ActionEvent actionEvent) {

    }

    public void btnReserveUpdateOnAction(ActionEvent actionEvent) throws IOException {
        txtReserveID.setDisable(true);
        cmbUpdateSelectStudent.setDisable(true);
        cmbUpdateSelectRoom.setDisable(true);
        txtUpdateStatus.setDisable(true);

        Student student = new Student();
        student.setStudentId(cmbUpdateSelectStudent.getValue());

        Room room = new Room();
        room.setRoomTypeId(cmbUpdateSelectRoom.getValue());

        boolean b = reserveDetailBO.updateReservation(new ReserveDTO(
                txtReserveID.getText(),
                date,
                student,
                room,
                txtUpdateStatus.getText()));

        if (b) {
            new Alert(Alert.AlertType.INFORMATION, "Reservation Updated!!").show();
            loadAllReservation();
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Something Went Wrong").show();

        }
    }

    public void RoomClearOnAction(ActionEvent actionEvent) {
    }

    public void clearSearchOnAction(ActionEvent actionEvent) throws IOException {
        loadAllReservation();
        cmbSearchRoomId.setValue(null);
        cmbRoomType.setValue(null);

        checkPaid.selectedProperty().setValue(false);
        checkNonPaid.selectedProperty().setValue(false);
        checkOtherPayment.selectedProperty().setValue(false);
    }
}
