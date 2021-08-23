package pl.rabczynski.openbook.book.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.rabczynski.openbook.author.dto.AuthorDTO;

import java.util.Set;

import static pl.rabczynski.openbook.utill.DataFormatHelper.getFormattedAverageRating;

@EqualsAndHashCode
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {

    private Integer id;
    private String isbn;
    private Integer publicationYear;
    private String title;
    private String imageUrl;
    private Integer oneStar;
    private Integer twoStar;
    private Integer threeStar;
    private Integer fourStar;
    private Integer fiveStar;
    private Double averageRating;
    private Set<AuthorDTO> authors;
    private Double ratingsCount;

    public BookDTO(Integer id, Integer publicationYear, String title, Double averageRating) {
        this.id = id;
        this.publicationYear = publicationYear;
        this.title = title;
        this.averageRating = getFormattedAverageRating(averageRating);
    }

    public BookDTO(Integer id, String isbn, Integer publicationYear, String title, Double averageRating, Double ratingsCount) {
        this.id = id;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.title = title;
        this.averageRating = getFormattedAverageRating(averageRating);
        this.ratingsCount = ratingsCount;
    }

    public BookDTO(Integer id, String title, Integer oneStar, Integer twoStar, Integer threeStar, Integer fourStar, Integer fiveStar, Double averageRating, Double ratingsCount) {
        this.id = id;
        this.title = title;
        this.oneStar = oneStar;
        this.twoStar = twoStar;
        this.threeStar = threeStar;
        this.fourStar = fourStar;
        this.fiveStar = fiveStar;
        this.averageRating = getFormattedAverageRating(averageRating);
        this.ratingsCount = ratingsCount;
    }
}
