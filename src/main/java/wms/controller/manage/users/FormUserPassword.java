package wms.controller.manage.users;

import wms.model.user.User;

public class FormUserPassword {

	private int id;
	private String username;
	private String password;
	private String passwordRepeat;
	;
	
	public FormUserPassword() {
	
	}
	
	public FormUserPassword(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordRepeat() {
		return passwordRepeat;
	}

	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}

	@Override
	public String toString() {
		return "FormUserPassword [id=" + id + ", username=" + username + ", password=" + password + ", passwordRepeat="
				+ passwordRepeat + "]";
	}

	
}


