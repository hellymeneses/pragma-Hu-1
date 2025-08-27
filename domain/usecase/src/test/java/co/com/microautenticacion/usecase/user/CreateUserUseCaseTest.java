package co.com.microautenticacion.usecase.user;

import co.com.microautenticacion.model.common.exception.EmailAlreadyExistsException;
import co.com.microautenticacion.model.user.User;
import co.com.microautenticacion.model.user.gateways.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


class CreateUserUseCaseTest {

  private UserRepository userRepository;
  private CreateUserUseCase createUserUseCase;

  @BeforeEach
  void setup() {
    userRepository = Mockito.mock(UserRepository.class);
    createUserUseCase = new CreateUserUseCase(userRepository);
  }

  @Test
  void createUserWhenEmailNotExistsShouldCreateUser() {
    User user = new User();
    user.setEmail("test@example.com");

    Mockito.when(userRepository.verifyEmail(user.getEmail())).thenReturn(Mono.just(false));
    Mockito.when(userRepository.createUser(user)).thenReturn(Mono.just(user));

    Mono<User> result = createUserUseCase.createUser(user);

    StepVerifier.create(result)
            .expectNext(user)
            .verifyComplete();

    Mockito.verify(userRepository).verifyEmail(user.getEmail());
    Mockito.verify(userRepository).createUser(user);
  }

  @Test
  void createUserwhenEmailExistsShouldThrowException() {
    User user = new User();
    user.setEmail("existing@example.com");

    Mockito.when(userRepository.verifyEmail(user.getEmail())).thenReturn(Mono.just(true));
    Mono<User> result = createUserUseCase.createUser(user);

    StepVerifier.create(result)
            .expectError(EmailAlreadyExistsException.class)
            .verify();

    Mockito.verify(userRepository).verifyEmail(user.getEmail());
    Mockito.verify(userRepository, Mockito.never()).createUser(Mockito.any());
  }
}