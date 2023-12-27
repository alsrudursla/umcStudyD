package UMC.studyD.controller;

import UMC.studyD.domain.Book;
import UMC.studyD.dto.AddBookDto;
import UMC.studyD.dto.AddLikesCount;
import UMC.studyD.dto.ResponseBookDto;
import UMC.studyD.dto.ResponseBookListDto;
import UMC.studyD.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    // 책 등록
    @PostMapping()
    public void addBook(@RequestBody AddBookDto addBookDto) {
        bookService.addBook(addBookDto);
    }

    // 책 조회 (전체)
    @GetMapping()
    public List<ResponseBookListDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    // 책 조회 (하나)
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBookDto> getBookById(@PathVariable("id") Long id) {
        Book book = bookService.getBookById(id);

        // 조회하고자 하는 책의 유무 확인
        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ResponseBookDto bookDto = ResponseBookDto.responseBook(book);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

}
