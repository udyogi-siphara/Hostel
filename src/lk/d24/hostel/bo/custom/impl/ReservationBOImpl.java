/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/30/2022
 * Time        : 9:38 AM
 */

package lk.d24.hostel.bo.custom.impl;

import lk.d24.hostel.bo.BOFactory;
import lk.d24.hostel.bo.custom.ReservationBO;
import lk.d24.hostel.dao.DAOFactory;
import lk.d24.hostel.dao.custom.ReservationDAO;
import lk.d24.hostel.dao.custom.RoomDAO;
import lk.d24.hostel.dao.custom.StudentDAO;
import lk.d24.hostel.dao.custom.impl.ReservationDAOImpl;
import lk.d24.hostel.dao.custom.impl.RoomDAOImpl;
import lk.d24.hostel.dao.custom.impl.StudentDAOImpl;
import lk.d24.hostel.dto.ReserveDTO;
import lk.d24.hostel.dto.RoomDTO;
import lk.d24.hostel.dto.StudentDTO;
import lk.d24.hostel.entity.Reserve;
import lk.d24.hostel.entity.Room;
import lk.d24.hostel.entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {

    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);

    @Override
    public Room getRoom(String id) throws IOException {
        return roomDAO.getRoom(id);
    }

    @Override
    public Student getStudent(String id) throws IOException {
        return studentDAO.getStudent(id);
    }

    @Override
    public ArrayList<RoomDTO> getAllRoom() throws IOException {
       ArrayList<Room> all = roomDAO.getAll();
       ArrayList<RoomDTO> allRoom = new ArrayList<>();

        for (Room room : all) {
            allRoom.add(new RoomDTO(
                    room.getRoomTypeId(),
                    room.getType(),
                    room.getKeyMoney(),
                    room.getQty()
            ));
        }
        return allRoom;
    }

    @Override
    public ArrayList<StudentDTO> getAllStudent() throws IOException {
        ArrayList<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> allStudent = new ArrayList<>();

        for (Student student : all) {
            allStudent.add(new StudentDTO(
                    student.getStudentId(),
                    student.getName(),
                    student.getAddress(),
                    student.getTelNo(),
                    student.getDob(),
                    student.getGender()
            ));
        }
        return allStudent;
    }

    @Override
    public List<ReserveDTO> searchReservedRoomById(String id) throws IOException {
        List<Reserve> reserves = reservationDAO.searchReservedRoomById(id);

        List<ReserveDTO> reserveDTOS = new ArrayList<>();

        for (Reserve reserve : reserves) {
            reserveDTOS.add(new ReserveDTO(
                    reserve.getResId(),
                    reserve.getDate(),
                    reserve.getStudent(),
                    reserve.getRoom(),
                    reserve.getStatus()
            ));
        }
        return reserveDTOS;
    }

    @Override
    public boolean registerStudent(ReserveDTO dto) throws IOException {
        return reservationDAO.save(new Reserve(
                dto.getResId(),
                dto.getDate(),
                dto.getStudentId(),
                dto.getRoomId(),
                dto.getStatus()
        ));
    }
}
