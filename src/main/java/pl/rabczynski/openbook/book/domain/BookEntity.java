package pl.rabczynski.openbook.book.domain;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.rabczynski.openbook.author.AuthorEntity;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

//TODO review model and rating model
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
    private BookRating bookRating;

    //TODO PREUPDATE ? PREPERSIST

    private Double averageRating;

    @ManyToMany(mappedBy = "books", cascade = CascadeType.MERGE)
    private Set<AuthorEntity> authors = new HashSet<>();


    public void addAuthor(AuthorEntity author) {
        this.authors.add(author);
    }

    public void initializeBookRating(int oneStar, int twoStar, int threeStar, int fourStar, int fiveStar) {
        this.bookRating = new BookRating(oneStar, twoStar, threeStar, fourStar, fiveStar);
        this.averageRating = bookRating.calculateAverage();
    }
}
