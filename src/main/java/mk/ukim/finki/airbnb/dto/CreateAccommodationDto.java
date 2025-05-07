package mk.ukim.finki.airbnb.dto;

import mk.ukim.finki.airbnb.model.domain.Accommodation;
import mk.ukim.finki.airbnb.model.domain.Host;
import mk.ukim.finki.airbnb.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record CreateAccommodationDto(
        String name,
        Category category,
        Integer numRooms,
        Long hostId,
        boolean rented
) {

    public static CreateAccommodationDto from(Accommodation accommodation) {
        return new CreateAccommodationDto(
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getNumRooms(),
                accommodation.getHost().getId(),
                accommodation.isRented()
        );
    }

    public static List<CreateAccommodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(CreateAccommodationDto::from).collect(Collectors.toList());
    }

    public Accommodation toAccommodation(Host host) {
        return new Accommodation(name, category, host, numRooms, rented);
    }
}
