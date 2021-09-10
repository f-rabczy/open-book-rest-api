package pl.rabczynski.openbook.user.mapper;

import org.mapstruct.Mapper;
import pl.rabczynski.openbook.user.domain.UserEntity;
import pl.rabczynski.openbook.user.dto.UserDTO;
import pl.rabczynski.openbook.user.dto.request.UserCreateRequest;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity userCreateRequestToUserEntity(UserCreateRequest source);
    UserDTO userEntityToUserDTO(UserEntity source);
}
