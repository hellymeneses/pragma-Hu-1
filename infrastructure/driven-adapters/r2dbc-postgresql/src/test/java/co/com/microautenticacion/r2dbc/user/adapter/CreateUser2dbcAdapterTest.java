package co.com.microautenticacion.r2dbc.user.adapter;

import co.com.microautenticacion.model.user.User;
import co.com.microautenticacion.r2dbc.user.dao.UserR2dbcRepository;
import co.com.microautenticacion.r2dbc.user.dto.UserDto;
import co.com.microautenticacion.r2dbc.user.transformers.UserR2dbcTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.mockito.ArgumentMatchers.any;


import java.math.BigDecimal;



@ExtendWith(MockitoExtension.class)
class CreateUser2dbcAdapterTest {
  @Mock
  private UserR2dbcRepository userR2dbcRepository;

  @Mock
  private UserR2dbcTransformer userR2dbcTransformer;

  @Mock
  private TransactionalOperator transactionalOperator;

  @InjectMocks
  private CreateUser2dbcAdapter createUser2dbcAdapter;

  private User user;
  private UserDto userDto;
  private User transformedUser;

  @BeforeEach
  void setup() {
    user = new User();
    user.setId(1L);
    user.setIdentification("123456789");
    user.setFirstName("Juan");
    user.setLastName("Pérez");
    user.setDateOfBirth(java.sql.Date.valueOf("1990-05-15"));
    user.setAddress("Calle Falsa 123");
    user.setPhoneNumber("3001234567");
    user.setEmail("juan@example.com");
    user.setBaseSalary(new BigDecimal("2500000"));

    userDto = new UserDto();
    userDto.setId(1L);
    userDto.setIdentification("123456789");
    userDto.setFirstName("Juan");
    userDto.setLastName("Pérez");
    userDto.setDateOfBirth(java.sql.Date.valueOf("1990-05-15"));
    userDto.setAddress("Calle Falsa 123");
    userDto.setPhoneNumber("3001234567");
    userDto.setEmail("juan@example.com");
    userDto.setBaseSalary(new BigDecimal("2500000"));

    transformedUser = new User();
    transformedUser.setId(1L);
    transformedUser.setIdentification("123456789");
    transformedUser.setFirstName("Juan");
    transformedUser.setLastName("Pérez");
    transformedUser.setDateOfBirth(java.sql.Date.valueOf("1990-05-15"));
    transformedUser.setAddress("Calle Falsa 123");
    transformedUser.setPhoneNumber("3001234567");
    transformedUser.setEmail("juan@example.com");
    transformedUser.setBaseSalary(new BigDecimal("2500000"));
  }

  @Test
  void createUserShouldSaveUserAndReturnUser() {
    Mockito.when(userR2dbcTransformer.toDto(user)).thenReturn(userDto);
    Mockito.when(userR2dbcRepository.save(any(UserDto.class))).thenReturn(Mono.just(userDto));
    Mockito.when(userR2dbcTransformer.toEntity(userDto)).thenReturn(transformedUser);
    Mockito.when(transactionalOperator.transactional(any(Mono.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

    Mono<User> result = createUser2dbcAdapter.createUser(user);

    StepVerifier.create(result)
            .expectNext(transformedUser)
            .verifyComplete();

    Mockito.verify(userR2dbcTransformer).toDto(user);
    Mockito.verify(userR2dbcRepository).save(any(UserDto.class));
    Mockito.verify(userR2dbcTransformer).toEntity(userDto);
    Mockito.verify(transactionalOperator).transactional(any(Mono.class));
  }

  @Test
  void verifyEmailShouldReturnTrueOrFalse() {
    String email = "test@example.com";

    Mockito.when(userR2dbcRepository.existsUserDtoByEmail(email)).thenReturn(Mono.just(true));

    Mono<Boolean> result = createUser2dbcAdapter.verifyEmail(email);

    StepVerifier.create(result)
            .expectNext(true)
            .verifyComplete();

    Mockito.verify(userR2dbcRepository).existsUserDtoByEmail(email);
  }
}