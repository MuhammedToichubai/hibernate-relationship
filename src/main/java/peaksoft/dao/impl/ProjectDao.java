package peaksoft.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import peaksoft.config.DatabaseConnectionConfig;
import peaksoft.entities.Company;
import peaksoft.entities.Project;

/**
 * @author Mukhammed Asantegin
 */
public class ProjectDao {
    private final EntityManagerFactory entityManagerFactory = DatabaseConnectionConfig.entityManagerFactory();
    public String save(Project project){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(project);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            return  e.getMessage();
        }
        finally {
            entityManager.close();
        }
        return "Successfully saved";

    }

    public String save(Long companyId, Project project){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Company company = entityManager.createQuery("select c from Company c where c.id = :companyID", Company.class)
                    .setParameter("companyID", companyId)
                    .getSingleResult();
            company.addProject(project);
            project.setCompany(company);
            entityManager.merge(project);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            return e.getMessage();
        }finally {
            entityManager.close();
        }
        return "Successfully saved";
    }

}
