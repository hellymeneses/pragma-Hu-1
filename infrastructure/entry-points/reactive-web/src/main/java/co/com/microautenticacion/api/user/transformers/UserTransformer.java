package co.com.microautenticacion.api.user.transformers;

import co.com.microautenticacion.api.user.dto.UserDto;
import co.com.microautenticacion.model.user.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserTransformer {

User userDtoAUser (UserDto userDto);
}
