package pl.rabczynski.openbook.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface AuthorRepository extends JpaRepository<AuthorEntity,Integer> {

    AuthorEntity findByFullName(String fullName);

    @Query("SELECT a FROM authors a JOIN FETCH a.books WHERE a.fullName =?1")
    AuthorEntity getAuthorWithBooksByFullName(String fullName);
}
