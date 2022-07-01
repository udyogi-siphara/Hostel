/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/27/2022
 * Time        : 11:24 AM
 */

package lk.d24.hostel.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomTM {
    private String roomTypeId;
    private String type;
    private double keyMoney;
    private int qty;

}
