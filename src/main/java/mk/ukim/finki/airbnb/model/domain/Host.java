package mk.ukim.finki.airbnb.model.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@NamedEntityGraph(
        name = "Host.withoutTemporaryReservations",
        attributeNodes = {
                @NamedAttributeNode("id"),
                @NamedAttributeNode("name"),
                @NamedAttributeNode("surname"),
                @NamedAttributeNode("country")
        }
)
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @ManyToOne
    private Country country;

    // Ensure temporaryReservations are excluded
    @OneToMany(mappedBy = "host", fetch = FetchType.LAZY)
    private List<TemporaryReservation> temporaryReservations;

    public Host() {}

    public Host(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Country getCountry() {
        return country;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
