/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/25/2022
 * Time        : 11:02 AM
 */

package lk.d24.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.d24.hostel.bo.BOFactory;
import lk.d24.hostel.bo.custom.StudentBO;
import lk.d24.hostel.bo.custom.impl.StudentBoImpl;
import lk.d24.hostel.dto.StudentDTO;
import lk.d24.hostel.util.ValidationUtil;
import lk.d24.hostel.view.tdm.StudentTM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class StudentFormController {

    public TableView<StudentTM>tblStudent;
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtAddress;
    public JFXTextField txtTellNo;
    public JFXDatePicker dateDOB;
    public JFXComboBox<String>cmbGender;
    public JFXButton btnAdd;

    private final StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();

    Pattern studentNamePattern = Pattern.compile("^[A-z ]{4,25}$");
    Pattern studentaddressPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
    Pattern phonePattern = Pattern.compile("^(\\+\\d{1,3}[- ]?)?\\d{10}$");

    public void initialize() throws IOException {

        storeValidations();

        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("studentId"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("telNo"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));

        cmbGender.getItems().addAll("Male","Female");

        getAllStudent();
    }

    private void storeValidations() {
        map.put(txtStudentName, studentNamePattern);
        map.put(txtAddress, studentNamePattern);
        map.put(txtTellNo, phonePattern);
    }

    public void getAllStudent() throws IOException {
        tblStudent.getItems().clear();
        ArrayList<StudentDTO> allStudent = studentBO.getAllStudent();

        for (StudentDTO studentDTO : allStudent) {
            tblStudent.getItems().add(new StudentTM(
                    studentDTO.getStudentId(),
                    studentDTO.getName(),
                    studentDTO.getAddress(),
                    studentDTO.getTelNo(),
                    studentDTO.getDob(),
                    studentDTO.getGender()
            ));
        }
    }

    public void textFieldValidationOnAction(KeyEvent keyEvent) {
        Object response = ValidationUtil.validateJFXTextField(map, btnAdd);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {

            }

        }

    }

    public void StudentAddOnAction(ActionEvent actionEvent) throws IOException {
        if (btnAdd.getText().equals("Add Student")){
            boolean b = studentBO.saveStudent(new StudentDTO(
                    txtStudentId.getText(),
                    txtStudentName.getText(),
                    txtAddress.getText(),
                    txtTellNo.getText(),
                    dateDOB.getValue(),
                    cmbGender.getValue()
            ));

            if (b){
                new Alert(Alert.AlertType.CONFIRMATION, "Student Added SuccessFully").show();

                tblStudent.getItems().add(new StudentTM(
                        txtStudentId.getText(),
                        txtStudentName.getText(),
                        txtAddress.getText(),
                        txtTellNo.getText(),
                        dateDOB.getValue(),
                        cmbGender.getValue()
                ));
                clearAllFields();
            }else{
                new Alert(Alert.AlertType.WARNING, "Something Went Wrong !!").show();
            }
        }else{
            studentBO.updateStudent(new StudentDTO(
                    txtStudentId.getText(),
                    txtStudentName.getText(),
                    txtAddress.getText(),
                    txtTellNo.getText(),
                    dateDOB.getValue(),
                    cmbGender.getValue()
            ));
            btnAdd.setText("Add Student");
            txtStudentId.setEditable(true);
            clearAllFields();
            getAllStudent();
        }

    }

    public void RoomClearOnAction(ActionEvent actionEvent) {
        clearAllFields();
    }

    public void clearAllFields(){
        txtStudentId.clear();
        txtStudentName.clear();
        txtAddress.clear();
        txtTellNo.clear();
        cmbGender.setValue(null);
        dateDOB.setValue(null);
    }

    public void menuDeleteOnAction(ActionEvent actionEvent) throws IOException {
        StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
        if (studentBO.deleteStudent(selectedItem.getStudentId())){
            new Alert(Alert.AlertType.CONFIRMATION,"Student Deleted SuccessFully").show();
            getAllStudent();
        }else{
            new Alert(Alert.AlertType.WARNING,"Something Went Wring !!").show();
        }
    }

    public void menuUpdateOnAction(ActionEvent actionEvent) {
        StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();

        txtStudentId.setText(selectedItem.getStudentId());
        txtStudentName.setEditable(false);
        txtStudentName.setText(selectedItem.getName());
        txtAddress.setText(selectedItem.getAddress());
        txtTellNo.setText(selectedItem.getTelNo());
        dateDOB.setValue(selectedItem.getDob());
        cmbGender.setValue(selectedItem.getGender());

        btnAdd.setText("Update");
    }
}
