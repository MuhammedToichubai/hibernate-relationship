package peaksoft.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.DatabaseConnectionConfig;
import peaksoft.dao.CustomDao;
import peaksoft.entities.Address;
import peaksoft.entities.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Mukhammed Asantegin
 */
public class AddressDaoImpl implements CustomDao<Address, Long> {

    private final EntityManagerFactory entityManagerFactory = DatabaseConnectionConfig.entityManagerFactory();
    @Override
    public String save(Address address) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(address);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
           rollback(entityManager);
           return e.getMessage();
        }
        return "Address successfully saved";
    }

    @Override
    public Optional<Address> findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Address address = null;
        try {
            entityManager.getTransaction().begin();
             address = entityManager.find(Address.class, id);
            entityManager.getTransaction().commit();
        }catch (Exception e){
           rollback(entityManager);
            System.err.println(e.getMessage());
        }
        return Optional.ofNullable(address);
    }

    private void rollback(EntityManager entityManager){
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
            entityManager.close();
        }
    }

    @Override
    public String updateById(Long aLong, Address type) {
        return null;
    }

    @Override
    public String deleteByID(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Address address = entityManager.find(Address.class, id);
            Company company = address.getCompany();
            company.setAddress(null);
            address.setCompany(null);
            entityManager.remove(address);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            rollback(entityManager);
            return e.getMessage();
        }
        return "Successfully deleted ";
    }

    @Override
    public List<Address> getAll() {
        return null;
    }

    public List<Address> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Address> resultAddresses = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
             resultAddresses = entityManager.createQuery("select a from Address  a", Address.class)
                    .getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            rollback(entityManager);
            System.err.println(e.getMessage());
        }
        return resultAddresses;
    }
}
