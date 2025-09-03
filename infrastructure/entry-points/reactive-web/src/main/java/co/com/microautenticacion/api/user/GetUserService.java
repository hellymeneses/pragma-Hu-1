package co.com.microautenticacion.api.user;

import co.com.microautenticacion.model.common.constantes.Constants;
import co.com.microautenticacion.usecase.user.ObtenerUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuarios")
@Slf4j
public class GetUserService {

  private final ObtenerUserUseCase obtenerUserUseCase;

  @GetMapping("/{email}/{identification}")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Boolean> validarUsuarioExiste(@PathVariable("email") String email, @PathVariable("identification") String identification) {
    return obtenerUserUseCase.validarUsuarioExiste(email, identification)
            .doOnSuccess(result -> {
              if (Boolean.TRUE.equals(result)) {
                log.info(Constants.USER_VALIDO, identification);
              } else {
                log.warn(Constants.ERROR_USER, identification);
              }
            })
            .doOnError(ex -> log.error(Constants.ERROR_USER_VALID, ex.getMessage(), ex));
  }
}
