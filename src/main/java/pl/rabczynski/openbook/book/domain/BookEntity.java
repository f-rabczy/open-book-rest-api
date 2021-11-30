package pl.rabczynski.openbook.book.domain;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.rabczynski.openbook.author.domain.AuthorEntity;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name = "books")
@NoArgsConstructor
@Entity(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String isbn;

    @NotNull
    private Integer publicationYear;

    @NotNull
    private String title;

    private String imageUrl;

    @Embedded
    private BookRatingContainer bookRatingContainer;

    //TODO PREUPDATE ? PREPERSIST

    private Double averageRating;

    @ManyToMany(mappedBy = "books", cascade = CascadeType.MERGE)
    private Set<AuthorEntity> authors = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<BookRatingEntity> ratings = new HashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<BookReviewEntity> reviews = new HashSet<>();

    public void addAuthor(AuthorEntity author) {
        this.authors.add(author);
    }

    public void initializeBookRatingContainer(int oneStar, int twoStar, int threeStar, int fourStar, int fiveStar) {
        this.bookRatingContainer = new BookRatingContainer(oneStar, twoStar, threeStar, fourStar, fiveStar);
        this.averageRating = bookRatingContainer.calculateAverage();
    }
}
