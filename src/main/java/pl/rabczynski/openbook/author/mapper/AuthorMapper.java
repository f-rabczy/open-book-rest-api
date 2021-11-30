package pl.rabczynski.openbook.author.mapper;

import org.mapstruct.Mapper;
import pl.rabczynski.openbook.author.domain.AuthorEntity;
import pl.rabczynski.openbook.author.dto.AuthorDTO;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDTO authorToAuthorDTO(AuthorEntity authorEntity);
}
