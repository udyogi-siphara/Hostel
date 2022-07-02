/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 7/1/2022
 * Time        : 8:30 PM
 */

package lk.d24.hostel.dao.custom.impl;

import lk.d24.hostel.dao.custom.UserDAO;
import lk.d24.hostel.entity.User;
import lk.d24.hostel.util.FactoryConfiguration;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public ArrayList<User> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        List user = criteria.list();

        ArrayList<User> all = new ArrayList<>(user);

        transaction.commit();
        session.close();
        return all;
    }

    @Override
    public boolean save(User entity) throws IOException {
        return false;
    }

    @Override
    public boolean update(User entity) throws IOException {
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
    public User search(String id) {
        return null;
    }
}
