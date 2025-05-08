package mk.ukim.finki.airbnb.repository;

import jakarta.transaction.Transactional;
import mk.ukim.finki.airbnb.model.views.HostsByCountryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HostsByCountryViewRepository extends JpaRepository<HostsByCountryView, String> {

    @Modifying
    @Transactional
    @Query(value = "REFRESH MATERIALIZED VIEW hosts_by_country;", nativeQuery = true)
    void refreshView();
}

