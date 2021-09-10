package pl.rabczynski.openbook.user.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.rabczynski.openbook.user.dto.UserDTO;
import pl.rabczynski.openbook.user.dto.request.UserCreateRequest;
import pl.rabczynski.openbook.user.mapper.UserMapper;

@RequiredArgsConstructor
@Service
public class UserFacade {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO createUser(UserCreateRequest source){
        UserEntity userEntity = userMapper.userCreateRequestToUserEntity(source);
        return userMapper.userEntityToUserDTO(userRepository.save(userEntity));
    }
}
