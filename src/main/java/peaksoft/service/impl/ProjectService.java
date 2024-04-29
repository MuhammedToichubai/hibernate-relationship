package peaksoft.service.impl;

import peaksoft.dao.impl.ProjectDao;
import peaksoft.entities.Project;

/**
 * @author Mukhammed Asantegin
 */
public class ProjectService {
    private final ProjectDao projectDao = new ProjectDao();

    public String save(Project project){
        return projectDao.save(project);
    }
    public String save(Long cId, Project project){
        return projectDao.save(cId, project);
    }

}
