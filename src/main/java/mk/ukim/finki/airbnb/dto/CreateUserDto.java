package mk.ukim.finki.airbnb.dto;

import mk.ukim.finki.airbnb.model.domain.User;
import mk.ukim.finki.airbnb.model.enumerations.Role;

public record CreateUserDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role
) {
    public User toUser() {
        return new User(username, password, name, surname, role);
    }
}
