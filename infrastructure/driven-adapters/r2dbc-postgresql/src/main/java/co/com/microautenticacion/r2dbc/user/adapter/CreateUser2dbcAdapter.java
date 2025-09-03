package co.com.microautenticacion.r2dbc.user.adapter;

import co.com.microautenticacion.model.common.constantes.Constants;
import co.com.microautenticacion.model.user.User;
import co.com.microautenticacion.model.user.gateways.UserRepository;
import co.com.microautenticacion.r2dbc.user.transformers.UserR2dbcTransformer;
import co.com.microautenticacion.r2dbc.user.dao.UserR2dbcRepository;
import co.com.microautenticacion.r2dbc.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@AllArgsConstructor
public class CreateUser2dbcAdapter implements UserRepository {

  private final UserR2dbcRepository userR2dbcRepository;
  private final UserR2dbcTransformer userR2dbcTransformer;
  private final TransactionalOperator transactionalOperator;


  public Mono<User> createUser(User user) {
    UserDto dto = userR2dbcTransformer.toDto(user);
    return userR2dbcRepository.save(dto)
            .doOnSuccess(savedDto -> log.info(Constants.SUCCESS_SAVE, savedDto.getId()))
            .doOnError(ex -> log.error(Constants.ERROR_SAVE, ex))
            .map(userR2dbcTransformer::toEntity)
            .as(transactionalOperator::transactional);
  }

  public Mono<Boolean> verifyEmail(String email) {
    log.info(Constants.VERIFY_EMAIL, email);
    return userR2dbcRepository.existsUserDtoByEmail(email);
  }

  @Override
  public Mono<Boolean> verifyUser(String email, String identification) {
    log.info(Constants.VERIFY_USER, identification, email);
    return userR2dbcRepository.existsUserDtoByEmailAndIdentification(email, identification);
  }
}
