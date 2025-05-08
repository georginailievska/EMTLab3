package mk.ukim.finki.airbnb.dto;

import mk.ukim.finki.airbnb.model.domain.Accommodation;
import mk.ukim.finki.airbnb.model.domain.Host;
import mk.ukim.finki.airbnb.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAccommodationDto(
        Long id,
        String name,
        Category category,
        Integer numRooms,
        boolean rented,
        Long hostId,
        String hostName,
        String hostSurname,
        String hostCountryName,
        String hostCountryContinent
) {
    public static mk.ukim.finki.airbnb.dto.DisplayAccommodationDto from(Accommodation accommodation) {
        return new mk.ukim.finki.airbnb.dto.DisplayAccommodationDto(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getNumRooms(),
                accommodation.isRented(),
                accommodation.getHost().getId(),
                accommodation.getHost().getName(),
                accommodation.getHost().getSurname(),
                accommodation.getHost().getCountry().getName(),
                accommodation.getHost().getCountry().getContinent()
        );
    }

    public static List<DisplayAccommodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(DisplayAccommodationDto::from).collect(Collectors.toList());
    }

    public Accommodation toAccommodation(Host host) {
        return new Accommodation(name, category, host, numRooms, rented);
    }
}
