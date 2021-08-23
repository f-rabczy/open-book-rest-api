package pl.rabczynski.openbook.utill;

import java.util.Locale;

public class DataFormatHelper {

    public static final String RATING_FORMAT = "%,.2f";

    public static Double getFormattedAverageRating(Double averageRating){
        return Double.valueOf(String.format(Locale.US,RATING_FORMAT,averageRating));
    }
}
