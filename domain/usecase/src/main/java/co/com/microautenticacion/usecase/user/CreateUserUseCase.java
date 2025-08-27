package co.com.microautenticacion.usecase.user;

import co.com.microautenticacion.model.common.constantes.MessageConstants;
import co.com.microautenticacion.model.common.exception.EmailAlreadyExistsException;
import co.com.microautenticacion.model.user.User;
import co.com.microautenticacion.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

public class CreateUserUseCase {

  private final UserRepository userRepository;

  public CreateUserUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Mono<User> createUser(User user) {
    return validateEmail(user)
            .flatMap(userRepository::createUser);
  }

  protected Mono<User> validateEmail(User user) {

    return userRepository.verifyEmail(user.getEmail())
            .flatMap(emailExists -> {
              if (emailExists) {
                return Mono.error(new EmailAlreadyExistsException(MessageConstants.ERROR_EMAIL_DUPLICATE));
              } else {
                return Mono.just(user);
              }
            });
  }
}

