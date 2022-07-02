/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 7/1/2022
 * Time        : 8:44 PM
 */

package lk.d24.hostel.bo.custom.impl;

import lk.d24.hostel.bo.custom.UserBO;
import lk.d24.hostel.dao.DAOFactory;
import lk.d24.hostel.dao.custom.RoomDAO;
import lk.d24.hostel.dao.custom.UserDAO;
import lk.d24.hostel.dto.UserDTO;
import lk.d24.hostel.entity.User;

import java.io.IOException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {

    private final UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public ArrayList<UserDTO> getAllUser() throws IOException {
        ArrayList<User> all = userDAO.getAll();

        ArrayList<UserDTO> ud = new ArrayList<>();


        for (User user : all) {
            ud.add(new UserDTO(
                    user.getUserId(),
                    user.getName(),
                    user.getUserName(),
                    user.getPassword()
            ));
        }

        return ud;

    }
}
