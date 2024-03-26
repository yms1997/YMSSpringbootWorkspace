package kr.ex.querydsl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class QueryDSLTest2 {



  /**
   * JPQL
   * select
   * COUNT(m), //회원수
   * SUM(m.age), //나이 합
   * AVG(m.age), //평균 나이
   * MAX(m.age), //최대 나이
   * MIN(m.age) //최소 나이
   * from Member m
   */
  @Test
  public void aggregation() throws Exception {
    List<Tuple> result = queryFactory
        .select(member.count(),
            member.age.sum(),
            member.age.avg(),
            member.age.max(),
            member.age.min())
        .from(member)
        .fetch();
    Tuple tuple = result.get(0);
    System.out.println("tuple.toString() = " + tuple.toString());
    System.out.println("count = " + tuple.get(member.count()));

    assertThat(tuple.get(member.count())).isEqualTo(4);
    assertThat(tuple.get(member.age.sum())).isEqualTo(100);
    assertThat(tuple.get(member.age.avg())).isEqualTo(25);
    assertThat(tuple.get(member.age.max())).isEqualTo(40);
    assertThat(tuple.get(member.age.min())).isEqualTo(10);
  }


  /**
   * 팀의 이름과 각 팀의 평균 연령을 구해라
   */
  @Test
  public void group() throws Exception {
    List<Tuple> result = queryFactory
        .select(team.name, member.age.avg())
        .from(member)
        .join(member.team, team) // join은 inner 조인을 의미한다 m.teamid = t.teamid
        .groupBy(team.name)
        //.having(member.age.avg().gt(10))
        .fetch();
    Tuple teamA = result.get(0);
    Tuple teamB = result.get(1);
    System.out.println("teamA = " + teamA.toString());
    System.out.println("teamB = " + teamB.toString());

    assertThat(teamA.get(team.name)).isEqualTo("teamA");
    assertThat(teamA.get(member.age.avg())).isEqualTo(15);
    assertThat(teamB.get(team.name)).isEqualTo("teamB");
    assertThat(teamB.get(member.age.avg())).isEqualTo(35);
  }

  /**
   * 세타 조인(연관관계가 없는 필드로 조인)
   * 회원의 이름이 팀 이름과 같은 회원 조회
   */
  @Test
  public void theta_join() throws Exception {
    // 사람 이름이 teamA
    // 연관관겨는 id 만 있으니깐 name 으로도 되는지 test : 연관관계
    em.persist(new Member("teamA")); // member.setName("teamA")
    em.persist(new Member("teamB"));
    em.persist(new Member("teamC"));

    // select * from member m, team t where m.username = t.username;

    List<Member> result = queryFactory
        .select(member)
        .from(member, team) // from 두개를 나열하는 것
        .where(member.username.eq(team.name))
        .fetch();
    result.stream().forEach(m -> System.out.println("m = " + m + "t =" + m.getTeam()));


    assertThat(result)
        .extracting("username")
        .containsExactly("teamA", "teamB");
  }
}
