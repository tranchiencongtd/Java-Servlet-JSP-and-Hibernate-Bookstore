package com.bookstore.entity;
// Generated Feb 11, 2023, 10:58:22 AM by Hibernate Tools 4.3.6.Final

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "users", catalog = "bookstoredb")
@NamedQueries({
	  @NamedQuery(name = "Users.findAll", query = "SELECT U FROM Users U ORDER BY U.fullName")
	 ,@NamedQuery(name = "Users.countAll", query = "SELECT COUNT(*) FROM Users")
	 ,@NamedQuery(name = "Users.findByEmail", query = "SELECT U FROM Users U where U.email = :email")
})
public class Users implements java.io.Serializable {

	private Integer userId;
	private String email;
	private String password;
	private String fullName;

	public Users() {
	}

	public Users(String email, String password, String fullName) {
		this.email = email;
		this.password = password;
		this.fullName = fullName;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "email", nullable = false, length = 30)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false, length = 16)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "full_name", nullable = false, length = 30)
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
