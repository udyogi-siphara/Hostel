/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/25/2022
 * Time        : 11:01 AM
 */

package lk.d24.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import lk.d24.hostel.bo.BOFactory;
import lk.d24.hostel.bo.custom.RoomBO;
import lk.d24.hostel.dto.RoomDTO;
import lk.d24.hostel.entity.Room;
import lk.d24.hostel.view.tdm.RoomTM;


import java.io.IOException;

public class RoomFormController {

    public TableView<RoomTM>tblRoom;
    public JFXTextField txtRoomType;
    public JFXTextField txtQty;
    public JFXTextField txtRoomId;
    public JFXTextField txtKeyMoney;
    public JFXButton btnAdd;
    public JFXComboBox<String>cmbRoomType;
    public JFXCheckBox checkRoomType;
    public JFXComboBox<String>cmbRoomId;
    public JFXCheckBox checkRoomId;
    public JFXButton btnAddRoomType;
    public JFXButton btnAddRoomId;
    public Rectangle reId;
    public Rectangle recType;

    private final RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);

    public void initialize() throws IOException {
        tblRoom.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("roomTypeId"));
        tblRoom.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblRoom.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        tblRoom.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));

        cmbRoomId.setDisable(false);

        btnAddRoomType.setDisable(true);
        txtRoomType.setDisable(true);
        recType.setDisable(true);

        btnAddRoomId.setDisable(true);
        txtRoomId.setDisable(true);
        reId.setDisable(true);

        txtKeyMoney.setEditable(false);

        loadAllRooms();
        setCmbRoomTypes();
        addRoomTypeSelectListener();
        setRoomIds();

        enableDisableCheckBox(checkRoomType, btnAddRoomType, txtRoomType, recType,cmbRoomType);

        enableDisableCheckBox(checkRoomId, btnAddRoomId, txtRoomId, reId,cmbRoomId);
    }

    private void enableDisableCheckBox(JFXCheckBox checkBox, JFXButton btnAdd, JFXTextField txtRoom, Rectangle rectangle,JFXComboBox combo) {
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue){
                btnAdd.setDisable(false);
                txtRoom.setDisable(false);
                rectangle.setDisable(false);

                txtKeyMoney.setEditable(true);
                combo.setValue(null);

                if(checkBox.getText().equals("Add new Room Type")){
                    txtKeyMoney.clear();
                }

            }else {
                btnAdd.setDisable(true);
                txtRoom.setDisable(true);
                rectangle.setDisable(true);
                txtKeyMoney.setEditable(false);
            }
        });
    }

    private void addRoomTypeSelectListener(){
        cmbRoomType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                try {
                    for (Room room : roomBO.getRoomDataFromType(newValue)) {

                        txtRoomId.setText(room.getRoomTypeId());
                        txtKeyMoney.setText(String.valueOf(room.getKeyMoney()));

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadAllRooms() throws IOException {
        tblRoom.getItems().clear();
        for (RoomDTO roomDTO : roomBO.getAllRoom()) {
            tblRoom.getItems().add(new RoomTM(
                    roomDTO.getRoomTypeId(),
                    roomDTO.getType(),
                    roomDTO.getKeyMoney(),
                    roomDTO.getQty()
            ));
        }
    }

    private void setCmbRoomTypes() throws IOException {
        cmbRoomType.getItems().clear();
        for (RoomDTO roomDTO : roomBO.getAllRoom()) {
            cmbRoomType.getItems().add(roomDTO.getType());
        }

    }

    private void setRoomIds() throws IOException {
        cmbRoomId.getItems().clear();
        for (RoomDTO roomDTO : roomBO.getAllRoom()) {
            cmbRoomId.getItems().add(roomDTO.getRoomTypeId());
        }
    }


    public void menuEditOnAction(ActionEvent actionEvent) {
        RoomTM selectedItem = tblRoom.getSelectionModel().getSelectedItem();

        cmbRoomId.getSelectionModel().select(selectedItem.getRoomTypeId());
        cmbRoomId.setDisable(true);

        cmbRoomType.getSelectionModel().select(selectedItem.getType());
        txtKeyMoney.setText(String.valueOf(selectedItem.getKeyMoney()));
        txtQty.setText(String.valueOf(selectedItem.getQty()));
        txtKeyMoney.setEditable(true);

        btnAdd.setText("Update");
    }

    public void menuDeleteOnAction(ActionEvent actionEvent) throws IOException {
        RoomTM selectedItem = tblRoom.getSelectionModel().getSelectedItem();

        if (roomBO.deleteRoom(selectedItem.getRoomTypeId())){
            new Alert(Alert.AlertType.CONFIRMATION, "Student Deleted SuccessFully").show();
            loadAllRooms();
            setCmbRoomTypes();
            setRoomIds();
        }else{
            new Alert(Alert.AlertType.WARNING, "Something Went Wrong !!").show();
        }
    }

    public void textFieldValidationOnAction(KeyEvent keyEvent) {
    }

    public void RoomAddOnAction(ActionEvent actionEvent) throws IOException {
        boolean b=true;
        for (RoomDTO roomDTO : roomBO.getAllRoom()) {
            if (roomDTO.getRoomTypeId().equals(cmbRoomId.getValue())){
                b=false;
            }
        }
        if (b) {
            roomBO.saveRoom(new RoomDTO(
                    cmbRoomId.getValue(),
                    cmbRoomType.getValue(),
                    Double.parseDouble(txtKeyMoney.getText()),
                    Integer.parseInt(txtQty.getText())
            ));
            clearAllFields();
            loadAllRooms();
        }else{
            Room room = roomBO.getRoom(cmbRoomId.getValue());
            roomBO.updateRoom(new RoomDTO(
                    cmbRoomId.getValue(),
                    cmbRoomType.getValue(),
                    Double.parseDouble(txtKeyMoney.getText()),
                    (room.getQty()+(Integer.parseInt(txtQty.getText()))
                    )));
            clearAllFields();
            loadAllRooms();
        }
    }

    public void RoomClearOnAction(ActionEvent actionEvent) {
        clearAllFields();
    }

    public void clearAllFields(){
        cmbRoomType.setValue(null);
        txtRoomType.clear();
        txtKeyMoney.clear();
        cmbRoomId.setValue(null);
        txtRoomId.clear();
        txtQty.clear();
    }

    public void btnAddRoomTypeOnAction(ActionEvent actionEvent) {
        cmbRoomType.getItems().add(txtRoomType.getText());
        cmbRoomType.getSelectionModel().select(txtRoomType.getText());

        cmbRoomId.setValue(null);
        cmbRoomId.setDisable(true);
        checkRoomId.selectedProperty().setValue(true);
        checkRoomType.selectedProperty().setValue(false);
    }

    public void btnAddRoomIdOnAction(ActionEvent actionEvent) {
        cmbRoomId.getItems().add(txtRoomId.getText());
        cmbRoomId.getSelectionModel().select(txtRoomId.getText());
        cmbRoomId.setDisable(false);
        checkRoomId.selectedProperty().setValue(false);
    }
}
