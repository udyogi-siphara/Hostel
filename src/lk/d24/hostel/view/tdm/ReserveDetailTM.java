/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 7/1/2022
 * Time        : 2:33 PM
 */

package lk.d24.hostel.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveDetailTM {
    String resId;
    LocalDate date;
    String studentId;
    String roomId;
    String status;
    String remainKeyMoney;

}
