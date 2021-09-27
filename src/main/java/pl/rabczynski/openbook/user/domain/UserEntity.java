package pl.rabczynski.openbook.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.rabczynski.openbook.book.domain.BookRatingEntity;
import pl.rabczynski.openbook.book.domain.BookReviewEntity;
import pl.rabczynski.openbook.book.domain.BookShelfEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "users")
@Table(name = "users")
public class UserEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Column(unique = true)
    @Email
    private String email;

    @OneToMany(mappedBy = "rater")
    private Set<BookRatingEntity> booksRatings = new HashSet<>();

    @OneToMany(mappedBy = "reviewer")
    private Set<BookReviewEntity> booksReviews = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<BookShelfEntity> bookShelves = new HashSet<>();

}
