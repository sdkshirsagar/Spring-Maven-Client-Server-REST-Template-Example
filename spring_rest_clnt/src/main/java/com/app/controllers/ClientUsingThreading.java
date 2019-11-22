package com.app.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import pojos.User;

@Controller
@RequestMapping("/cut")
public class ClientUsingThreading 
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
			BufferedReader br = null;
			try 
			{
				InputStream is = myFile.getInputStream();
				br = new BufferedReader(new InputStreamReader(is));
				int count=0;
				Date d = new Date();
				String line;
				List<User> userList = new ArrayList<User>();
				 List <Future<String>> list = new ArrayList<Future<String>>();
				
				
				while ((line = br.readLine()) != null) 
				{
					System.out.println("count on enetering loop : "+count);
					String[] b = line.split(",");
					User u = new User(b[0],b[1],b[2],b[3]);
					userList.add(u);
					
					System.out.println(new Date()+" : count : "+count++);
				}
			
				System.out.println(d);
				
				d = new Date();
				
				
				int i = 0 ;
				 ExecutorService pool = Executors.newCachedThreadPool();
				for (User u : userList) 
				{
					
					 
                     list.add(pool.submit(new Callable<String>()
                    		 {
                    	 		@Override
                    	 		public String   call() 
                    	 		{
                    	 			HttpEntity<User> ht = new HttpEntity<User>(u,headers);
                    	 			return re.postForObject("http://localhost:8080/spring_rest_server/xml/post",ht, String.class);
                    	 			
                    	 		}
                    		 }));
					
					System.out.println(i++ +" : "+new Date());
				}
					
					System.out.println(d);
					
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
