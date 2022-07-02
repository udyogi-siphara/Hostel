/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/25/2022
 * Time        : 11:03 AM
 */

package lk.d24.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import lk.d24.hostel.bo.BOFactory;
import lk.d24.hostel.bo.custom.ReservationBO;
import lk.d24.hostel.bo.custom.StudentBO;
import lk.d24.hostel.bo.custom.impl.ReservationBOImpl;
import lk.d24.hostel.dto.ReserveDTO;
import lk.d24.hostel.dto.RoomDTO;
import lk.d24.hostel.dto.StudentDTO;
import lk.d24.hostel.entity.Room;
import lk.d24.hostel.entity.Student;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class StudentRegistrationFormController {

    public JFXComboBox<String>cmbSelectStudent;
    public JFXComboBox<String>cmbSelectRoom;
    public JFXTextField txtStudentName;
    public JFXTextField txtAddress;
    public JFXDatePicker dateDOB;
    public JFXComboBox<String>cmbGender;
    public JFXTextField txtRegistrationId;
    public JFXTextField txtRoomType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQty;
    public JFXButton btnRegister;
    public Label lblAvailable;
    public JFXTextField txtStatus;
    public Label lblAllRooms;
    public Label lblUsedRooms;
    public Label lblRemainingRooms;

    private final ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);

    public void initialize() throws IOException {

        lblAvailable.setText(".................");
        cmbGender.getItems().addAll("Male","Female");

        for (StudentDTO studentDTO : reservationBO.getAllStudent()) {
            cmbSelectStudent.getItems().add(studentDTO.getStudentId());
        }

        for (RoomDTO roomDTO : reservationBO.getAllRoom()) {
            cmbSelectRoom.getItems().add(roomDTO.getRoomTypeId());
        }

        cmbSelectRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                Room room = null;
                try {
                    room = reservationBO.getRoom(newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                txtRoomType.setText(room.getType());
                txtKeyMoney.setText(String.valueOf(room.getKeyMoney()));
                txtQty.setText(String.valueOf(room.getQty()));

                try {
                    List<ReserveDTO> reserveDTOS = reservationBO.searchReservedRoomById(newValue);

                    int count = 0;
                    for (ReserveDTO reserveDTO : reserveDTOS) {
                        count++;
                    }
                    int remainQty = Integer.parseInt(txtQty.getText())-count;
                    lblUsedRooms.setText(String.valueOf(count));
                    lblRemainingRooms.setText(String.valueOf(room.getQty()));

                    if(remainQty==0){
                        lblAvailable.setText("Un-Available");
                    }else{
                        lblAvailable.setText("Available");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        cmbSelectStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                Student student = null;
                try {
                    student = reservationBO.getStudent(newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                txtStudentName.setText(student.getName());
                txtAddress.setText(student.getAddress());
                cmbGender.getSelectionModel().select(student.getGender());
                dateDOB.setValue(student.getDob());
            }
        });

        searchRoomQty();
    }

    private void searchRoomQty() {
    }

    public void textFieldValidationOnAction(KeyEvent keyEvent) {
    }

    public void RegisterOnAction(ActionEvent actionEvent) throws IOException {
        if (lblAvailable.getText().equalsIgnoreCase("Available")){
            Student student = new Student();
            student.setStudentId(cmbSelectStudent.getValue());

            Room room = new Room();
            room.setRoomTypeId(cmbSelectRoom.getValue());
            reservationBO.registerStudent(new ReserveDTO(
                 txtRegistrationId.getText(),
                 LocalDate.now(),
                 student,
                 room,
                 txtStatus.getText()
            ));
            clearAllFields();
            lblAvailable.setText("00");
            lblUsedRooms.setText("00");
            lblRemainingRooms.setText("00");
        }else{
        new Alert(Alert.AlertType.WARNING,"You Can't Register Student for This Room").show();
        }
    }

    public void ClearOnAction(ActionEvent actionEvent) {
        clearAllFields();
    }

    private void clearAllFields(){
        cmbSelectStudent.setValue(null);
        cmbSelectRoom.setValue(null);
        txtStatus.clear();
    }
}
