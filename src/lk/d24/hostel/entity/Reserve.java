/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/25/2022
 * Time        : 10:51 AM
 */

package lk.d24.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reserve {
    @Id
    private String resId;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentId", referencedColumnName = "studentId")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roomTypeId", referencedColumnName = "roomTypeId")
    private Room room;
    private String status;

}
