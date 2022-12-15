package com.hwt.hwtboard.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hwt.hwtboard.entity.SiteMember;
import com.hwt.hwtboard.exception.DataNotFoundException;
import com.hwt.hwtboard.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Memberservice {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	public SiteMember memberCreate(String username, String password, String email) {
		
		SiteMember member = new SiteMember();
		member.setUsername(username);
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setPassword(passwordEncoder.encode(password));
//		member.setPassword(password);
		member.setEmail(email);
		
		memberRepository.save(member);
		return member;
		
	}
	public SiteMember getMemberInfo(String username) {
		
		Optional<SiteMember> optSiteMember= memberRepository.findByUsername(username);
		if(optSiteMember.isPresent()) {
			SiteMember siteMember = optSiteMember.get();
			return siteMember;
		}else {
			throw new DataNotFoundException("유저를 찾을수 없습니다.");
		}
	}
	
}
