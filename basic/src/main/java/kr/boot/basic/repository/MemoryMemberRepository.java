package kr.boot.basic.repository;

import kr.boot.basic.domain.Member;

import java.util.*;

// DAO

public class MemoryMemberRepository implements MemberRepository{

  private static Map<Long, Member> store = new HashMap<>();
  private static long sequence = 0L;

  @Override
  public Member save(Member member) {
    member.setId(++sequence);
    store.put(member.getId(), member);
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    return Optional.ofNullable(store.get(id)); // id값이 있으면 id가 가고, null이면 null이 감
  }

  @Override
  public  Optional<Member> findByName(String name) {
    return store.values()
        .stream()
        .filter(member -> member.getName().equals(name))
        .findAny();
  }

  @Override
  public List<Member> findAll() {
    return new ArrayList<>(store.values());
  }
}
