/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/25/2022
 * Time        : 10:56 AM
 */

package lk.d24.hostel.dao.custom.impl;

import lk.d24.hostel.dao.custom.StudentDAO;
import lk.d24.hostel.entity.Student;
import lk.d24.hostel.util.FactoryConfiguration;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public ArrayList<Student> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        Criteria criteria = session.createCriteria(Student.class);
        List student = criteria.list();

        ArrayList<Student> allSt = new ArrayList<>(student);

        transaction.commit();
        session.close();
        return allSt;
    }

    @Override
    public boolean save(Student entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        Student load = session.load(Student.class,s);
        session.delete(load);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public String generateNewId() {
        return null;
    }

    @Override
    public Student search(String id) {
        return null;
    }

    @Override
    public Student getStudent(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        Student st = session.get(Student.class, id);

        transaction.commit();
        session.close();
        return st;
    }
}
