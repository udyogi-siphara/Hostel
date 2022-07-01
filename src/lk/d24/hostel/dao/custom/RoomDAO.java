/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/25/2022
 * Time        : 10:55 AM
 */

package lk.d24.hostel.dao.custom;

import lk.d24.hostel.dao.CrudDAO;
import lk.d24.hostel.entity.Room;

import java.io.IOException;
import java.util.List;

public interface RoomDAO extends CrudDAO<Room,String> {
    List<Room> getRoomDataFromType(String type) throws IOException;
    Room getRoom(String id) throws IOException;
}
