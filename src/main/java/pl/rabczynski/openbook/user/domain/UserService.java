package pl.rabczynski.openbook.user.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.rabczynski.openbook.auth.domain.enums.Role;
import pl.rabczynski.openbook.book.domain.BookShelfEntity;
import pl.rabczynski.openbook.user.dto.UserDTO;
import pl.rabczynski.openbook.user.dto.request.UserCreateRequest;
import pl.rabczynski.openbook.user.mapper.UserMapper;
import pl.rabczynski.openbook.utill.BookShelfConstants;

import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserMapper userMapper;

    public Optional<UserEntity> getOptionalOfUserByUsername(String username) {
        return userRepository.findUserWithRolesByUsername(username);
    }

    public UserDTO createUser(UserCreateRequest request) {
        UserEntity userEntity = userMapper.mapUserCreateRequestToUserEntity(request);
        grantRoles(userEntity, Role.USER);
        initializeDefaultUserShelves(userEntity);
        return userMapper.mapUserEntityToUserDTO(userRepository.save(userEntity));
    }

    private void grantRoles(UserEntity userEntity, Role... roles) {
        Arrays.stream(roles)
                .map(this::fetchOrCreateUserRoleEntity)
                .forEach(userEntity::addRole);
    }

    private void initializeDefaultUserShelves(UserEntity userEntity) {
        BookShelfConstants.DEFAULT_BOOK_SHELVES_NAMES.stream()
                .map(s -> BookShelfEntity.builder().name(s).build())
                .forEach(userEntity::addShelf);
    }

    private UserRoleEntity fetchOrCreateUserRoleEntity(Role role) {
        UserRoleEntity userRoleEntity = userRoleRepository.findByName(role);
        return userRoleEntity != null ?
                userRoleEntity :
                userMapper.mapRoleEnumToUserRoleEntity(role);
    }
}
