package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.MemberAccount;

public interface MemberApiRepository  extends JpaRepository<MemberAccount, Long>{


	MemberAccount findByIdStartsWith(String id);
	

}