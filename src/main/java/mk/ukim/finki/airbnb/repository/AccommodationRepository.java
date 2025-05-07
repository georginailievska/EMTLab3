package mk.ukim.finki.airbnb.repository;

import mk.ukim.finki.airbnb.model.domain.Accommodation;
import mk.ukim.finki.airbnb.model.enumerations.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccommodationRepository extends JpaSpecificationRepository<Accommodation, Long> {

        @Query("""
        SELECT a FROM Accommodation a
        WHERE (:hostName IS NULL OR LOWER(a.host.name) LIKE LOWER(CONCAT('%', :hostName, '%')))
          AND (:hostSurname IS NULL OR LOWER(a.host.surname) LIKE LOWER(CONCAT('%', :hostSurname, '%')))
          AND (:category IS NULL OR a.category = :category)
          AND (:rented IS NULL OR a.rented = :rented)
          AND (:name IS NULL OR LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%')))
          AND (:numRooms IS NULL OR a.numRooms = :numRooms)
    """)
        List<Accommodation> filterAll(
                @Param("hostName") String hostName,
                @Param("hostSurname") String hostSurname,
                @Param("category") Category category,
                @Param("rented") Boolean rented,
                @Param("name") String name,
                @Param("numRooms") Integer numRooms
        );
}
