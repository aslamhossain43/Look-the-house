package com.renu.look.house.web.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.renu.look.house.models.Role;
import com.renu.look.house.models.User;
import com.renu.look.house.web.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	private static final Logger LOGGER=LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOGGER.info("From class UserDetailsServiceImpl ,method : loadUserByUsername()");
		User user = userRepository.findByUsername(username);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
		for (Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), 
																	  user.getPassword(),
																	  grantedAuthorities);

	}
}
