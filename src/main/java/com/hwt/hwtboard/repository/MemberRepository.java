package com.hwt.hwtboard.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hwt.hwtboard.entity.SiteMember;

public interface MemberRepository extends JpaRepository<SiteMember, Integer>{
	public Optional<SiteMember> findByUsername(String Username);
}
