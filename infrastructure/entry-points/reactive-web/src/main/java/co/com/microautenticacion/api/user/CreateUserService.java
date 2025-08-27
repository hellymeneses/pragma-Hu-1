package co.com.microautenticacion.api.user;

import co.com.microautenticacion.api.user.dto.UserDto;
import co.com.microautenticacion.api.user.transformers.UserTransformer;
import co.com.microautenticacion.model.user.User;
import co.com.microautenticacion.usecase.user.CreateUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuarios")
public class CreateUserService {
  private final CreateUserUseCase createUserUseCase;
  private final UserTransformer userTransformer;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<User> createUser(@Valid @RequestBody UserDto userDto) {
    return createUserUseCase.createUser(
            userTransformer.userDtoAUser(userDto));
  }
}
