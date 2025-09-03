package co.com.microautenticacion.model.user.gateways;

import co.com.microautenticacion.model.user.User;
import reactor.core.publisher.Mono;

public interface UserRepository {

  Mono<User> createUser(User user);

  Mono<Boolean> verifyEmail (String email);

  Mono<Boolean>verifyUser(String email , String identification);
}
