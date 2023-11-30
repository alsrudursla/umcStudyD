package UMC.studyD.service;

import UMC.studyD.domain.Book;
import UMC.studyD.dto.AddBookDto;
import UMC.studyD.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    // 책 등록
    public void addBook(AddBookDto addBookDto) {
        // DB 에 책 저장
        Book book = Book.createBook(addBookDto);
        bookRepository.save(book);
    }

    // 책 조회 (전체)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // 책 조회 (하나)
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
