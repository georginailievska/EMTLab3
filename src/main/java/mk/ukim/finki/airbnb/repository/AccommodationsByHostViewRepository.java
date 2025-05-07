package mk.ukim.finki.airbnb.repository;

import jakarta.transaction.Transactional;
import mk.ukim.finki.airbnb.model.views.AccommodationsByHostView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationsByHostViewRepository extends JpaRepository<AccommodationsByHostView, Long> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "REFRESH MATERIALIZED VIEW accommodations_by_host", nativeQuery = true)
    void refreshMaterializedView();

}
