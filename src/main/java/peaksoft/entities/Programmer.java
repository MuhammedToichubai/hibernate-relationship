package peaksoft.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

/**
 * @author Mukhammed Asantegin
 */
@Entity
@Table(name = "programmers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "id_gen", sequenceName = "dev_seq", allocationSize = 1)
public class Programmer extends BaseEntity{
    private String fullName;
    private String email;
    @ManyToMany(mappedBy = "programmers")
    private List<Project> projects;

}
