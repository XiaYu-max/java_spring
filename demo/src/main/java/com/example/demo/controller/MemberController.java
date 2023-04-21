package com.example.demo.controller;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import com.example.demo.dao.MemberRepository;
import com.example.demo.model.MemberAccountJPA;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Controller
public class MemberController {
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	DataSource dataSource;
	 

	@GetMapping("/addMemberPage")
    public String addMemberPage(){

		List<MemberAccountJPA> memberAccountJPA= new ArrayList<MemberAccountJPA>();
		memberAccountJPA = memberRepository.findAll();

		for(int i=0;i<memberAccountJPA.size();i++){
			System.out.println(memberAccountJPA.get(i).getId());
		}
        
        return "addMemberPage";
    }

	@GetMapping("/login")
    public String login(@ModelAttribute MemberAccountJPA memberAccountJPA){

        return "login";
    }
	
	@PostMapping("/doLogin")
    public String doLogin(@ModelAttribute MemberAccountJPA memberAccountJPA,HttpSession session){
		String email = memberAccountJPA.getEmail();
		String password = memberAccountJPA.getPassword();
		List<MemberAccountJPA> MemberAccountJPAList = new ArrayList<MemberAccountJPA>();
		MemberAccountJPAList = memberRepository.findCheckMemberAccount(email, password);
		
		MemberAccountJPA memberAccount = new  MemberAccountJPA();
		memberAccount.setPassword(password);
		memberAccount.setEmail(email);;

		if(MemberAccountJPAList.size()==0){
			System.out.println("登入錯誤");
			return "login";
		}
		else{
			session.setAttribute("uid", memberAccount);
	        return "welcome";
		}

    }
	

	@GetMapping("/up_load")
    public String up_load(){

        return "up_load";
    }

		// 多個檔案上傳的接口
	@PostMapping("/api/upload/multi")
	@ResponseBody
	public ResponseEntity<?> uploadFileMulti(
			@RequestParam("files") MultipartFile[] uploadfiles) {


		// 取得檔案名稱
		String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
				.filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

		if (StringUtils.isEmpty(uploadedFileName)) {
			return new ResponseEntity("請選擇檔案!", HttpStatus.OK);
		}

		try {

			saveUploadedFiles(Arrays.asList(uploadfiles));

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity("成功上傳 - "
				+ uploadedFileName, HttpStatus.OK);

	}

	 //將檔案儲存
	 private void saveUploadedFiles(List<MultipartFile> files) throws IOException {

		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				continue; //繼續下一個檔案
			}

			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

		}

	}

}