package co.com.microautenticacion.api.user;

import co.com.microautenticacion.api.user.dto.UserDto;
import co.com.microautenticacion.api.user.transformers.UserTransformer;
import co.com.microautenticacion.model.user.User;
import co.com.microautenticacion.usecase.user.CreateUserUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;


class CreateUserServiceTest {


  private CreateUserUseCase createUserUseCase;
  private UserTransformer userTransformer;
  private CreateUserService createUserService;

  private WebTestClient webTestClient;


  @BeforeEach
  void setUp() {
    createUserUseCase = Mockito.mock(CreateUserUseCase.class);
    userTransformer = Mockito.mock(UserTransformer.class);
    createUserService = new CreateUserService(createUserUseCase, userTransformer);

    webTestClient = WebTestClient.bindToController(createUserService).build();
  }

  @Test
  void createUserShouldReturnCreatedUser() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(1990, Calendar.JANUARY, 1);
    Date birthDate = calendar.getTime();

    UserDto userDto = UserDto.builder()
            .identification("1234567890")
            .firstName("Juan")
            .lastName("Pérez")
            .email("juan.perez@example.com")
            .address("Calle  123")
            .phoneNumber("3001234567")
            .dateOfBirth(birthDate)
            .baseSalary(new BigDecimal("5000000"))
            .build();

    User user = User.builder()
            .identification("1234567890")
            .firstName("Juan")
            .lastName("Pérez")
            .email("juan.perez@example.com")
            .address("Calle  123")
            .phoneNumber("3001234567")
            .dateOfBirth(birthDate)
            .baseSalary(new BigDecimal("5000000"))
            .build();

    Mockito.when(userTransformer.userDtoAUser(any(UserDto.class))).thenReturn(user);

    Mockito.when(createUserUseCase.createUser(any(User.class))).thenReturn(Mono.just(user));

    webTestClient.post()
            .uri("/api/v1/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(userDto)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(User.class)
            .consumeWith(response -> {
              User responseBody = response.getResponseBody();
              Assertions.assertEquals(user.getIdentification(), responseBody.getIdentification());
              Assertions.assertEquals(user.getFirstName(), responseBody.getFirstName());
              Assertions.assertEquals(user.getLastName(), responseBody.getLastName());
              Assertions.assertEquals(user.getEmail(), responseBody.getEmail());
              Assertions.assertEquals(user.getAddress(), responseBody.getAddress());
              Assertions.assertEquals(user.getPhoneNumber(), responseBody.getPhoneNumber());
              Assertions.assertEquals(user.getBaseSalary(), responseBody.getBaseSalary());
              Assertions.assertEquals(user.getDateOfBirth(), responseBody.getDateOfBirth());
            });
  }
}