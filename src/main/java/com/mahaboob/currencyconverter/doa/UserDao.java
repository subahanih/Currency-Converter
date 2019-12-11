package com.mahaboob.currencyconverter.doa;

import java.util.List;
import com.mahaboob.currencyconverter.domain.User;

public interface UserDao {
	
	public List<User> findAll();

}
