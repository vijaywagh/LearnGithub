package org.ascent.stripes.model;

import javax.persistence.*;

@Entity
@Table (name="login")
public class Login {

	@Id
	@GeneratedValue
	private long id;
	@Column (name="user_name")
	private String userName;
	private String password;
	@Override
	public String toString() {
		return "Login [id=" + id + ", userName=" + userName + ", password="
				+ password + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
