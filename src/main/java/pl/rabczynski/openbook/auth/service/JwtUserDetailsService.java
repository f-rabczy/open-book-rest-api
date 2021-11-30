package pl.rabczynski.openbook.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.rabczynski.openbook.user.domain.UserEntity;
import pl.rabczynski.openbook.user.domain.UserRoleEntity;
import pl.rabczynski.openbook.user.domain.UserService;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.getOptionalOfUserByUsername(username)
                .map(this::mapUserEntityToUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("User with given username not found"));
    }

    private UserDetails mapUserEntityToUserDetails(UserEntity userEntity) {
        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                mapRolesToGrantedAuthorities(userEntity.getRoleEntities()));
    }

    private Set<GrantedAuthority> mapRolesToGrantedAuthorities(Set<UserRoleEntity> roleEntities) {
        var authorities = new HashSet<GrantedAuthority>();
        roleEntities.forEach(userRoleEntity -> authorities.add(new SimpleGrantedAuthority("ROLE_" + userRoleEntity)));
        return authorities;
    }
}
