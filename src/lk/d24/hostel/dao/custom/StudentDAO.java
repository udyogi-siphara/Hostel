/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/25/2022
 * Time        : 10:55 AM
 */

package lk.d24.hostel.dao.custom;

import lk.d24.hostel.dao.CrudDAO;
import lk.d24.hostel.entity.Student;

import java.io.IOException;

public interface StudentDAO extends CrudDAO<Student,String> {
    Student getStudent(String id) throws IOException;
}
