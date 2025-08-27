package co.com.microautenticacion.config;

import co.com.microautenticacion.api.user.transformers.UserTransformer;
import co.com.microautenticacion.r2dbc.user.transformers.UserR2dbcTransformer;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransformersConfig {

@Bean
  public UserTransformer userTransformer(){
  return Mappers.getMapper(UserTransformer.class);
}

  @Bean
  public UserR2dbcTransformer userR2dbcTransformer(){
    return Mappers.getMapper(UserR2dbcTransformer.class);
  }
}

