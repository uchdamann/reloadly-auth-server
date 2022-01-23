package com.reloadly.devops.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.reloadly.devops.exceptions.AuthException;
import com.reloadly.devops.models.User;
import com.reloadly.devops.repositories.UserRepo;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepo.findByUsername(username);

		if (!user.isPresent()) {
			throw new AuthException("Username does not exist");
		}
		
//		This is to ensure that you cannot use postman etc and get token if your account is not activated
		if(!user.get().getIsActive()) {
			throw new AuthException("User has not been activated");
		}

		return new UserDetailsClass(user.get());
	}
}
