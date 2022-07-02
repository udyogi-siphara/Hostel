/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 7/1/2022
 * Time        : 8:10 PM
 */

package lk.d24.hostel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    String userId;
    String name;
    String userName;
    String password;
}
