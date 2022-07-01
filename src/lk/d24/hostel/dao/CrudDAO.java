/**
 * @author : Udyogi Siphara
 * Project Name: Hostel
 * Date        : 6/25/2022
 * Time        : 10:53 AM
 */

package lk.d24.hostel.dao;

import java.io.IOException;
import java.util.ArrayList;

public interface CrudDAO<T,ID> extends SuperDAO{
    ArrayList<T> getAll()throws IOException;

    boolean save(T entity)throws IOException;

    boolean update(T entity)throws IOException;

    boolean delete(ID id)throws IOException;

    String generateNewId();

    T search(String id);
}
