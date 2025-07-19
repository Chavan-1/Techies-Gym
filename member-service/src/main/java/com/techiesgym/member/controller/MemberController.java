package com.techiesgym.member.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techiesgym.member.entity.Member;
import com.techiesgym.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	
	@PostMapping
	public ResponseEntity<Member> addMember(@RequestBody Member member){
		return ResponseEntity.ok(memberService.addMember(member));
	}
	
	@GetMapping
	public ResponseEntity<List<Member>> getAllMembers(){
		return ResponseEntity.ok(memberService.getAllMembers());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Member> getMemberById(@PathVariable Long id){
		return ResponseEntity.ok(memberService.getMemberById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member member){
		return ResponseEntity.ok(memberService.updateMember(id, member));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deactivateMember(@PathVariable Long id){
		memberService.deactivateMember(id);
		return ResponseEntity.ok("Member deactivated successfully");
	}
}
