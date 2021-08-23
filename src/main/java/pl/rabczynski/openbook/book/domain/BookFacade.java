package pl.rabczynski.openbook.book.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.rabczynski.openbook.author.AuthorEntity;
import pl.rabczynski.openbook.author.AuthorFacade;
import pl.rabczynski.openbook.author.dto.AuthorDTO;
import pl.rabczynski.openbook.author.mapper.AuthorMapper;
import pl.rabczynski.openbook.book.dto.BookDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.rabczynski.openbook.utill.PageHelper.FIRST_PAGE_NUMBER;
import static pl.rabczynski.openbook.utill.PageHelper.PAGE_SIZE;

@Service
public class BookFacade {

    private final BookRepository bookRepository;
    private final AuthorFacade authorFacade;
    private final AuthorMapper authorMapper;

    public BookFacade(final BookRepository bookRepository,
                      final AuthorFacade authorFacade,
                      final AuthorMapper authorMapper) {
        this.bookRepository = bookRepository;
        this.authorFacade = authorFacade;
        this.authorMapper = authorMapper;
    }

    public Optional<BookDTO> getSingleBookRating(Integer id){
        return Optional.of(bookRepository.findBookDtoWithRatingUsingId(id));
    }

    public Optional<BookDTO> getSingleBookWithAuthors(Integer id){
        BookDTO book = bookRepository.findBookDtoUsingId(id);
        Set<AuthorEntity> authors = authorFacade.findAuthorsByBookId(id);
        book.setAuthors(authors.stream()
                .map(authorMapper::authorToAuthorDTO)
                .collect(Collectors.toSet()));
        return Optional.of(book);
    }

    public List<BookDTO> getBooksWithAuthors(Integer page) {
        if (page == null || page < FIRST_PAGE_NUMBER) page = FIRST_PAGE_NUMBER;
        var books = bookRepository.findAllBookDto(PageRequest.of(--page, PAGE_SIZE)).getContent();
        var ids = books.stream()
                .map(BookDTO::getId)
                .collect(Collectors.toList());
        var authors = authorFacade.findAllAuthorsWithIds(ids);
        books.forEach(book -> book.setAuthors(extractAuthors(authors, book.getId())));
        return books;
    }

    public void save(BookEntity bookEntity){
        bookRepository.save(bookEntity);
    }

    private Set<AuthorDTO> extractAuthors(List<AuthorEntity> authors, Integer id) {
        return authors.stream()
                .filter(author -> isBookAuthor(author.getBooks(), id))
                .map(authorMapper::authorToAuthorDTO)
                .collect(Collectors.toSet());
    }

    private boolean isBookAuthor(Set<BookEntity> books, Integer id) {
        return books.stream().anyMatch(book -> book.getId().equals(id));
    }
}
