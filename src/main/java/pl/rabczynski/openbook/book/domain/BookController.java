package pl.rabczynski.openbook.book.domain;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.rabczynski.openbook.book.dto.BookDTO;

import java.util.List;

@RestController
@RequestMapping("/api/books")
class BookController {

    private final BookFacade bookFacade;

    BookController(final BookFacade bookFacade) {
        this.bookFacade = bookFacade;
    }

    @GetMapping
    List<BookDTO> getBooks(@RequestParam(defaultValue = "1", required = false) Integer page){
        return bookFacade.getBooksWithAuthors(page);
    }

    @GetMapping("/{id}")
    ResponseEntity<BookDTO> getSingleBook(@PathVariable Integer id){
        return bookFacade.getSingleBookWithAuthors(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/rating")
    ResponseEntity<BookDTO> getSingleBookRating(@PathVariable Integer id){
        return bookFacade.getSingleBookRating(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



}
