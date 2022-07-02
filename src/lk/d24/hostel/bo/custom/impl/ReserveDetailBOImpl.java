/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 7/1/2022
 * Time        : 2:05 PM
 */

package lk.d24.hostel.bo.custom.impl;

import lk.d24.hostel.bo.custom.ReserveDetailBO;
import lk.d24.hostel.dao.DAOFactory;
import lk.d24.hostel.dao.custom.ReservationDAO;
import lk.d24.hostel.dao.custom.RoomDAO;
import lk.d24.hostel.dao.custom.StudentDAO;
import lk.d24.hostel.dto.CustomDTO;
import lk.d24.hostel.dto.ReserveDTO;
import lk.d24.hostel.dto.RoomDTO;
import lk.d24.hostel.dto.StudentDTO;
import lk.d24.hostel.entity.Reserve;
import lk.d24.hostel.entity.Room;
import lk.d24.hostel.entity.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReserveDetailBOImpl implements ReserveDetailBO {

    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);


    @Override
    public ArrayList<CustomDTO> getAllReservationDetails() throws IOException {
        ArrayList<Reserve> all = reservationDAO.getAll();

        ArrayList<CustomDTO>arrayList=new ArrayList<>();


        for (Reserve res : all) {
            arrayList.add(new CustomDTO(
                    res.getStudent().getStudentId(),
                    res.getStudent().getName(),
                    res.getStudent().getAddress(),
                    res.getStudent().getTelNo(),
                    res.getStudent().getDob(),
                    res.getStudent().getGender(),
                    res.getResId(),
                    res.getDate(),
                    res.getStudent(),
                    res.getRoom(),
                    res.getStatus(),
                    res.getRoom().getRoomTypeId(),
                    res.getRoom().getType(),
                    res.getRoom().getKeyMoney(),
                    res.getRoom().getQty()
            ));


        }

        return arrayList;

    }

    @Override
    public ArrayList<RoomDTO> getAllRoom() throws IOException {
       ArrayList<Room> all = roomDAO.getAll();
       ArrayList<RoomDTO> allRooms = new ArrayList<>();

        for (Room room : all) {
            allRooms.add(new RoomDTO(
                    room.getRoomTypeId(),
                    room.getType(),
                    room.getKeyMoney(),
                    room.getQty()
            ));

        }
        return allRooms;
    }

    @Override
    public ArrayList<StudentDTO> getAllStudent() throws IOException {
        ArrayList<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> allStudents = new ArrayList<>();

        for (Student student : all) {
            allStudents.add(new StudentDTO(
                    student.getStudentId(),
                    student.getName(),
                    student.getAddress(),
                    student.getTelNo(),
                    student.getDob(),
                    student.getGender()
            ));
        }
        return allStudents;
     }

    @Override
    public boolean updateReservation(ReserveDTO dto) throws IOException {
        return reservationDAO.update(new Reserve(
                dto.getResId(),
                dto.getDate(),
                dto.getStudentId(),
                dto.getRoomId(),
                dto.getStatus()
        ));
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
}
