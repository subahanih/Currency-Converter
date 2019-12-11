package com.mahaboob.currencyconverter.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mahaboob.currencyconverter.doa.UserDao;
import com.mahaboob.currencyconverter.domain.User;
import com.mahaboob.currencyconverter.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional(readOnly = true)
	@PreAuthorize("hasAuthority('admin-read-user')")
	public List<User> findAll() {
		return userDao.findAll();
	}

}
