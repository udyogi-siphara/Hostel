/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/25/2022
 * Time        : 10:52 AM
 */

package lk.d24.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    private String studentId;
    private String name;
    private String address;
    private String telNo;
    private LocalDate dob;
    private String gender;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Reserve> reservation;

    public Student(String studentId, String name, String address, String telNo, LocalDate dob, String gender) {
        this.studentId = studentId;
        this.name = name;
        this.address = address;
        this.telNo = telNo;
        this.dob = dob;
        this.gender = gender;

    }
}
