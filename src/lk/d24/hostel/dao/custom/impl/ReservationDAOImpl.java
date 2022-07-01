/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/29/2022
 * Time        : 11:25 PM
 */

package lk.d24.hostel.dao.custom.impl;

import lk.d24.hostel.dao.custom.ReservationDAO;
import lk.d24.hostel.entity.Reserve;
import lk.d24.hostel.entity.Room;
import lk.d24.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    @Override
    public ArrayList<Reserve> getAll() throws IOException {
        return null;
    }

    @Override
    public boolean save(Reserve entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reserve entity) throws IOException {
        return false;
    }

    @Override
    public boolean delete(String s) throws IOException {
        return false;
    }

    @Override
    public String generateNewId() {
        return null;
    }

    @Override
    public Reserve search(String id) {
        return null;
    }

    @Override
    public List<Reserve> searchReservedRoomById(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Reserve WHERE room = :roomTypeId";
        Query query = session.createQuery(hql);

        Room room = new Room();
        room.setRoomTypeId(id);

        query.setParameter("roomTypeId",room);
        List<Reserve> r = query.list();

        transaction.commit();
        session.close();

        return r;
    }
}
