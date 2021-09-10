package pl.rabczynski.openbook.book.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.rabczynski.openbook.book.dto.BookDTO;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
class BookController {

    private final BookFacade bookFacade;

    @GetMapping
    List<BookDTO> getBooks(@RequestParam(defaultValue = "1", required = false) Integer page) {
        return bookFacade.getBooksWithAuthors(page);
    }

    @GetMapping("/{id}")
    ResponseEntity<BookDTO> getBook(@PathVariable Integer id) {
        return ResponseEntity.ok(bookFacade.getBookWithAuthors(id));
    }

    @GetMapping("/{id}/rating")
    ResponseEntity<BookDTO> getBookRating(@PathVariable Integer id) {
        return ResponseEntity.ok(bookFacade.getBookRating(id));
    }

    @GetMapping(value = "/{id}/cover", produces = MediaType.IMAGE_JPEG_VALUE)
    ResponseEntity<byte[]> getBookCover(@PathVariable Integer id) {
        return ResponseEntity.ok(bookFacade.getBookCover(id));
    }


}
