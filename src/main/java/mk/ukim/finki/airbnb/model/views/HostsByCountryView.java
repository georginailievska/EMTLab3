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
@Subselect("SELECT * FROM hosts_by_country")
public class HostsByCountryView {

    @Id
    @Column(name = "country")
    private String country;

    @Column(name = "host_count")
    private Integer hostCount;
}
