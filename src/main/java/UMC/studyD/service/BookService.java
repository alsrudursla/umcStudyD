package UMC.studyD.service;

import UMC.studyD.domain.Book;
import UMC.studyD.domain.Member;
import UMC.studyD.dto.AddBookDto;
import UMC.studyD.dto.AddLikesCount;
import UMC.studyD.dto.ResponseBookListDto;
import UMC.studyD.repository.BookRepository;
import UMC.studyD.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
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
    public List<ResponseBookListDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(ResponseBookListDto::responseBookList)
                .collect(Collectors.toList());
    }

    // 책 조회 (하나)
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(RuntimeException::new);
    }

}
