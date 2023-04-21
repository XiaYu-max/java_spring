package com.example.demo.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dao.MemberApiRepository;
import com.example.demo.Model.MemberAccount;

@RestController
@RequestMapping("/memberApi")
public class MemberApiController {

    @Autowired
    MemberApiRepository memberapiRepository;

    @RequestMapping(value = "/{id}", produces = { "application/json;charset=UTF-8" })
    public Optional<MemberAccount> read(@PathVariable long id) {
        return memberapiRepository.findById(id);
    }
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	 public void create(@RequestBody MemberAccount memberaccount) {
		memberapiRepository.save(memberaccount);
	 }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody MemberAccount memberaccount) {
        memberapiRepository.save(memberaccount);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        memberapiRepository.deleteById(id);

    }

}