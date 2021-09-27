package pl.rabczynski.openbook.book.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
class BookRatingContainer {

    private int oneStar;
    private int twoStar;
    private int threeStar;
    private int fourStar;
    private int fiveStar;
    private double ratingsCount;

    double calculateAverage() {
        return (oneStar + twoStar * 2 + threeStar * 3 + fourStar * 4 + fiveStar * 5) / ratingsCount;
    }

    public BookRatingContainer(int oneStar, int twoStar, int threeStar, int fourStar, int fiveStar) {
        this.oneStar = oneStar;
        this.twoStar = twoStar;
        this.threeStar = threeStar;
        this.fourStar = fourStar;
        this.fiveStar = fiveStar;
        this.ratingsCount = oneStar + twoStar + threeStar + fourStar + fiveStar;
    }

}
