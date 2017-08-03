package com.uptake.revenue.controller;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.uptake.revenue.controller.api.Link;
import com.uptake.revenue.controller.api.JsonApiWrapper;
import com.uptake.revenue.entities.User;
import com.uptake.revenue.repository.UserRepository;
import com.uptake.revenue.service.IRevenueService;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
public class RevenueController extends BaseController {

	@Autowired
	IRevenueService revenueService;

	/**
	 * This REST API is designed to Login into Invoice and Revenue Application
	 * 
	 * @param login
	 *            This is the only request parameter.
	 * @param request
	 *            This parameter is passed by the spring container.
	 * @param response
	 *            This parameter is passed by the spring container.
	 * @return Return Login object
	 * @throws Exception
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Login Page ", response = User.class)
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonApiWrapper<User> login(@ApiIgnore UriComponentsBuilder builder, @RequestBody User user,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		checkArguments(user.getUserName());
		
		User userNew = revenueService.loginApi(user);
		
		checkArguments(userNew);
		
		String tmp = builder.path("/login").build().toString();
		Link l1 = new Link(tmp, " User Detail");

		return new JsonApiWrapper(userNew, getSelfLink(request), Arrays.asList(l1));
	}
}
