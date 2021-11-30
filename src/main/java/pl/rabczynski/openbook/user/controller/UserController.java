package pl.rabczynski.openbook.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rabczynski.openbook.user.domain.UserService;
import pl.rabczynski.openbook.user.dto.UserDTO;
import pl.rabczynski.openbook.user.dto.request.UserCreateRequest;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
class UserController {

    private final UserService userService;

    @PostMapping
    ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserCreateRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }
}
