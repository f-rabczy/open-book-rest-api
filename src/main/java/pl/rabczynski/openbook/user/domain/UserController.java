package pl.rabczynski.openbook.user.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rabczynski.openbook.user.dto.UserDTO;
import pl.rabczynski.openbook.user.dto.request.UserCreateRequest;

@RequiredArgsConstructor
@RestController
class UserController {

    private final UserFacade userFacade;

    @PostMapping
    ResponseEntity<UserDTO> register(UserCreateRequest source){
        return ResponseEntity.ok(userFacade.createUser(source));
    }

}
