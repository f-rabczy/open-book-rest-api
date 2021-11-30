package pl.rabczynski.openbook.user.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.rabczynski.openbook.auth.domain.enums.Role;
import pl.rabczynski.openbook.user.domain.UserEntity;
import pl.rabczynski.openbook.user.domain.UserRoleEntity;
import pl.rabczynski.openbook.user.dto.UserDTO;
import pl.rabczynski.openbook.user.dto.request.UserCreateRequest;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class UserMapper {

    @Autowired
    private PasswordEncoder encoder;

    @Mapping(target = "password", source = "source", qualifiedByName = "encodePassword")
    public abstract UserEntity mapUserCreateRequestToUserEntity(UserCreateRequest source);

    @Mapping(target = "name", source = "role")
    public abstract UserRoleEntity mapRoleEnumToUserRoleEntity(Role role);

    public abstract UserDTO mapUserEntityToUserDTO(UserEntity source);

    @Named("encodePassword")
    protected String encodePassword(UserCreateRequest source) {
        return encoder.encode(source.getPassword());
    }

}
