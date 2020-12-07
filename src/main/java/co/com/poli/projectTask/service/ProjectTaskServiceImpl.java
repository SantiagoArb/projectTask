package co.com.poli.projectTask.service;

import co.com.poli.projectTask.domain.ProjectTask;
import co.com.poli.projectTask.repository.ProjectTaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskServiceImpl implements ProjectTaskService{

    private final ProjectTaskRepository taskRepository;

    public ProjectTaskServiceImpl(ProjectTaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<ProjectTask> getAllTask() {
        return this.taskRepository.findAll();
    }

    @Override
    public ProjectTask getTask(Long id) {
        return this.taskRepository.findById(id).orElse(null);
    }

    @Override
    public ProjectTask createTask(ProjectTask task) {
        ProjectTask taskBD = getTaskByProjectIdentifier(task.getProjectIdentifier());
        if(taskBD != null){
            return null;
        }
        return this.taskRepository.save(task);
    }

    @Override
    public ProjectTask updateTask(ProjectTask task) {

        ProjectTask taskBD = getTask(task.getId());
        if(taskBD == null){
            return null;
        }
        task.setId(taskBD.getId());
        return this.taskRepository.save(task);
    }

    @Override
    public ProjectTask deleteTask(Long id, String projectIdentifier) {
        ProjectTask taskBD = getTask(id);
        if(taskBD==null) {
            return null;
        }
        if(taskBD.getProjectIdentifier().equalsIgnoreCase(projectIdentifier)){
            taskBD.setStatus("DELETED");
            return taskRepository.save(taskBD);
        }else {
            return null;
        }
    }

    @Override
    public ProjectTask getTaskByProjectIdentifier(String projectIdentifier) {
        return this.taskRepository.findByProjectIdentifier(projectIdentifier);
    }
    @Override
    public List<ProjectTask> findAllByProjectIdentifier(String projectIdentifier) {
        return this.taskRepository.findAllByProjectIdentifier(projectIdentifier);
    }
}
