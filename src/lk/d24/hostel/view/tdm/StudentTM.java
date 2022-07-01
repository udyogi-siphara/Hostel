/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/26/2022
 * Time        : 3:48 PM
 */

package lk.d24.hostel.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentTM {
    String studentId;
    String name;
    String address;
    String telNo;
    LocalDate dob;
    String gender;
}
