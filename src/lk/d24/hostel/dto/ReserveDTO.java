/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/25/2022
 * Time        : 10:52 AM
 */

package lk.d24.hostel.dto;


import lk.d24.hostel.entity.Room;
import lk.d24.hostel.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveDTO {
    String resId;
    LocalDate date;
    Student studentId;
    Room roomId;
    String status;


}
