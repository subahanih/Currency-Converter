package com.mahaboob.currencyconverter.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.mahaboob.currencyconverter.domain.User;
import com.mahaboob.currencyconverter.service.UserService;

@RestController
@RequestMapping("/secured")
public class UserController {
	
	@Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<User> getAll() {
        return userService.findAll();
    }

}
