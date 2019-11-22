package pojos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "User")
public class User {

	String id;
	String name;
	String ifsc;
	String accNo;
	
	public User() {
		
	}
	
	public User(String id, String name, String ifsc, String accNo) {
		super();
		this.id = id;
		this.name = name;
		this.ifsc = ifsc;
		this.accNo = accNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", ifsc=" + ifsc + ", accNo=" + accNo + "]";
	}

	
	
}
