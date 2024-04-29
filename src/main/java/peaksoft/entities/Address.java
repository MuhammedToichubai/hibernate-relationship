package peaksoft.entities;

import jakarta.persistence.*;

import lombok.*;

import static jakarta.persistence.CascadeType.*;

/**
 * @author Mukhammed Asantegin
 */

@Entity
@Table(name = "addresses")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "id_gen", sequenceName = "address_seq", allocationSize = 1)
public class Address extends BaseEntity{
    private String country;
    private String city;
    @OneToOne(mappedBy = "address", cascade = {PERSIST}, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Company company;

    @Override
    public String toString() {
        return "Address{" +
               ",idd='" + super.getId() + '\'' +
               "country='" + country + '\'' +
               ", city='" + city + '\'' +
               '}';
    }
}
