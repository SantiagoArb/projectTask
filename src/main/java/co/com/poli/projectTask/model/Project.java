package co.com.poli.projectTask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class Project {
    private Long id;
    private String name;
    private String identifier;
    private String description;
    private Date startDate;
    private Date endDate;
    private String backlog;
}
