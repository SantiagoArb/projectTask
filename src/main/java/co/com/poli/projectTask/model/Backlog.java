package co.com.poli.projectTask.model;

import co.com.poli.projectTask.domain.ProjectTask;
import lombok.Data;

import java.util.List;

@Data
public class Backlog {
    private Long id;
    private String projectIdentifier;
    private Project project;
    private List<ProjectTask> projectTask;
}
