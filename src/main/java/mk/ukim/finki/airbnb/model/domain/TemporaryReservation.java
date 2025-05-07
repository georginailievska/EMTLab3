package mk.ukim.finki.airbnb.model.domain;

import jakarta.persistence.*;

@Entity
public class TemporaryReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Accommodation accommodation;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private Host host;

    private boolean confirmed;

    public TemporaryReservation(User user, Accommodation acc) {}

    public TemporaryReservation(User user, Accommodation accommodation, Host host) {
        this.user = user;
        this.accommodation = accommodation;
        this.host = host;
        this.confirmed = false;
    }

    public TemporaryReservation(User user, Accommodation accommodation, Host host, boolean confirmed) {
        this.user = user;
        this.accommodation = accommodation;
        this.host = host;
        this.confirmed = confirmed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
