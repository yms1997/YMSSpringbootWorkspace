package kr.ex.querydsl.domain;

import lombok.Data;

public class MemberDTO {
  @Data
  public class MemberDto {
    private String username;
    private int age;
    public MemberDto() {
    }
    public MemberDto(String username, int age) {
      this.username = username;
      this.age = age;
    }
  }
}
