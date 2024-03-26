package entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
@Table(name="student_tbl")
@ToString(exclude = "major")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    private  String name;
    private  String grade;              // fetch = FetchType.LAZY 지연로딩(성능면에서 좋음)
    @ManyToOne(fetch = FetchType.EAGER) // 관계 구성 FetchType.EAGER(기본값) : 즉시로딩 : 연관되어있는 모든 테이블 다 가져오기
    @JoinColumn(name = "majorId") // 테이블 컬럼의 fk 명
    private Major majorId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Locker locker; // locker_id 1를 누가 참조하고 있으면 다른 Student는 참조못함

  public Student(String name, String grade) {
    this.name = name;
    this.grade = grade;
  }
}
