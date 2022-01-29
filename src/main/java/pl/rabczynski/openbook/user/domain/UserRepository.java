package pl.rabczynski.openbook.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "select u from UserEntity u join fetch u.roleEntities where u.username =:username")
    Optional<UserEntity> findUserWithRolesByUsername(String username);
}
