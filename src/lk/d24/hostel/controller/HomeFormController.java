/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/25/2022
 * Time        : 10:49 AM
 */

package lk.d24.hostel.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class HomeFormController {

    public AnchorPane icnSideAnchorPane;
    public Label lblDate;
    public Label lblTime;
    public AnchorPane NameSideAnchorPane;
    public JFXButton btnMangeRooms;
    public JFXButton btnRegisterStudent;
    public JFXButton btnMangeStudent;
    public JFXButton btnReservationDetails;
    public JFXButton btnLogOut;
    public AnchorPane apnMainPain;
    public Label lblUserType;

    public void initialize(){
        loadDateAndTime();
        NameSideAnchorPane.setVisible(false);
    }
    public void iconSideOnAction(MouseEvent mouseEvent) {
        NameSideAnchorPane.setVisible(true);
    }

    public void NameSideOnAction(MouseEvent mouseEvent) {
        NameSideAnchorPane.setVisible(false);
    }

    public void buttonsClickOnAction(MouseEvent mouseEvent) throws IOException {
        Object o = mouseEvent.getSource();

        if (o instanceof JFXButton){
            JFXButton button= (JFXButton) o;

            if(button.getId().equals("DashBoardImage")){

            }if(button.getId().equals("RoomButton")){
                setUI("RoomForm");

            }if(button.getId().equals("RegisterStudentButton")){
                setUI("StudentRegistrationForm");

            }if(button.getId().equals("StudentButton")){
                setUI("StudentForm");

            }if(button.getId().equals("ReservationButton")){
                setUI("ReservationDetailForm");

            }if(button.getId().equals("LogoutButton")){
                setUI("LoginForm");
            }

        }
    }

    public void setUI(String URI) throws IOException {
        apnMainPain.getChildren().clear();
        apnMainPain.getChildren().add(FXMLLoader.load(getClass().getResource("/lk/d24/hostel/view/" + URI + ".fxml")));

    }

    private void loadDateAndTime() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(currentTime.getHour()+":"+currentTime.getMinute()+":"+currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    String userName;
    public void getAllData(String text) {
        userName=text;
        lblUserType.setText(userName);
    }

}
