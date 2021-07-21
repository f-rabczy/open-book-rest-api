package pl.rabczynski.openbook.book;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.rabczynski.openbook.author.AuthorEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//TODO review model and rating model
@Getter
@Setter
@Entity(name = "books")
@NoArgsConstructor
@Table(name = "books")
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

    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER , cascade = CascadeType.MERGE)
    private Set<AuthorEntity> authors = new HashSet<>();


    public void addAuthor(AuthorEntity authorEntity){
        this.authors.add(authorEntity);
    }

    public void initializeBookRating(int oneStar, int twoStar, int threeStar, int fourStar, int fiveStar){
        this.bookRating = new BookRating(oneStar,twoStar,threeStar,fourStar,fiveStar);
        this.averageRating = bookRating.calculateAverage();
    }


}
