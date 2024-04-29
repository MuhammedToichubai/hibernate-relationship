package peaksoft.service.impl;

import peaksoft.dao.impl.AddressDaoImpl;
import peaksoft.dao.impl.CompanyDao;
import peaksoft.entities.Address;
import peaksoft.entities.Company;
import peaksoft.service.CrudService;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Mukhammed Asantegin
 */
public class CompanyService implements CrudService<Company, Long> {
    private final CompanyDao companyDao = new CompanyDao();
    private AddressDaoImpl addressDao = new AddressDaoImpl();
    @Override
    public String save(Company newCom) {
        return companyDao.save(newCom);
    }

    @Override
    public Company findById(Long aLong) {
        return null;
    }

    @Override
    public String updateById(Long aLong, Company type) {
        return null;
    }

    @Override
    public String deleteByID(Long aLong) {
        return companyDao.deleteByID(aLong);
    }

    @Override
    public List<Company> getAll() {
        return null;
    }

    public String saveCompany(Long addressId, Company company) {
        try {
            Address foundAddress = addressDao.findById(addressId)
                    .orElseThrow(() -> new NoSuchElementException(String.format("Address with id: %d not found!", addressId)));
            company.setAddress(foundAddress);
            foundAddress.setCompany(company);
        }catch (NoSuchElementException e){
            return e.getMessage();
        }
       return companyDao.save(company);
    }

    public void assignCompanyToAddress(Long addressId, Long companyId) {
        companyDao.assign(addressId, companyId);
    }
}
