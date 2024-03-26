
import entity.Customer;
import entity.Locker;
import entity.Major;
import entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

  public static void init(EntityManager em){
    Student stu1 = new Student("김씨", "1학년");
    Student stu2 = new Student("이씨", "2학년");
    Student stu3 = new Student("박씨", "3학년");
    Major m1 = new Major("컴싸", "소프트엔지니어링");
    em.persist(m1);
//    stu1.setMajorId(m1.getMajorId());
//    stu2.setMajorId(m1.getMajorId());
//    stu3.setMajorId(m1.getMajorId());
    stu1.setMajorId(m1);
    stu2.setMajorId(m1);
    stu3.setMajorId(m1);

    em.persist(stu1);
    em.persist(stu2);
    em.persist(stu3);
  }
  public static void main(String[] args) {
    // 객체 sessitonFactory
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("customer-ex");
    EntityManager em = emf.createEntityManager(); // session 객체
    EntityTransaction tx = em.getTransaction();

    tx.begin(); // start transction;
    try {
      init(em);

//      Student stu = em.find(Student.class,1L);
//      Locker locker = new Locker(100);
//      stu.setLocker(locker); // locker_id
//      em.persist(locker);

      tx.commit(); // commit; 쓰기지연 저장소에있는 sql 쿼리문(insert, update, delete ) 한꺼번에 나간다
    }
    catch(Exception e){
      tx.rollback();
    }
    finally{
      em.close();
      emf.close();
    }

  }
}

