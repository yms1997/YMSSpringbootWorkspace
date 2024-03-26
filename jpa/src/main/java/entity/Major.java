package entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "major_tbl")
public class Major {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long majorId;
  private String name;
  private String category;
  @OneToMany
  private List<Student> students = new ArrayList<>();

  public Major(String name, String category) {
    this.name = name;
    this.category = category;
  }
}
