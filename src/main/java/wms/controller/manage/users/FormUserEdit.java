package wms.controller.manage.users;

import javax.validation.constraints.Size;

import wms.model.user.User;

public class FormUserEdit {
	
	@Size(min=1, max=50)
	private String username;
	@Size(min=1, max=75)
	private String name;
	private int id;
	private boolean active;
	
	public FormUserEdit() {
	
	}
	
	public FormUserEdit(User user) {
		this.fillWithUserInstance(user);
	}
	
	public void fillWithUserInstance(User user) {
		this.setUsername(user.getUsername());
		this.setName(user.getName());
		this.setId(user.getId());
		this.setActive(user.isActive());
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "FormUserEdit [username=" + username + ", name=" + name + ", id=" + id + ", active=" + active + "]";
	}
	
	
}
