package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mukhammed Asantegin
 */
@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "id_gen", sequenceName = "company_seq", allocationSize = 1)
public class Company extends BaseEntity{
    private String name;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Address address;

    @OneToMany(mappedBy = "company")
    private List<Project> projects;

    public Company(String name) {
        this.name = name;
    }


    public void addProject(Project project) {
        if (this.projects == null) this.projects = new ArrayList<>();
        this.projects.add(project);
    }
}
