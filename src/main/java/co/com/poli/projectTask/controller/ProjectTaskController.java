package co.com.poli.projectTask.controller;

import co.com.poli.projectTask.domain.ProjectTask;
import co.com.poli.projectTask.model.ErrorMessage;
import co.com.poli.projectTask.service.ProjectTaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/task")
public class ProjectTaskController {
    private final ProjectTaskService projectTaskService;

    public ProjectTaskController(ProjectTaskService projectTaskService) {
        this.projectTaskService = projectTaskService;
    }

    @PostMapping
    public ResponseEntity<ProjectTask> createTask(@Valid @RequestBody ProjectTask project, BindingResult result){

        if ( result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));

        }
        ProjectTask projectBD = this.projectTaskService.createTask(project);
        if(projectBD == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(projectBD);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProjectTask> getTask(@PathVariable("id") Long id) {
        System.out.println(id);
        ProjectTask TaskBD  = projectTaskService.getTask(id);
        if (null == TaskBD) {
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(TaskBD);
    }

    @GetMapping(value = "/project/{projectIdentifier}")
    public ResponseEntity<List<ProjectTask>> listTaskById(@PathVariable("projectIdentifier") String  projectIdentifier) {
        List<ProjectTask> tasks = projectTaskService.findAllByProjectIdentifier(projectIdentifier);
        if (tasks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tasks);
    }

    @GetMapping(value = "/hours/project/{projectIdentifier}")
    public ResponseEntity<Double> getHoursByProject(@PathVariable("projectIdentifier") String  projectIdentifier) {
        List<ProjectTask> tasks = projectTaskService.findAllByProjectIdentifier(projectIdentifier);
        Double hours = tasks.stream().filter(value -> !value.getStatus().equalsIgnoreCase("deleted")).mapToDouble(val -> val.getHours()).sum();
        System.out.println(hours);
        return ResponseEntity.ok(hours);
    }

    @GetMapping(value = "/hours/project/{projectIdentifier}/{status}")
    public ResponseEntity<Double> getHoursByProject(@PathVariable("projectIdentifier") String  projectIdentifier, @PathVariable("status") String  status) {
        List<ProjectTask> tasks = projectTaskService.findAllByProjectIdentifier(projectIdentifier);
        Double hours = tasks.stream().filter(value -> value.getStatus().equalsIgnoreCase(status)).mapToDouble(val -> val.getHours()).sum();
        System.out.println(hours);
        return ResponseEntity.ok(hours);
    }

    @DeleteMapping(value = "/{idtask}/{projectIdentifier}")
    public ResponseEntity<ProjectTask> deleteProduct(@PathVariable("idtask") Long idtask, @PathVariable("projectIdentifier") String projectIdentifier){
        ProjectTask productBD = projectTaskService.deleteTask(idtask,projectIdentifier);
        if (productBD == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productBD);
    }

    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String> error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
