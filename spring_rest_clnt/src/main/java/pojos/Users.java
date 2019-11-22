package pojos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Users")
public class Users {

	ArrayList<User> userList = new ArrayList<User>() ;

	public ArrayList<User> getUserList() {
		return userList;
	}

	@XmlElement(name = "User")
	public void setUserList(ArrayList<User> arr) {
		this.userList = arr;
	}

	@Override
	public String toString() {
		return "Users [userList=" + userList + "]";
	}
	
	
}
