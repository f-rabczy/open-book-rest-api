package pl.rabczynski.openbook.book.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.rabczynski.openbook.book.dto.BookDTO;

import java.util.Optional;


@Repository
interface BookRepository extends JpaRepository<BookEntity, Integer> {

    @Query(value = "select new pl.rabczynski.openbook.book.dto.BookDTO(b.id,b.publicationYear,b.title,b.averageRating) " +
            "from BookEntity b",
            countQuery = "select count (b) from BookEntity  b")
    Page<BookDTO> findAllBookDto(Pageable page);

    @Query(value = "select new pl.rabczynski.openbook.book.dto.BookDTO(b.id,b.isbn,b.publicationYear,b.title,b.averageRating,b.bookRatingContainer.ratingsCount)" +
            " from BookEntity b where b.id =:id ")
    Optional<BookDTO> findBookDtoUsingId(Integer id);

    @Query(value = "select new pl.rabczynski.openbook.book.dto.BookDTO(b.id,b.title,b.bookRatingContainer.oneStar,b.bookRatingContainer.twoStar," +
            "b.bookRatingContainer.threeStar,b.bookRatingContainer.fourStar," +
            "b.bookRatingContainer.fiveStar,b.averageRating,b.bookRatingContainer.ratingsCount)" +
            " from BookEntity b where b.id =:id")
    Optional<BookDTO> findBookDtoWithRatingUsingId(Integer id);

    @Query(value = "select b.imageUrl from BookEntity b where b.id =:id")
    Optional<String> findBookImageUrlUsingId(Integer id);

    @Query(nativeQuery = true, value = "select id from books limit 1 ")
    Optional<Integer> findAnyBookId();
}
