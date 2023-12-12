package UMC.studyD.controller;

import UMC.studyD.dto.AddRentDto;
import UMC.studyD.dto.ResponseRentListDto;
import UMC.studyD.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent")
@RequiredArgsConstructor
public class RentController {
    private final RentService rentService;

    // 책 대여 목록 조회
    @GetMapping()
    public List<ResponseRentListDto> getAllRent() {
        return rentService.getAllRent();
    }

    // 책 대여 등록
    @PostMapping()
    public ResponseEntity<String> createRent(@RequestBody AddRentDto addRentDto) {
        // 1. BookStatus 확인 후 변경 : AVAILABLE -> UNAVAILABLE
        HttpStatus bookStatus = rentService.checkBookStatus(addRentDto);

        // 2. Rent table 등록
        if (bookStatus == HttpStatus.CONFLICT) {
            return new ResponseEntity<>("이미 대출 중인 책입니다", HttpStatus.CONFLICT);
        } else {
            rentService.createRent(addRentDto);
            return new ResponseEntity<>("책을 대출하였습니다", HttpStatus.OK);
        }
    }

    // 책 반납
    @DeleteMapping("/{id}")
    public void deleteRent(@PathVariable("id") Long id) {
        rentService.deleteRent(id);
    }
}
