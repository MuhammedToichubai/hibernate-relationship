package peaksoft.service.impl;

import peaksoft.dao.impl.AddressDaoImpl;
import peaksoft.entities.Address;
import peaksoft.service.CrudService;

import java.util.List;

/**
 * @author Mukhammed Asantegin
 */
public class AddressService implements CrudService<Address, Long> {
    private AddressDaoImpl addressDao = new AddressDaoImpl();
    @Override
    public String save(Address address) {
        return addressDao.save(address);
    }

    @Override
    public Address findById(Long aLong) {
        return null;
    }

    @Override
    public String updateById(Long aLong, Address type) {
        return null;
    }

    @Override
    public String deleteByID(Long aLong) {
        return addressDao.deleteByID(aLong);
    }

    @Override
    public List<Address> getAll() {
        return null;
    }

    public List<Address> findAll() {
       return addressDao.findAll();
    }
}
