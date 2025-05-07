package mk.ukim.finki.airbnb.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.airbnb.service.application.CountryApplicationService;
import mk.ukim.finki.airbnb.dto.CreateCountryDto;
import mk.ukim.finki.airbnb.dto.DisplayCountryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryApplicationService countryService;

    public CountryController(CountryApplicationService countryService) {
        this.countryService = countryService;
    }

    @Operation(summary = "Get all countries", description = "Returns all countries.")
    @GetMapping
    public ResponseEntity<List<DisplayCountryDto>> getAll() {
        return ResponseEntity.ok(countryService.getAll());
    }

    @Operation(summary = "Get country by ID")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayCountryDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(countryService.getById(id));
    }

    @Operation(summary = "Create new country", description = "Creates a new country with name and continent.")
    @PostMapping
    public ResponseEntity<DisplayCountryDto> create(@RequestBody CreateCountryDto dto) {
        return ResponseEntity.ok(countryService.create(dto));
    }

    @Operation(summary = "Update country")
    @PutMapping("/{id}")
    public ResponseEntity<DisplayCountryDto> update(@PathVariable Long id, @RequestBody CreateCountryDto dto) {
        return ResponseEntity.ok(countryService.update(id, dto));
    }

    @Operation(summary = "Delete country", description = "Deletes a country by ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        countryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
