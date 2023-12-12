package UMC.studyD.controller;

import UMC.studyD.dto.AddLikesCount;
import UMC.studyD.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book/likes")
@RequiredArgsConstructor
public class LikesController {
    private final LikesService likesService;

    // 좋아요 추가
    @PostMapping()
    public ResponseEntity<String> addLikes(@RequestBody AddLikesCount addLikesCount) {
        HttpStatus response = likesService.addLikes(addLikesCount);

        if (response == HttpStatus.CONFLICT) {
            return new ResponseEntity<>("이미 좋아요를 눌렀습니다", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>("좋아요가 반영되었습니다", HttpStatus.OK);
        }
    }

    // 좋아요 취소
    @DeleteMapping("/{id}")
    public void deleteLikes(@PathVariable("id") Long id) {
        likesService.deleteLikes(id);
    }
}
