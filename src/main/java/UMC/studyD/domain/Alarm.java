package UMC.studyD.domain;

import UMC.studyD.domain.common.BaseEntity;
import UMC.studyD.domain.enums.AlarmStatus;
import UMC.studyD.domain.mapping.MemberAlarm;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Alarm extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long id;

    @Column(nullable = false, length = 10)
    private String dtype;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    private AlarmStatus status;

    @OneToMany(mappedBy = "alarm", cascade = CascadeType.ALL)
    private List<MemberAlarm> memberAlarms = new ArrayList<>();
}
