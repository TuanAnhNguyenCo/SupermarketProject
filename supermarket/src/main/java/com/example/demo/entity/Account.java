package com.example.demo.entity;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@Table(catalog = "marketDB",name = "Account")
public class Account {
	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "Username",length = 30,unique = true)
	private String username;
	
	@Column(name = "Password",length = 30)
	private String password;
	
	@Column(name = "Role")
	@ColumnDefault("false")
	private boolean role;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role roleOfUser;
}
