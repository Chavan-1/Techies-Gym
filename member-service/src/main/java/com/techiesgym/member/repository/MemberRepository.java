package com.techiesgym.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.techiesgym.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	boolean existsByEmail(String email);
}
