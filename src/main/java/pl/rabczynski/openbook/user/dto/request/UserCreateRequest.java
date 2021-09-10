package pl.rabczynski.openbook.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UserCreateRequest {

    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String email;
}
