package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Mukhammed Asantegin
 */
@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "id_gen", sequenceName = "project_seq", allocationSize = 1)
public class Project extends BaseEntity{
    private String title;
    private LocalDate startDate;
    @ManyToOne
    private Company company;
    @ManyToMany
    private List<Programmer> programmers;


    @PrePersist
    private void saveDate(){
        this.startDate = LocalDate.now();
    }

    @PreUpdate
    private void updateDate(){
        this.startDate = LocalDate.now();
    }

}
