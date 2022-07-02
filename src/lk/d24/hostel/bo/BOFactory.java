/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/25/2022
 * Time        : 10:58 AM
 */

package lk.d24.hostel.bo;

import lk.d24.hostel.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes {
    ROOM,STUDENT,RESERVATION,RESERVATION_DETAIL,USER
    }
    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case ROOM:
                return new RoomBoImpl();
            case STUDENT:
                return new StudentBoImpl();
            case RESERVATION:
                return new ReservationBOImpl();
            case RESERVATION_DETAIL:
                return new ReserveDetailBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }


}
