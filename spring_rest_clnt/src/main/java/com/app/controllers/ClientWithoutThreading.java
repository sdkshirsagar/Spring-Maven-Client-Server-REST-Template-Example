package com.app.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import pojos.User;
import pojos.Users;

@Controller
@RequestMapping("/cwt")
public class ClientWithoutThreading 
{

	@RequestMapping(value = "/hit", method = RequestMethod.POST)
	@ResponseBody
	public String hitService(@RequestParam("myFile") MultipartFile myFile) 
	{	
		String response = "";
		if (myFile != null) 
		{
			System.out.println("file is not null");
			RestTemplate re = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/xml");
			HttpEntity<Users> ht;
			BufferedReader br = null;
			try 
			{
				InputStream is = myFile.getInputStream();
				br = new BufferedReader(new InputStreamReader(is));
				int count=0;
				Date d1,d2,d3,d4;
				d1 = new Date();
				String line,tempResponse = null;	
				Users ulist = new Users();
				Users tempUserList = null;
				HashMap<Integer,Users> userListMap = new HashMap<Integer, Users>(); 
				int mapCounter = 0;
				int sentListSixe = 0 ; int responseListSixe = 0;
				
				while ((line = br.readLine()) != null) 
				{
					System.out.println("count on enetering loop : "+count);
					String[] b = line.split(",");
					User u = new User(b[0],b[1],b[2],b[3]);
					 
					if(ulist.getUserList().size()<100000)
						ulist.getUserList().add(u);
					else
					{
						userListMap.put(mapCounter++,ulist);
						ulist = new Users();
						ulist.getUserList().add(u);
					}
					
					System.out.println(u + " : " +new Date()+" : count : "+count++);
				}
				userListMap.put(mapCounter++,ulist);//for last list
				sentListSixe= count;
				
				d2 = new Date();
				
				System.out.println("hitting web server");
				
				//ht = new HttpEntity<Users>( ulist,headers);
				
				
				for(Map.Entry<Integer,Users> entry : userListMap.entrySet())
				{
					tempUserList = entry.getValue();
				
				
				RequestEntity<Users> request = RequestEntity
						     .post(new URI("http://localhost:8080/spring_rest_server/xml/postlist"))
						     .accept(MediaType.APPLICATION_XML)
						     .body(tempUserList);
				
				ResponseEntity<Users> responseUserList = re.exchange(request, Users.class);
				responseListSixe = responseListSixe + responseUserList.getBody().getUserList().size();
				
				System.out.println("responseUserList : "+responseUserList.getBody().getUserList().size() + "responseUserList");
				
				for(User u : responseUserList.getBody().getUserList())
					System.out.println(u.toString());
				
				}
				
				d4 = new Date();
				System.out.println("sentListSixe.... : "+sentListSixe);
				System.out.println("responseListSixe : "+responseListSixe);
				System.out.println("CSV Upload/Reading Starts...................................: " +d1);
				System.out.println("CSV Upload/Reading ends.. webservice hit starts.............: " +d2);
				System.out.println("response iteration ends.....................................: " +d4);
				
				
				
				/*
				 * ResponseEntity<Users> ul = re. re.getForEntity(
				 * "http://localhost:8080/day14_spring_rest_server/xml/postlist",
				 * ht,Users.class);
				 * postForObject("http://localhost:8080/day14_spring_rest_server/xml/postlist",
				 * ht, String.class);
				 * 
				 * re.
				 */
					
					
			} catch (Exception e){e.printStackTrace();
			} finally {
				
					if (br != null)
						try {
							br.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			}

		} else 
		{
			System.out.println("file is null");
		}
		return response;
	}
}
