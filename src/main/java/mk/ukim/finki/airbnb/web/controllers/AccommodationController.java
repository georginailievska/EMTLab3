package mk.ukim.finki.airbnb.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.airbnb.model.enumerations.Category;
import mk.ukim.finki.airbnb.service.application.AccommodationApplicationService;
import mk.ukim.finki.airbnb.dto.CreateAccommodationDto;
import mk.ukim.finki.airbnb.dto.DisplayAccommodationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    private final AccommodationApplicationService accommodationService;

    public AccommodationController(AccommodationApplicationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @Operation(summary = "Get all accommodations", description = "Returns a list of all available accommodations.")
    @GetMapping
    public ResponseEntity<List<DisplayAccommodationDto>> getAll() {
        return ResponseEntity.ok(accommodationService.getAll());
    }

    @Operation(summary = "Get accommodation by ID")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayAccommodationDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(accommodationService.getById(id));
    }

    @Operation(summary = "Create new accommodation", description = "Creates a new accommodation with the provided data.")
    @PostMapping
    public ResponseEntity<DisplayAccommodationDto> create(@RequestBody CreateAccommodationDto dto) {
        return ResponseEntity.ok(accommodationService.create(dto));
    }

    @Operation(summary = "Update accommodation", description = "Updates the fields of an existing accommodation.")
    @PutMapping("/{id}")
    public ResponseEntity<DisplayAccommodationDto> update(@PathVariable Long id, @RequestBody CreateAccommodationDto dto) {
        return ResponseEntity.ok(accommodationService.update(id, dto));
    }

    @Operation(summary = "Delete accommodation", description = "Deletes an accommodation by its ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        accommodationService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter")
    public List<DisplayAccommodationDto> filter(
            @RequestParam(required = false) String hostName,
            @RequestParam(required = false) String hostSurname,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Boolean rented,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer numRooms
    ) {
        return accommodationService.filter(
                hostName,
                hostSurname,
                category,
                rented,
                name,
                numRooms
        );
    }
}
