package pl.rabczynski.openbook.book;

import org.springframework.stereotype.Service;

@Service
public class BookFacade {

    private final BookRepository bookRepository;

    public BookFacade(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }



}
