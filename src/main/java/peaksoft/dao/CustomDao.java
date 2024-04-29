package peaksoft.dao;

import java.util.List;
import java.util.Optional;

/**
 * @author Mukhammed Asantegin
 */
public interface CustomDao<T, ID> {
    // save
    String save(T type);

    // find by id
    Optional<T> findById(ID id);

    // update
    String updateById(ID id, T type);

    //delete

    String deleteByID(ID id);

    //get all
    List<T> getAll();

}
