package mk.ukim.finki.airbnb.repository;

import mk.ukim.finki.airbnb.model.domain.Host;
import mk.ukim.finki.airbnb.model.projections.HostNameProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HostRepository extends JpaSpecificationRepository<Host, Long> {

    @Query("SELECT h FROM Host h")
    List<HostNameProjection> findAllHostNames();

    @EntityGraph(value = "Host.withoutTemporaryReservations", type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT h FROM Host h")
    List<Host> findAllWithoutTemporaryReservations();
}
