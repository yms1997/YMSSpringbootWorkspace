package kr.boot.basic.service;

import jakarta.transaction.Transactional;
import kr.boot.basic.domain.Member;
import kr.boot.basic.repository.SpringMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class MemberService {

    //private final MemberRepository memberRepository;

    //public MemberService(MemberRepository repository){
       // this.memberRepository = repository;
    //}

    @Autowired
    private SpringMemberRepository memberRepository;

    // 회원가입
    public boolean join(Member member){
        if(validateDuplicateMember(member)){
            memberRepository.save(member);
            return true;
        }
        else{
            System.out.println("이미 존재하는 회원입니다");
            return false;
        }
    }

    // 아이디 중복검사
    private boolean validateDuplicateMember(Member member){
//      memberRepository.findById(member.getId()).ifPresent(m -> {throw new IllegalArgumentException("이미 존재하는 회원입니다");});
        return memberRepository.findByName(member.getName()) == null;
    }

    // 전체 회원조회
    public List<Member> findMembers(){
        return memberRepository.findAll(); // select * from member;
    }

    // 회원한명조회
    public Optional<Member> findOneMember(Long id){
        return memberRepository.findById(id);
    }
}
