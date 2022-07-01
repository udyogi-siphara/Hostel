/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/29/2022
 * Time        : 11:24 PM
 */

package lk.d24.hostel.dao.custom;

import lk.d24.hostel.dao.CrudDAO;
import lk.d24.hostel.entity.Reserve;

import java.io.IOException;
import java.util.List;

public interface ReservationDAO extends CrudDAO<Reserve,String> {
    List<Reserve> searchReservedRoomById (String id) throws IOException;
}
