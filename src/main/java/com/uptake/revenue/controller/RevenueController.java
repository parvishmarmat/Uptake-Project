package com.uptake.revenue.controller;

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
import com.uptake.revenue.entities.MonthRevenue;
import com.uptake.revenue.entities.Revenue;
import com.uptake.revenue.entities.User;
import com.uptake.revenue.entities.YearRevenue;
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
	 * @param user
	 *            This is the only request parameter.
	 * @param request
	 *            This parameter is passed by the spring container.
	 * @param response
	 *            This parameter is passed by the spring container.
	 * @return Return Login object
	 * @throws Exception
	 */
	// @CrossOrigin(origins = "http://localhost:8080/InvoiceAndRevenue")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Login Page ", response = User.class)
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonApiWrapper<User> login(@ApiIgnore UriComponentsBuilder builder, @RequestBody User user,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		checkArguments(user.getUsername().trim());

		User userNew = revenueService.loginApi(user);

		checkArguments(userNew);

		String tmp = builder.path("/login").build().toString();
		Link l1 = new Link(tmp, "User Detail");

		return new JsonApiWrapper(userNew, getSelfLink(request), Arrays.asList(l1));
	}

	/**
	 * This REST API is designed to get the totsl revenue of Month-to-Date,
	 * Quarter-to-Date and Year-to-Date based on the user.
	 * 
	 * @param revenue
	 *            This is the only request parameter.
	 * @param request
	 *            This parameter is passed by the spring container.
	 * @param response
	 *            This parameter is passed by the spring container.
	 * @return returns the Revenue object
	 * @throws Exception
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = " Revenue Page ", response = Revenue.class)
	@RequestMapping(value = "/revenue", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonApiWrapper<Revenue> revenue(@ApiIgnore UriComponentsBuilder builder, @RequestBody Revenue revenue,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		checkArguments(revenue.getUserid().trim());

		Revenue newRevenue = revenueService.revenueApi(revenue.getUserid());

		checkArguments(newRevenue);

		String tmp = builder.path("/revenue").build().toString();
		Link l1 = new Link(tmp, "Revenue Detail");

		return new JsonApiWrapper(newRevenue, getSelfLink(request), Arrays.asList(l1));
	}

	/**
	 * This REST API is designed to get the revenue of the current Year
	 * (Year-to-Date) and all previous year based on the user.
	 * 
	 * @param yearRevenue
	 *            This is the only request parameter.
	 * @param request
	 *            This parameter is passed by the spring container.
	 * @param response
	 *            This parameter is passed by the spring container.
	 * @return returns the YearRevenue object
	 * @throws Exception
	 */
	@ApiOperation(value = " Revenue Year-to-Date Page ", response = YearRevenue.class)
	@RequestMapping(value = "/revenueYearToDate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonApiWrapper<YearRevenue> revenueYearToDate(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody YearRevenue yearRevenue, HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		checkArguments(yearRevenue.getUserid().trim());

		YearRevenue newYearRevenue = revenueService.yearRevenueApi(yearRevenue.getUserid());

		checkArguments(newYearRevenue);

		String tmp = builder.path("/revenueYearToDate").build().toString();
		Link l1 = new Link(tmp, "Revenue Year-to-Date Detail");

		return new JsonApiWrapper(newYearRevenue, getSelfLink(request), Arrays.asList(l1));
	}

	/**
	 * This REST API is designed to get the revenue of the current Month
	 * (Month-to-Date)and all previous year based on the user.
	 * 
	 * @param monthRevenue
	 *            This is the only request parameter.
	 * @param request
	 *            This parameter is passed by the spring container.
	 * @param response
	 *            This parameter is passed by the spring container.
	 * @return returns the MonthRevenue object
	 * @throws Exception
	 */
	@ApiOperation(value = " Revenue Month-to-Date Page ", response = MonthRevenue.class)
	@RequestMapping(value = "/revenueMonthToDate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonApiWrapper<MonthRevenue> revenueForMonth(@ApiIgnore UriComponentsBuilder builder,
			@RequestBody MonthRevenue monthRevenue, HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		checkArguments(monthRevenue.getUserid().trim());

		MonthRevenue newMonthRevenue = revenueService.monthRevenueApi(monthRevenue.getUserid());

		checkArguments(newMonthRevenue);

		String tmp = builder.path("/revenueForMonth").build().toString();
		Link l1 = new Link(tmp, "RevenueForMonth Detail");

		return new JsonApiWrapper(newMonthRevenue, getSelfLink(request), Arrays.asList(l1));
	}
}
