package pl.rabczynski.openbook.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.rabczynski.openbook.auth.domain.enums.Role;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {

    @Query(value = "select u from roles u where u.name =:role")
    UserRoleEntity findByName(Role role);
}
