package dev.aliaga.login.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateUserResponse{
	private String createdAt;
	private String name;
	private String id;
	private String job;
}
