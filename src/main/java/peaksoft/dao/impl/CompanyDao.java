package peaksoft.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.DatabaseConnectionConfig;
import peaksoft.dao.CustomDao;
import peaksoft.entities.Address;
import peaksoft.entities.Company;

import java.util.List;
import java.util.Optional;

/**
 * @author Mukhammed Asantegin
 */
public class CompanyDao implements CustomDao<Company, Long> {
    private final EntityManagerFactory entityManagerFactory = DatabaseConnectionConfig.entityManagerFactory();
    @Override
    public String save(Company company) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(company);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return e.getMessage();
        }
        finally {
            entityManager.close();
        }
        return "Company successfully saved";
    }

    @Override
    public Optional<Company> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public String updateById(Long aLong, Company type) {
        return null;
    }

    @Override
    public String deleteByID(Long aLong) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Company.class, aLong));
//            int executed = entityManager.createQuery("delete from Company c where c.id = :id")
//                    .setParameter("id", aLong)
//                    .executeUpdate();

            entityManager.getTransaction().commit();

        }catch (Exception e){
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return e.getMessage();
        }
        return "Company with id : "+aLong+" successfully deleted";

    }

    @Override
    public List<Company> getAll() {
        return null;
    }

    public void assign(Long addressId, Long companyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            entityManager.getTransaction().begin();

            Address address = entityManager.find(Address.class, addressId);

            Company company = entityManager.createQuery("select c from Company c where c.id = :comId", Company.class)
                    .setParameter("comId", companyId).getSingleResult();

            address.setCompany(company);
            company.setAddress(address);

            entityManager.getTransaction().commit();
        }catch (Exception e){
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
                entityManager.close();
            }
            System.out.println(e.getMessage());
        }
    }
}
