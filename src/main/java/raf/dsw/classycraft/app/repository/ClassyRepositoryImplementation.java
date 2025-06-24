package raf.dsw.classycraft.app.repository;

import lombok.Getter;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

@Getter
public class ClassyRepositoryImplementation implements ClassyRepository {

    private ProjectExplorer projectExplorer;

    public ClassyRepositoryImplementation() {
        projectExplorer = new ProjectExplorer("Project Explorer");
    }

    @Override
    public ProjectExplorer getRoot() {
        return projectExplorer;
    }
}
