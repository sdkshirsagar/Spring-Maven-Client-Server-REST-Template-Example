package com.app.controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pojos.User;
import pojos.Users;

@Controller
@RequestMapping("/xml")
public class XMLController {

	
	@RequestMapping(value = "/post",method = RequestMethod.POST,headers = "Accept=application/xml")
	@ResponseBody
	public String postData(@RequestBody User usr) 
	{
		return usr.getId() +"<--->" +usr.getName();
	}
	
	@RequestMapping(value = "/postlist",method = RequestMethod.POST,headers = "Accept=application/xml")
	@ResponseBody
	public Users postDatalisr(@RequestBody Users usr) 
	{
		Date d = new Date();
		Users testUser = new Users(); 
		System.out.println("inside post data");
		for(User u : usr.getUserList())
		{
			testUser.getUserList().add(new User("test_"+u.getAccNo(),"test_"+u.getId(),"test_"+u.getIfsc(),"test_"+u.getName()));
			System.out.println("after for loop  post data");
			System.out.println(u.toString());
			
		}
		
		

		System.out.println(d +" testUser list size" + testUser.getUserList().size());
		return testUser;
	}
}
