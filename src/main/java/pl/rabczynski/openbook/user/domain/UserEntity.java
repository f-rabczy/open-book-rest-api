package pl.rabczynski.openbook.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import pl.rabczynski.openbook.book.domain.BookRatingEntity;
import pl.rabczynski.openbook.book.domain.BookReviewEntity;
import pl.rabczynski.openbook.book.domain.BookShelfEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@FieldNameConstants
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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<UserRoleEntity> roleEntities = new HashSet<>();

    @OneToMany(mappedBy = "rater")
    private Set<BookRatingEntity> booksRatings = new HashSet<>();

    @OneToMany(mappedBy = "reviewer")
    private Set<BookReviewEntity> booksReviews = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private Set<BookShelfEntity> bookShelves = new HashSet<>();

    public void addRole(UserRoleEntity roleEntity) {
        this.roleEntities.add(roleEntity);
    }

    public void addShelf(BookShelfEntity bookShelfEntity) {
        this.bookShelves.add(bookShelfEntity);
        bookShelfEntity.setUser(this);
    }

}
