package pl.rabczynski.openbook.author.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {

    @Query("select a from AuthorEntity a join fetch a.books where a.fullName =?1")
    AuthorEntity getAuthorWithBooksByFullName(String fullName);

    @Query("select a from AuthorEntity a join fetch a.books b where b.id in ?1")
    List<AuthorEntity> findAllByBooksIdIn(List<Integer> ids);

    @Query("select a from AuthorEntity a join fetch a.books b where b.id = ?1")
    Set<AuthorEntity> findAuthorsDTOByBooksId(Integer id);
}
