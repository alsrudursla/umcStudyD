package UMC.studyD.service;

import UMC.studyD.domain.Book;
import UMC.studyD.domain.Member;
import UMC.studyD.domain.enums.BookStatus;
import UMC.studyD.domain.mapping.Rent;
import UMC.studyD.dto.AddRentDto;
import UMC.studyD.dto.ResponseRentListDto;
import UMC.studyD.repository.BookRepository;
import UMC.studyD.repository.MemberRepository;
import UMC.studyD.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RentService {
    private final RentRepository rentRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    // 책 대여 목록 조회
    public List<ResponseRentListDto> getAllRent() {
        List<Rent> rentList = rentRepository.findAll();
        return rentList.stream()
                .map(ResponseRentListDto::responseRentList)
                .collect(Collectors.toList());
    }

    // 책 대여 등록
    public void createRent(AddRentDto addRentDto) {
        Member member = memberRepository.findById(addRentDto.getMemberId())
                .orElseThrow(RuntimeException::new);
        Book book = bookRepository.findById(addRentDto.getBookId())
                .orElseThrow(RuntimeException::new);
        rentRepository.save(new Rent(member, book));
    }

    // 책 상태 변경 (AVAILABLE -> UNAVAILABLE)
    public HttpStatus checkBookStatus(AddRentDto addRentDto) {
        Book book = bookRepository.findById(addRentDto.getBookId()).orElseThrow(RuntimeException::new);

        // 이미 대출 중인 책일 경우 (BookStatus : UNAVAILABLE)
        if (book.getBookStatus() == BookStatus.UNAVAILABLE) {
            return HttpStatus.CONFLICT;
        } else {
            // 대출 가능한 책일 경우 (BookStatus : AVAILABLE -> UNAVAILABLE)
            book.changeBookStatus(BookStatus.UNAVAILABLE);
            return HttpStatus.OK;
        }
    }

    // 책 반납
    public void deleteRent(Long id) {
        // BookStatus 변경 : UNAVAILABLE -> AVAILABLE
        Rent rent = rentRepository.findById(id).orElseThrow(RuntimeException::new);
        Book book = bookRepository.findById(rent.getBook().getId()).orElseThrow(RuntimeException::new);
        book.changeBookStatus(BookStatus.AVAILABLE);

        // rent table 에서 해당 id 삭제
        rentRepository.deleteById(id);
    }

}
