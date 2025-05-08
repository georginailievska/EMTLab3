package mk.ukim.finki.airbnb.config.init;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.airbnb.model.domain.Accommodation;
import mk.ukim.finki.airbnb.model.domain.Country;
import mk.ukim.finki.airbnb.model.domain.Host;
import mk.ukim.finki.airbnb.model.domain.User;
import mk.ukim.finki.airbnb.model.enumerations.Category;
import mk.ukim.finki.airbnb.model.enumerations.Role;
import mk.ukim.finki.airbnb.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {

    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final AccommodationRepository accommodationRepository;
    private final UserRepository userRepository;
    private final HostsByCountryViewRepository hostsByCountryViewRepository;
    private final AccommodationsByHostViewRepository accommodationsByHostViewRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            CountryRepository countryRepository,
            HostRepository hostRepository,
            AccommodationRepository accommodationRepository,
            UserRepository userRepository,
            HostsByCountryViewRepository hostsByCountryViewRepository,
            AccommodationsByHostViewRepository accommodationsByHostViewRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.accommodationRepository = accommodationRepository;
        this.userRepository = userRepository;
        this.hostsByCountryViewRepository = hostsByCountryViewRepository;
        this.accommodationsByHostViewRepository = accommodationsByHostViewRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        Country macedonia = new Country("Macedonia", "Europe");
        Country usa = new Country("USA", "North America");
        countryRepository.saveAll(List.of(macedonia, usa));

        Host host1 = new Host("Georgina", "Ilievska", macedonia);
        Host host2 = new Host("John", "Smith", usa);
        hostRepository.saveAll(List.of(host1, host2));

        Accommodation accommodation1 = new Accommodation("Luxury Apartment", Category.APARTMENT, host1, 3, false);
        Accommodation accommodation2 = new Accommodation("Beach House", Category.HOUSE, host2, 5, true);
        accommodationRepository.saveAll(List.of(accommodation1, accommodation2));

        User user = new User("user", passwordEncoder.encode("user"), "user", "user", Role.ROLE_USER);
        User admin = new User("admin", passwordEncoder.encode("admin"), "admin", "admin", Role.ROLE_ADMIN);
        userRepository.saveAll(List.of(user, admin));

        // Refresh materialized views after data insertion
        hostsByCountryViewRepository.refreshView();
        accommodationsByHostViewRepository.refreshMaterializedView();
    }
}

