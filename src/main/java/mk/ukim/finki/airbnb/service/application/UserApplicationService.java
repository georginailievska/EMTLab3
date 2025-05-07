package mk.ukim.finki.airbnb.service.application;

import mk.ukim.finki.airbnb.dto.CreateUserDto;
import mk.ukim.finki.airbnb.dto.DisplayUserDto;
import mk.ukim.finki.airbnb.dto.LoginResponseDto;
import mk.ukim.finki.airbnb.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);
    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);
    Optional<DisplayUserDto> findByUsername(String username);
}
