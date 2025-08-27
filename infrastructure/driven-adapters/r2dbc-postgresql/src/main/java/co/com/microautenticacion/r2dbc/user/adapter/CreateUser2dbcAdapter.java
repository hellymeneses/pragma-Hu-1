package co.com.microautenticacion.r2dbc.user.adapter;

import co.com.microautenticacion.model.user.User;
import co.com.microautenticacion.model.user.gateways.UserRepository;
import co.com.microautenticacion.r2dbc.user.transformers.UserR2dbcTransformer;
import co.com.microautenticacion.r2dbc.user.dao.UserR2dbcRepository;
import co.com.microautenticacion.r2dbc.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class CreateUser2dbcAdapter implements UserRepository {

  private final UserR2dbcRepository userR2dbcRepository;
  private final UserR2dbcTransformer userR2dbcTransformer;
  private final TransactionalOperator transactionalOperator;


  public Mono<User> createUser(User user) {
    UserDto dto = userR2dbcTransformer.toDto(user);
    return userR2dbcRepository.save(dto)
            .map(userR2dbcTransformer::toEntity)
            .as(transactionalOperator::transactional);
  }

  public Mono<Boolean> verifyEmail(String email) {
    return userR2dbcRepository.existsUserDtoByEmail(email);
  }
}
