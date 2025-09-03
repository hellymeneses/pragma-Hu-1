package co.com.microautenticacion.usecase.user;

import co.com.microautenticacion.model.user.gateways.UserRepository;
import reactor.core.publisher.Mono;

public class ObtenerUserUseCase {

  private UserRepository userRepository;

  public ObtenerUserUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Mono<Boolean>validarUsuarioExiste(String email, String identification){
    return userRepository.verifyUser(email,identification);
  }
}
