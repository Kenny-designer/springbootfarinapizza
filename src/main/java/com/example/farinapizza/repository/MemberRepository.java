package com.example.farinapizza.repository;

import com.example.farinapizza.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByEmail(String email); // 此查詢為CrudRepository已寫好的方法
}
