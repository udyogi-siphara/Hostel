/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/30/2022
 * Time        : 9:37 AM
 */

package lk.d24.hostel.bo.custom;

import lk.d24.hostel.bo.SuperBO;
import lk.d24.hostel.dto.ReserveDTO;
import lk.d24.hostel.dto.RoomDTO;
import lk.d24.hostel.dto.StudentDTO;
import lk.d24.hostel.entity.Room;
import lk.d24.hostel.entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ReservationBO extends SuperBO {
    Room getRoom(String id)throws IOException;

    Student getStudent(String id)throws IOException;

    ArrayList<RoomDTO> getAllRoom()throws IOException;

    ArrayList<StudentDTO> getAllStudent()throws IOException;

    List<ReserveDTO> searchReservedRoomById(String id) throws IOException;

    boolean registerStudent(ReserveDTO dto) throws IOException;

}
