package com.example.repositories;

import java.util.Date;
import java.util.HashMap;

import com.example.repositories.DTO.UserDTO;

public class UserRepository {

	public static HashMap<Integer, UserDTO> userDB;


	public UserRepository() {
		// TODO Auto-generated constructor stub
		userDB = new HashMap<Integer, UserDTO>();
		UserDTO  user1 = new UserDTO(); 
		user1.id = 1;
		user1.token = "token1";
		user1.refresh_token = "refresh_token1";
		user1.can_book = true;
		userDB.put(user1.id, user1);
	}

	public Boolean Is_Token_Valid(String token) {
		for(UserDTO user : userDB.values()) {
			if(user.token.equals(token)) {
				if(user.expiration.after(new java.util.Date())) {
					return true;
				}
			}
		}
		return false;
	}

	public UserDTO Refresh_Token(String refreshToken, int userId) {
		UserDTO user = userDB.get(userId);
		user.token = "";
		user.refresh_token = "";
		user.expiration = new java.util.Date();
		user.expiration = new java.util.Date(user.expiration.getYear()+1, user.expiration.getMonth(), user.expiration.getDay());
		return user;

	}
}
