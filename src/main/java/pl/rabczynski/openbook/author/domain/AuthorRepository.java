package pl.rabczynski.openbook.author.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.rabczynski.openbook.author.domain.AuthorEntity;

import java.util.List;
import java.util.Set;

interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {

    AuthorEntity findByFullName(String fullName);

    @Query("SELECT a FROM authors a JOIN FETCH a.books WHERE a.fullName =?1")
    AuthorEntity getAuthorWithBooksByFullName(String fullName);

    @Query("SELECT a FROM authors a JOIN FETCH a.books b WHERE b.id IN ?1")
    List<AuthorEntity> findAllByBooksIdIn(List<Integer> ids);

    @Query("SELECT a FROM authors a JOIN FETCH a.books b WHERE b.id = ?1")
    Set<AuthorEntity> findAuthorsDTOByBooksId(Integer id);
}
