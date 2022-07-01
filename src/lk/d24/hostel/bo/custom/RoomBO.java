/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/25/2022
 * Time        : 10:59 AM
 */

package lk.d24.hostel.bo.custom;

import lk.d24.hostel.bo.SuperBO;
import lk.d24.hostel.dto.RoomDTO;
import lk.d24.hostel.entity.Room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface RoomBO extends SuperBO {
    ArrayList<RoomDTO> getAllRoom()throws IOException;

    boolean saveRoom(RoomDTO dto)throws IOException;

    boolean updateRoom(RoomDTO dto)throws IOException;

    boolean deleteRoom(String id)throws IOException;

    String generateRoomId();

    List<Room> getRoomDataFromType(String type) throws IOException;

    Room getRoom(String id) throws IOException;
}
