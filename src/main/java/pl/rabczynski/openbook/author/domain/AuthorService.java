package pl.rabczynski.openbook.author.domain;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void save(AuthorEntity author) {
        authorRepository.save(author);
    }

    public void saveAll(Set<AuthorEntity> authorEntities) {
        authorRepository.saveAll(authorEntities);
    }

    public AuthorEntity getAuthorByFullName(String fullName) {
        return authorRepository.getAuthorWithBooksByFullName(fullName);
    }

    public List<AuthorEntity> findAllAuthorsWithIds(List<Integer> ids) {
        return authorRepository.findAllByBooksIdIn(ids);
    }

    public Set<AuthorEntity> findAuthorsByBookId(Integer id) {
        return authorRepository.findAuthorsDTOByBooksId(id);
    }
}
