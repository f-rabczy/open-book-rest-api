package pl.rabczynski.openbook.book;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class BookController {


    @GetMapping
    ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello");
    }
}
