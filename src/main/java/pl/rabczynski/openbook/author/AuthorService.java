package pl.rabczynski.openbook.author;


import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;


    AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void save(AuthorEntity authorEntity){
        authorRepository.save(authorEntity);
    }

    public void saveAll(Set<AuthorEntity> authorEntities){
        authorRepository.saveAll(authorEntities);
    }

    public AuthorEntity getAuthorByFullName(String fullName){
        return authorRepository.getAuthorWithBooksByFullName(fullName);
    }
}
