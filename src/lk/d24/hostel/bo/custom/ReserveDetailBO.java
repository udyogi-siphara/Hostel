/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 7/1/2022
 * Time        : 1:57 PM
 */

package lk.d24.hostel.bo.custom;

import lk.d24.hostel.dto.CustomDTO;
import lk.d24.hostel.dto.ReserveDTO;
import lk.d24.hostel.dto.RoomDTO;
import lk.d24.hostel.dto.StudentDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ReserveDetailBO {
    ArrayList<CustomDTO> getAllReservationDetails() throws IOException;
    ArrayList<RoomDTO> getAllRoom() throws IOException;
    ArrayList<StudentDTO> getAllStudent() throws IOException;
    boolean updateReservation(ReserveDTO dto) throws IOException;
    List<ReserveDTO> searchReservedRoomById(String id) throws IOException;
}
