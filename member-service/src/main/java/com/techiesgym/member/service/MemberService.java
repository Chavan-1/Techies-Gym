package com.techiesgym.member.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.techiesgym.member.entity.Member;
import com.techiesgym.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	public Member addMember(Member member) {
		if(memberRepository.existsByEmail(member.getEmail())) {
			throw new RuntimeException("Member with this email already exists!");
		}
		member.setActive(true);
		return memberRepository.save(member);
	}
	
	public List<Member> getAllMembers(){
		return memberRepository.findAll();
	}
	
	public Member getMemberById(Long id){
		return memberRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Member not found!"));
	}
	
	public Member updateMember(Long id, Member updated) {
		Member existing = getMemberById(id);
		existing.setName(updated.getName());
		existing.setPhone(updated.getPhone());
		existing.setMembershipType(updated.getMembershipType());
		return memberRepository.save(existing);
	}
	
	public void deactivateMember(Long id) {
		Member existing = getMemberById(id);
		existing.setActive(false);
		memberRepository.save(existing);
	}
}