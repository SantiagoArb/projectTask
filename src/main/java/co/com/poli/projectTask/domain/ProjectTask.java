package co.com.poli.projectTask.domain;

import co.com.poli.projectTask.model.Backlog;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "tbl_projecttask")
public class ProjectTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "El nombre no debe ser vacio")
    private String name;
    @NotEmpty(message = "El nombre no debe ser vacio")
    private String summary;
    private String acceptanceCriteria;
    private String status;
    @Range(min = 1, max = 5)
    private int priority;
    @Range(min = 1, max = 8)
    private Double hours;
    private Date startDate;
    private Date endDate;
    private String projectIdentifier;
    @Column(name = "backlog_id")
    private Long backlogId;
    @Transient
    private Backlog backlog;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectTask projectTask = (ProjectTask) o;
        return Objects.equals(id, projectTask.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
