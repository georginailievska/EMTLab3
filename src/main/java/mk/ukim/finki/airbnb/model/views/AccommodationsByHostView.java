package mk.ukim.finki.airbnb.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Immutable
@Subselect("SELECT * FROM accommodations_by_host")
public class AccommodationsByHostView {

    @Id
    @Column(name = "host_id")
    private Long hostId;

    @Column(name = "name")
    private String name;

    @Column(name = "accommodation_count")
    private Integer accommodationCount;
}

