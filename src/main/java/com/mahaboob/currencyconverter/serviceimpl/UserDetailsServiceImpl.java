package com.mahaboob.currencyconverter.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mahaboob.currencyconverter.doa.UserDetailsDao;
import com.mahaboob.currencyconverter.domain.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserDetailsDao defaultAuthentictionDao;

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		User user = defaultAuthentictionDao.findByUsername(userName);
		
		if(user != null){
			return user;
		}
		
		throw new UsernameNotFoundException("Invalid username or password.");
	}

}
