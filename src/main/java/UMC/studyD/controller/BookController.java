package UMC.studyD.controller;

import UMC.studyD.domain.Book;
import UMC.studyD.dto.AddBookDto;
import UMC.studyD.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    // 책 등록
    @PostMapping("/book")
    public void addBook(@RequestBody AddBookDto addBookDto) {
        bookService.addBook(addBookDto);
    }

    // 책 조회 (전체)
    @GetMapping("/book")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // 책 조회 (하나)
    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }
}
