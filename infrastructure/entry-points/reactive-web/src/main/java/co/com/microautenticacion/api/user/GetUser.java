package co.com.microautenticacion.api.user;

import co.com.microautenticacion.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuarios")
public class GetUser {

  public Mono<List<User>> getAllUser(){
    return null;
  }
}
