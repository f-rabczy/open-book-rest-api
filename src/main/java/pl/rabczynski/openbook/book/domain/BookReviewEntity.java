package pl.rabczynski.openbook.book.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.rabczynski.openbook.user.domain.UserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
public class BookReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer id;

    @NotBlank
    private String content;

    @NotNull
    private LocalDate issueDate;

    @Column(name = "start_date")
    private LocalDate bookReadingStartDate;

    @Column(name = "end_date")
    private LocalDate bookReadingEndDate;

    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity reviewer;
}
