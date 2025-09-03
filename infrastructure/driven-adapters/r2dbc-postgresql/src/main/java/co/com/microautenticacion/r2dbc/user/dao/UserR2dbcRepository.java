package co.com.microautenticacion.r2dbc.user.dao;

import co.com.microautenticacion.r2dbc.user.dto.UserDto;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserR2dbcRepository  extends R2dbcRepository<UserDto , String> {


  Mono<Boolean>existsUserDtoByEmail(String Email);

  Mono<Boolean>existsUserDtoByEmailAndIdentification(String Email, String identification);

}
