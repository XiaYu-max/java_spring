package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.MemberAccountJPA;

public interface MemberRepository  extends JpaRepository<MemberAccountJPA, Long>{
	List<MemberAccountJPA> findAll();
	
	List<MemberAccountJPA> findByEmail(String email);

	@Query(value="select id,email,password,address,cellphone from member.memberaccountjpa where EMAIL = ?1 and PASSWORD = ?2 " ,nativeQuery = true)
	List<MemberAccountJPA> findCheckMemberAccount(String email,String password);
}