package pojos;

import java.util.concurrent.Callable;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class ThreadClass implements Callable<String> 
{
	User user;	
	RestTemplate re = new RestTemplate();
	String result;
	 HttpHeaders headers;
	
	public ThreadClass() {}
	public ThreadClass(User user,  HttpHeaders headers) 
	{
		super();
		this.user=user;
		this.headers=headers;
	}

	@Override
	public String call() throws Exception 
	{
		// TODO Auto-generated method stub
		HttpEntity<User> ht = new HttpEntity<User>(this.user,headers);
		return re.postForObject("http://localhost:8080/day14_spring_rest_server/xml/post",ht, String.class);
	}

}
