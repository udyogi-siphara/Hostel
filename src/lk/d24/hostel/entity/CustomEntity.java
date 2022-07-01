/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 7/1/2022
 * Time        : 1:10 PM
 */

package lk.d24.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomEntity {
    String studentId;
    String name;
    String address;
    String telNo;
    LocalDate dob;
    String gender;

    private String roomTypeId;
    private String type;
    private double keyMoney;
    private int qty;

    String resId;
    LocalDate date;
    Student regStudentId;
    Room regRoomId;
    String status;
}
