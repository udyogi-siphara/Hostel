/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/25/2022
 * Time        : 11:00 AM
 */

package lk.d24.hostel.bo.custom.impl;

import lk.d24.hostel.bo.BOFactory;
import lk.d24.hostel.bo.custom.StudentBO;
import lk.d24.hostel.dao.DAOFactory;
import lk.d24.hostel.dao.custom.StudentDAO;
import lk.d24.hostel.dao.custom.impl.StudentDAOImpl;
import lk.d24.hostel.dto.StudentDTO;
import lk.d24.hostel.entity.Student;

import java.io.IOException;
import java.util.ArrayList;

public class StudentBoImpl implements StudentBO {

    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public ArrayList<StudentDTO> getAllStudent() throws IOException {
        ArrayList<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> allStudent = new ArrayList<>();

        for (Student student : all) {
            allStudent.add(new StudentDTO(student.getStudentId(),student.getName(),student.getAddress(),student.getTelNo(),student.getDob(),student.getGender()));
        }
        return allStudent;
    }

    @Override
    public boolean saveStudent(StudentDTO dto) throws IOException {
        return studentDAO.save(new Student(
                dto.getStudentId(),
                dto.getName(),
                dto.getAddress(),
                dto.getTelNo(),
                dto.getDob(),
                dto.getGender()
        ));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws IOException {
        return studentDAO.update(new Student(
                dto.getStudentId(),
                dto.getName(),
                dto.getAddress(),
                dto.getTelNo(),
                dto.getDob(),
                dto.getGender()
        ));
    }

    @Override
    public boolean deleteStudent(String id) throws IOException {
        return studentDAO.delete(id);
    }

    @Override
    public String generateStudentId() {
        return null;
    }
}
