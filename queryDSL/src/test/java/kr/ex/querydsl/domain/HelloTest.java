package kr.ex.querydsl.domain;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest // 통합테스트 : 스프링부트 시작처럼 root-context : db 객체 -> em
@Transactional // 트렌지션을 열어야지만 db에 값 전달 가능 transitiaonal.commit() -> rollback() : 테스트만 하고 db값은 저장안함
class HelloTest {
  @Autowired
  EntityManager em;
  @Test
  // @Commit // commit을 하면 rollback 안하고 진짜 db에 저장된다
  void fristTest(){
    Hello hello1 = new Hello();
    Hello hello2 = new Hello();
    Hello hello3 = new Hello();
    Hello hello4 = new Hello();
    em.persist(hello1);
    em.persist(hello2);
    em.persist(hello3);
    em.persist(hello4);
    System.out.println("=====================");
    List<Hello> list = em.createQuery("select h from Hello h").getResultList();

    list.forEach(h -> System.out.println("h = " + h));
  }

  @Test
  void Test(){
    Hello hello1 = new Hello();
    Hello hello2 = new Hello();
    Hello hello3 = new Hello();
    Hello hello4 = new Hello();
    em.persist(hello1);
    em.persist(hello2);
    em.persist(hello3);
    em.persist(hello4);
    System.out.println("=====================");
    JPAQueryFactory queryFactory = new JPAQueryFactory(em);
    QHello h = new QHello("hello");
    List<Hello> result = queryFactory.select(h.hello).from(h.hello).fetch();
    Hello findHello = queryFactory.selectFrom(QHello.hello).fetchFirst();

    System.out.println("findHello = " + findHello);


  }
}