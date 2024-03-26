package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Customer {
  @Id
  String id;
  String name;
  LocalDate regDate;

  public Customer(String id, String name) {
    this.id = id;
    this.name = name;
    regDate = LocalDate.now();
  }
}
