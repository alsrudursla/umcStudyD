package UMC.studyD.domain;

import UMC.studyD.domain.common.BaseEntity;
import UMC.studyD.domain.enums.MemberGender;
import UMC.studyD.domain.enums.MemberStatus;
import UMC.studyD.domain.mapping.Like;
import UMC.studyD.domain.mapping.MemberAlarm;
import UMC.studyD.domain.mapping.Rent;
import UMC.studyD.dto.SignInDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, length = 10)
    private String nickname;

    @Column(nullable = false, length = 20)
    private String phoneNum;

    private LocalDateTime inactiveDate;

    @Enumerated(EnumType.STRING)
    private MemberGender gender;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Rent> rents = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberAlarm> memberAlarms = new ArrayList<>();

    public static Member createMember(SignInDto signInDto, BCryptPasswordEncoder bCryptPasswordEncoder) {
        Member member = Member.builder()
                .email(signInDto.getEmail())
                .password(bCryptPasswordEncoder.encode(signInDto.getPassword()))
                .nickname(signInDto.getNickname())
                .phoneNum(signInDto.getPhoneNum())
                .inactiveDate(null)
                .gender(signInDto.getGender())
                .status(MemberStatus.ACTIVE)
                .build();
        return member;
    }
}
