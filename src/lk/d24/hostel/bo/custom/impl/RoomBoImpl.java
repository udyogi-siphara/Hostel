/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/25/2022
 * Time        : 11:00 AM
 */

package lk.d24.hostel.bo.custom.impl;

import lk.d24.hostel.bo.BOFactory;
import lk.d24.hostel.bo.custom.RoomBO;
import lk.d24.hostel.dao.DAOFactory;
import lk.d24.hostel.dao.custom.RoomDAO;
import lk.d24.hostel.dao.custom.impl.RoomDAOImpl;
import lk.d24.hostel.dto.RoomDTO;
import lk.d24.hostel.entity.Room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomBoImpl implements RoomBO {

    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public ArrayList<RoomDTO> getAllRoom() throws IOException {
        ArrayList<Room> all = roomDAO.getAll();

        ArrayList<RoomDTO> allRoom = new ArrayList<>();

        for (Room room : all) {
            allRoom.add(new RoomDTO(room.getRoomTypeId(),room.getType(),room.getKeyMoney(),room.getQty()));
        }
        return allRoom;
    }

    @Override
    public boolean saveRoom(RoomDTO dto) throws IOException {
        return roomDAO.save(new Room(
                dto.getRoomTypeId(),
                dto.getType(),
                dto.getKeyMoney(),
                dto.getQty()
        ));
    }

    @Override
    public boolean updateRoom(RoomDTO dto) throws IOException {
        return roomDAO.update(new Room(
                dto.getRoomTypeId(),
                dto.getType(),
                dto.getKeyMoney(),
                dto.getQty()
        ));
    }

    @Override
    public boolean deleteRoom(String id) throws IOException {
        return roomDAO.delete(id);
    }

    @Override
    public String generateRoomId() {
        return null;
    }

    @Override
    public List<Room> getRoomDataFromType(String type) throws IOException {
        return roomDAO.getRoomDataFromType(type);
    }

    @Override
    public Room getRoom(String id) throws IOException {
        return roomDAO.getRoom(id);
    }


}
