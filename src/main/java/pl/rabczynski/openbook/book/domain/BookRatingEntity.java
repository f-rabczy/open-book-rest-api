package pl.rabczynski.openbook.book.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.rabczynski.openbook.user.domain.UserEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "ratings")
@Getter
@Setter
@NoArgsConstructor
public class BookRatingEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity rater;

    @NotNull
    private Double value;

    @NotNull
    private LocalDate issueDate;

}
