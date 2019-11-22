package com.app.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.univocity.parsers.csv.*;

import pojos.User;

import com.univocity.parsers.common.*;
import com.univocity.parsers.common.processor.*;
import com.univocity.parsers.common.record.*;
import com.univocity.parsers.conversions.*;


@Controller
@RequestMapping("/cuup")
public class ClientUsingUnivocityParser 
{

	@RequestMapping(value = "/hit", method = RequestMethod.POST)
	@ResponseBody
	public String hitService(@RequestParam("myFile") MultipartFile myFile) 
	{	
		String response = "";
		if (myFile != null) 
		{
			System.out.println("file is not null");
			
			StringBuilder out = new StringBuilder();
			CsvParserSettings settings = new CsvParserSettings();
			settings.getFormat().setLineSeparator("\n");
			CsvParser parser = new CsvParser(settings);
			InputStream is = null;
			Date d = new Date();
			int count=0;
			RestTemplate re = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/xml");
			HttpEntity<User> ht;
			try {
				is = myFile.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(String[] row : parser.iterate(is))
			{
				System.out.println("count  : "+count);
				User u = new User(row[0],row[1],row[2],row[3]);
				ht = new HttpEntity<User>(u,headers);
				String tempResponse =  re.postForObject("http://localhost:8080/day14_spring_rest_server/xml/post",ht,
								String.class);			 
				System.out.println(tempResponse + " : " +new Date()+" : count : "+count++);
			}
				
			System.out.println("date : "+d);
			
		} else 
		{
			System.out.println("file is null");
		}
		return response;
	}
}
