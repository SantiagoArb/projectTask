package co.com.poli.projectTask.service;

import co.com.poli.projectTask.domain.ProjectTask;

import java.util.List;

public interface ProjectTaskService {
    List<ProjectTask> getAllTask();
    ProjectTask getTask(Long id);
    ProjectTask createTask(ProjectTask task);
    ProjectTask updateTask(ProjectTask task);
    ProjectTask deleteTask(Long id, String projectIdentifier);
    ProjectTask getTaskByProjectIdentifier(String projectIdentifier);
    List<ProjectTask> findAllByProjectIdentifier(String projectIdentifier);
}
