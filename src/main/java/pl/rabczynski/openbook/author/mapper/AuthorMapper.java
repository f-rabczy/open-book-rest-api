package pl.rabczynski.openbook.author.mapper;

import org.mapstruct.Mapper;
import pl.rabczynski.openbook.author.AuthorEntity;
import pl.rabczynski.openbook.author.dto.AuthorDTO;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDTO authorToAuthorDTO(AuthorEntity authorEntity);
}
