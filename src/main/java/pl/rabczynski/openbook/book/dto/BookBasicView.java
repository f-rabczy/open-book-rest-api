package pl.rabczynski.openbook.book.dto;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import pl.rabczynski.openbook.book.domain.BookEntity;

@Projection(types = BookEntity.class)
public interface BookBasicView {

    Integer getId();
    Integer getPublicationYear();
    String getTitle();

    @Value("#{DataFormatHelper.getAuthorNames(target.authors)}")
    String getAuthors();

    @Value("#{target.getFormattedAverageRating()}")
    String getRating();
}
