package co.com.microautenticacion.r2dbc.user.transformers;

import co.com.microautenticacion.model.user.User;
import co.com.microautenticacion.r2dbc.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserR2dbcTransformer {

  User toEntity (UserDto userDto);

  UserDto toDto(User user);

}
