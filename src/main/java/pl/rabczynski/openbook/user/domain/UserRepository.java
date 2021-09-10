package pl.rabczynski.openbook.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<UserEntity,Integer> {
}
