package co.com.poli.projectTask.repository;

import co.com.poli.projectTask.domain.ProjectTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTaskRepository extends JpaRepository<ProjectTask,Long> {
    ProjectTask findByProjectIdentifier(String projectIdentifier);
    List<ProjectTask> findAllByProjectIdentifier(String projectIdentifier);

}
