/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 7/1/2022
 * Time        : 8:34 PM
 */

package lk.d24.hostel.bo.custom;

import lk.d24.hostel.bo.SuperBO;
import lk.d24.hostel.dto.UserDTO;

import java.io.IOException;
import java.util.ArrayList;

public interface UserBO extends SuperBO {
    ArrayList<UserDTO> getAllUser() throws IOException;

    boolean updateUser(UserDTO dto) throws IOException;
}
