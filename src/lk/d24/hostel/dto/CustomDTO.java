/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 7/1/2022
 * Time        : 1:18 PM
 */

package lk.d24.hostel.dto;

import lk.d24.hostel.entity.Room;
import lk.d24.hostel.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomDTO {
    String studentId;
    String name;
    String address;
    String telNo;
    LocalDate dob;
    String gender;

    String resId;
    LocalDate date;
    Student regStudentId;
    Room regRoomId;
    String status;

    private String roomTypeId;
    private String type;
    private double keyMoney;
    private int qty;
}
