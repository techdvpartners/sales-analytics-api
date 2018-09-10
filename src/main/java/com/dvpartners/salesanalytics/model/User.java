package com.dvpartners.salesanalytics.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@lombok.Getter
@lombok.Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	private String username;
	private String password;
	private String role;
}
