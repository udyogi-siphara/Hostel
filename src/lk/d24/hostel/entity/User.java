/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 7/1/2022
 * Time        : 8:09 PM
 */

package lk.d24.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    String userId;
    String name;
    String userName;
    String password;
}
