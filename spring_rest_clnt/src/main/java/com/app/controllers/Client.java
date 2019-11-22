package com.app.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
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

import pojos.RunnableClass;
import pojos.ThreadClass;
import pojos.User;

@Controller
@RequestMapping("/client")
public class Client 
{

	@RequestMapping(value = "/hit", method = RequestMethod.POST)
	@ResponseBody
	public String hitService(@RequestParam("myFile") MultipartFile myFile) 
	{
		String response = "";
		
		if (myFile != null) 
		{
			RestTemplate re = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/xml");
			//HttpEntity<User> ht;// = new HttpEntity<User>(new User("1","er"),headers);
			ArrayList<Future<String>> arr = new ArrayList<>();
			ArrayList<String> stringList = new ArrayList<>();
			
			try {
				ExecutorService pool = Executors.newCachedThreadPool();
				
				System.out.println("file is not null");

				BufferedReader br = null;
				String[] b;
				try {

					String line;
					InputStream is = myFile.getInputStream();
					br = new BufferedReader(new InputStreamReader(is));
					User u ;
					int countOfLines=0;
					String t ;
					Date d = new Date();
					List<User> userList = new ArrayList<User>();
					
					
					while ((line = br.readLine()) != null) 
					{
						
						b = line.split(",");
						u = new User(b[0],b[1],b[2],b[3]);
						System.out.println("count "+countOfLines);
						//userList.add(u);
						//HttpEntity<User> ht = new HttpEntity<User>(u,headers);
						//t =  re.postForObject("http://localhost:8080/day14_spring_rest_server/xml/post",ht, String.class);
						//	arr1.add(t);
						 pool.execute(new RunnableClass(u,headers));
						 System.out.println(new Date()+" count "+countOfLines++);
					}
					System.out.println(d);
					
					
					/*
					for(User usr : userList)
					{
						System.out.println("count "+countOfLines++);
						
						
						
						
						  Callable<String> task = new ThreadClass(usr,headers); Future<String> future =
						  executor.submit(task); String futureResult = null;
						 
						
						
						  try { futureResult = future.get(); } catch (InterruptedException |
						  ExecutionException e) { e.printStackTrace(); }
						  
						  stringList.add(futureResult); System.out.println(new Date() +
						  "  "+countOfLines);
						 
					
					}*/
						
					/*
					 * while (userList.size() != countOfLines) { }
					 */
					
					/*
					 * if (!eS.isShutdown()) eS.shutdown();
					 */	
					
				/*	for (Future<String> f : userList) 
					{
						try {
							response += "\n" + f.get();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
*/
				} catch (IOException e) {
					System.err.println(e.getMessage());
				} finally {
					if (br != null)
						br.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("file is null");
		}
		
		return response;
	}
}
