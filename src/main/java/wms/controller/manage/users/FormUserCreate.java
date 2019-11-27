package wms.controller.manage.users;

import javax.validation.constraints.Size;

public class FormUserCreate {
	
	@Size(min=1, max=50)
	private String username;
	@Size(min=1, max=75)
	private String name;
	
	public FormUserCreate() {
	
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "FormUserCreate [username=" + username + ", name=" + name + "]";
	}
	
		
}
