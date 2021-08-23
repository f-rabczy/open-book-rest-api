package pl.rabczynski.openbook.book.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.rabczynski.openbook.book.dto.BookDTO;


@Repository
interface BookRepository extends JpaRepository<BookEntity, Integer> {

    @Query(value = "SELECT NEW pl.rabczynski.openbook.book.dto.BookDTO(b.id,b.publicationYear,b.title,b.averageRating) " +
            "FROM books b",
            countQuery = "SELECT  COUNT (b) FROM books  b")
    Page<BookDTO> findAllBookDto(Pageable page);

    @Query(value = "SELECT NEW pl.rabczynski.openbook.book.dto.BookDTO(b.id,b.isbn,b.publicationYear,b.title,b.averageRating,b.bookRating.ratingsCount)" +
            " FROM books b WHERE b.id =?1 ")
    BookDTO findBookDtoUsingId(Integer id);

    @Query(value = "SELECT NEW pl.rabczynski.openbook.book.dto.BookDTO(b.id,b.title,b.bookRating.oneStar,b.bookRating.twoStar,b.bookRating.threeStar,b.bookRating.fourStar," +
            "b.bookRating.fiveStar,b.averageRating,b.bookRating.ratingsCount) FROM books b WHERE b.id =?1" )
    BookDTO findBookDtoWithRatingUsingId(Integer id);
}
