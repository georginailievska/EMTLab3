package mk.ukim.finki.airbnb.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.airbnb.dto.DisplayTemporaryReservationDto;
import mk.ukim.finki.airbnb.service.application.TemporaryReservationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class TemporaryReservationController {

    private final TemporaryReservationApplicationService reservationService;

    public TemporaryReservationController(TemporaryReservationApplicationService reservationService) {
        this.reservationService = reservationService;
    }

    @Operation(summary = "Add accommodation to temporary reservation list")
    @PostMapping("/temp/add/{accommodationId}")
    public ResponseEntity<Void> add(@PathVariable Long accommodationId, Principal principal) {
        reservationService.addToTempList(accommodationId, principal.getName());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "View temporary reservation list")
    @GetMapping("/temp")
    public ResponseEntity<List<DisplayTemporaryReservationDto>> view(Principal principal) {
        return ResponseEntity.ok(reservationService.viewTempList(principal.getName()));
    }

    @Operation(summary = "Confirm all temporary reservations")
    @PostMapping("/confirm")
    public ResponseEntity<Void> confirmAll(Principal principal) {
        reservationService.confirmAll(principal.getName());
        return ResponseEntity.ok().build();
    }
}
