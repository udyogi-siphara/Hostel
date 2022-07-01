/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/25/2022
 * Time        : 10:59 AM
 */

package lk.d24.hostel.bo.custom;

import lk.d24.hostel.bo.SuperBO;
import lk.d24.hostel.dto.StudentDTO;

import java.io.IOException;
import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    ArrayList<StudentDTO> getAllStudent()throws IOException;

    boolean saveStudent(StudentDTO dto)throws IOException;

    boolean updateStudent(StudentDTO dto)throws IOException;

    boolean deleteStudent(String id)throws IOException;

    String generateStudentId();
}
