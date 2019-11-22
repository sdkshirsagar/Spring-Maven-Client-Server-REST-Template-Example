
package pojos;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class RunnableClass implements Runnable 
{
	User user;	
	RestTemplate re = new RestTemplate();
	String result;
	 HttpHeaders headers;
	
	public RunnableClass() {}
	public RunnableClass(User user,  HttpHeaders headers) 
	{
		super();
		this.user=user;
		this.headers=headers;
	}

	
	@Override
	public void run() {
		HttpEntity<User> ht = new HttpEntity<User>(this.user,headers);
		String response =  re.postForObject("http://localhost:8080/day14_spring_rest_server/xml/post",ht, String.class);
		// System.out.println(response);
		
	}

}
