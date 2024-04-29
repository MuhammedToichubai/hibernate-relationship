package peaksoft.entities;

import jakarta.persistence.*;
import lombok.Getter;


/**
 * @author Mukhammed Asantegin
 */

@Getter
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_gen")
    private Long id;

    @Override
    public String toString() {
        return
               "id=" + id;
    }
}
