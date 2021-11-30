package pl.rabczynski.openbook.book.domain;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.rabczynski.openbook.author.domain.AuthorEntity;
import pl.rabczynski.openbook.author.domain.AuthorService;
import pl.rabczynski.openbook.author.dto.AuthorDTO;
import pl.rabczynski.openbook.author.mapper.AuthorMapper;
import pl.rabczynski.openbook.book.dto.BookDTO;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.rabczynski.openbook.utill.PageHelper.FIRST_PAGE_NUMBER;
import static pl.rabczynski.openbook.utill.PageHelper.PAGE_SIZE;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;
    private final RestTemplate restTemplate;

    public BookDTO getBookRating(Integer id) {
        return bookRepository.findBookDtoWithRatingUsingId(id)
                .orElseThrow(() -> new IllegalArgumentException("No book rating found with given book id: " + id));
    }

    public BookDTO getBookWithAuthors(Integer id) {
        var book = bookRepository.findBookDtoUsingId(id)
                .orElseThrow(() -> new IllegalArgumentException("No book found with given id: " + id));
        var authors = authorService.findAuthorsByBookId(id);
        book.setAuthors(authors.stream()
                .map(authorMapper::authorToAuthorDTO)
                .collect(Collectors.toSet()));
        return book;
    }

    public List<BookDTO> getBooksWithAuthors(Integer page) {
        if (page == null || page < FIRST_PAGE_NUMBER) page = FIRST_PAGE_NUMBER;
        var books = bookRepository.findAllBookDto(PageRequest.of(--page, PAGE_SIZE)).getContent();
        var ids = books.stream()
                .map(BookDTO::getId)
                .collect(Collectors.toList());
        var authors = authorService.findAllAuthorsWithIds(ids);
        books.forEach(book -> book.setAuthors(extractAuthors(authors, book.getId())));
        return books;
    }

    public void save(BookEntity bookEntity) {
        bookRepository.save(bookEntity);
    }

    @SneakyThrows
    public byte[] getBookCover(Integer id) {
        var imageUrl = bookRepository.findBookImageUrlUsingId(id).
                orElseThrow(() -> new IllegalArgumentException("No book's cover found with given book id: " + id));
        return restTemplate.getForObject(new URI(imageUrl), byte[].class);

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
