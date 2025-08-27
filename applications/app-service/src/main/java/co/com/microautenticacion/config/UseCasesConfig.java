package co.com.microautenticacion.config;

import co.com.microautenticacion.model.user.gateways.UserRepository;
import co.com.microautenticacion.usecase.user.CreateUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "co.com.microautenticacion.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {

      /*  @Bean
        public CreateUserUseCase CreateUserUserCase (UserRepository userRepository){
                return new CreateUserUseCase(userRepository);
        }*/
}
