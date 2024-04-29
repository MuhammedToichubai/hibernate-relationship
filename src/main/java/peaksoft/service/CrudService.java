package peaksoft.service;


import java.util.List;

/**
 * @author Mukhammed Asantegin
 */
public interface CrudService<T, ID> {
    // save
    String save(T type);

    // find by id
    T findById(ID id);

    // update
    String updateById(ID id, T type);

    //delete

    String deleteByID(ID id);

    //get all
    List<T> getAll();

}

