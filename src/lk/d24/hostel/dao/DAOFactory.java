/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/30/2022
 * Time        : 11:13 PM
 */

package lk.d24.hostel.dao;

import lk.d24.hostel.bo.BOFactory;
import lk.d24.hostel.bo.SuperBO;
import lk.d24.hostel.bo.custom.impl.ReservationBOImpl;
import lk.d24.hostel.bo.custom.impl.RoomBoImpl;
import lk.d24.hostel.bo.custom.impl.StudentBoImpl;
import lk.d24.hostel.dao.custom.impl.ReservationDAOImpl;
import lk.d24.hostel.dao.custom.impl.RoomDAOImpl;
import lk.d24.hostel.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    //singleton
    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes {
        ROOM,STUDENT,RESERVATION
    }
    public SuperDAO getDAO(DAOFactory.DAOTypes types) {
        switch (types) {
            case ROOM:
                return new RoomDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            default:
                return null;
        }
    }

}
