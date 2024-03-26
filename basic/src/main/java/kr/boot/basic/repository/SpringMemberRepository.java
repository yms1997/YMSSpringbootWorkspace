package kr.boot.basic.repository;

import kr.boot.basic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringMemberRepository extends JpaRepository<Member, Long>{
    public Member findByName(String name); // select 8 from member
}
