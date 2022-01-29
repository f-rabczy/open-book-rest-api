package pl.rabczynski.openbook.book.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "bookshelf_entries")
public class BookShelfEntryEntity {

    @Id
    @Column
    @NotNull
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_shelf_id", nullable = false)
    private BookShelfEntity bookShelf;

    @OneToOne
    @JoinColumn(name = "book_rating_id", referencedColumnName = "id")
    private BookRatingEntity bookRating;

    @OneToOne
    @JoinColumn(name = "book_review_id", referencedColumnName = "id")
    private BookReviewEntity bookReview;

    @Column
    private LocalDate readingStartDate;

    @Column
    private LocalDate readingEndDate;

    @Column
    private String note;
}
