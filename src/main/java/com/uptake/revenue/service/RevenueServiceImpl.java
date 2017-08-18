package com.uptake.revenue.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;

import com.uptake.revenue.entities.Customer;
import com.uptake.revenue.entities.DateBasedRevenue;
import com.uptake.revenue.entities.MonthRevenue;
import com.uptake.revenue.entities.PreviousYear;
import com.uptake.revenue.entities.Revenue;
import com.uptake.revenue.entities.Type;
import com.uptake.revenue.entities.User;
import com.uptake.revenue.entities.YearRevenue;
import com.uptake.revenue.exception.BadArgumentException;
import com.uptake.revenue.repository.RevenueRepository;
import com.uptake.revenue.repository.RevenueRepositoryCustom;
import com.uptake.revenue.repository.UserRepository;
import com.uptake.revenue.util.Constants;

@org.springframework.stereotype.Service
public class RevenueServiceImpl implements IRevenueService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RevenueRepository rRepo;

	@Autowired
	RevenueRepositoryCustom revenueRepository;

	@Override
	public User loginApi(User user) {
		User userNew = userRepository.findByUsername(user.getUsername().trim());
		if (userNew == null) {
			throw new BadArgumentException(Constants.USER_NOT_EXIST);
		} else if (!userNew.getPassword().equals(user.getPassword())) {
			throw new BadArgumentException(Constants.WRONG_PASSWORD);
		}
		return userNew;
	}

	@Override
	public Revenue revenueApi(String userId) {
		Customer customer = rRepo.findCurrencyByUserid(userId);

		Revenue revenue = new Revenue();
		revenue.setUserid(userId);
		revenue.setCurrency(customer.getCurrency());

		DateTime dateT = new DateTime();
		// get first date and last date of the current month (Month-to-Date).
		Date fdatemonth = getFormattedDate(
				Date.from(getCurrentMonthFirstDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		Date ldatemonth = getFormattedDate(
				Date.from(getCurrentMonthLastDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		Long fDateOfMonth = fdatemonth.getTime() / 1000;
		Long lDateOfMonth = DateUtils.addHours(ldatemonth, 24).getTime() / 1000;

		DateBasedRevenue dateBasedRevenue1 = new DateBasedRevenue();
		dateBasedRevenue1.setTimePeriod("Month-to-Date");
		dateBasedRevenue1.setTotalRevenue(
				revenueRepository.findRevenueByDateRange(userId, fDateOfMonth.toString(), lDateOfMonth.toString()));

		// get first date and last date of the Quarter of the current
		// month(Quarter-to-Date).
		Date fdateQuarter = getQuarterStartDate(dateT);
		Date ldateQuarter = getQuarterEndDate(dateT);
		Long fDateOfQuarter = getFormattedDate(fdateQuarter).getTime() / 1000;
		Long lDateOfQuarter = getFormattedDate(ldateQuarter).getTime() / 1000;

		DateBasedRevenue dateBasedRevenue2 = new DateBasedRevenue();
		dateBasedRevenue2.setTimePeriod("Quarter-to-Date");
		dateBasedRevenue2.setTotalRevenue(
				revenueRepository.findRevenueByDateRange(userId, fDateOfQuarter.toString(), lDateOfQuarter.toString()));

		// get first date and last date of the current year (Year-to-Date).
		DateTime firstDayOfYear = getFirstDateOfYear(dateT.getYear());
		Date fdateyear = firstDayOfYear.toDate();
		Date ldateyear = getLastDateOfYear(firstDayOfYear.plusYears(1));
		Long fDateOfYear = fdateyear.getTime() / 1000;
		Long lDateOfYear = ldateyear.getTime() / 1000;

		DateBasedRevenue dateBasedRevenue3 = new DateBasedRevenue();
		dateBasedRevenue3.setTimePeriod("Year-to-Date");
		dateBasedRevenue3.setTotalRevenue(
				revenueRepository.findRevenueByDateRange(userId, fDateOfYear.toString(), lDateOfYear.toString()));

		List<DateBasedRevenue> list = new ArrayList<DateBasedRevenue>();
		list.add(dateBasedRevenue1);
		list.add(dateBasedRevenue2);
		list.add(dateBasedRevenue3);
		revenue.setRevenue(list);

		return revenue;
	}

	@Override
	public MonthRevenue monthRevenueApi(String userId) {

		MonthRevenue monthRevenue = new MonthRevenue();
		monthRevenue.setUserid(userId);

		// get first date and last date of the current month (Month-to-Date).
		Date fdatemonth = getFormattedDate(
				Date.from(getCurrentMonthFirstDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		Date ldatemonth = getFormattedDate(
				Date.from(getCurrentMonthLastDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		Long fDateOfMonth = fdatemonth.getTime() / 1000;
		Long lDateOfMonth = DateUtils.addHours(ldatemonth, 24).getTime() / 1000;

		List<Type> typeList = listOfTypeBasedRevenue(userId, fDateOfMonth.toString(), lDateOfMonth.toString());
		monthRevenue.setType(typeList);

		int totalRevenue = 0;
		for (int i = 0; i < typeList.size(); i++) {
			totalRevenue = totalRevenue + Integer.parseInt(typeList.get(i).getRevenue());
		}
		monthRevenue.setTotalRevenue(String.valueOf(totalRevenue));

		Customer customer = rRepo.findCurrencyByUserid(userId);
		monthRevenue.setCurrency(customer.getCurrency());

		List<Customer> listOfCustomer = revenueRepository.findCustomersByUserIdAndDate(userId, fDateOfMonth.toString(),
				lDateOfMonth.toString());
		monthRevenue.setCustomers(listOfCustomer);

		return monthRevenue;
	}

	@Override
	public MonthRevenue quarterRevenueApi(String userId) {

		MonthRevenue monthRevenue = new MonthRevenue();
		monthRevenue.setUserid(userId);

		DateTime dateT = new DateTime();
		// get first date and last date of the Quarter of the current
		// month(Quarter-to-Date).
		Date fdateQuarter = getQuarterStartDate(dateT);
		Date ldateQuarter = getQuarterEndDate(dateT);
		Long fDateOfQuarter = getFormattedDate(fdateQuarter).getTime() / 1000;
		Long lDateOfQuarter = getFormattedDate(ldateQuarter).getTime() / 1000;

		List<Type> typeList = listOfTypeBasedRevenue(userId, fDateOfQuarter.toString(), lDateOfQuarter.toString());
		monthRevenue.setType(typeList);

		int totalRevenue = 0;
		for (int i = 0; i < typeList.size(); i++) {
			totalRevenue = totalRevenue + Integer.parseInt(typeList.get(i).getRevenue());
		}
		monthRevenue.setTotalRevenue(String.valueOf(totalRevenue));

		Customer customer = rRepo.findCurrencyByUserid(userId);
		monthRevenue.setCurrency(customer.getCurrency());

		List<Customer> listOfCustomer = revenueRepository.findCustomersByUserIdAndDate(userId,
				fDateOfQuarter.toString(), lDateOfQuarter.toString());
		monthRevenue.setCustomers(listOfCustomer);

		return monthRevenue;
	}

	private List<Type> listOfTypeBasedRevenue(String userId, String fDateOfMonth, String lDateOfMonth) {
		List<Type> typeList = new ArrayList<Type>();

		Type typeSales = new Type();
		typeSales.setType(Constants.TYPE_SALES);
		typeSales.setRevenue(revenueRepository.findRevenueByType(userId, fDateOfMonth.toString(),
				lDateOfMonth.toString(), Constants.TYPE_SALES));
		typeList.add(typeSales);

		Type typeRental = new Type();
		typeRental.setType(Constants.TYPE_RENTAL);
		typeRental.setRevenue(revenueRepository.findRevenueByType(userId, fDateOfMonth.toString(),
				lDateOfMonth.toString(), Constants.TYPE_RENTAL));
		typeList.add(typeRental);

		Type typeLabor = new Type();
		typeLabor.setType(Constants.TYPE_LABOR);
		typeLabor.setRevenue(revenueRepository.findRevenueByType(userId, fDateOfMonth.toString(),
				lDateOfMonth.toString(), Constants.TYPE_LABOR));
		typeList.add(typeLabor);

		Type typeParts = new Type();
		typeParts.setType(Constants.TYPE_PARTS);
		typeParts.setRevenue(revenueRepository.findRevenueByType(userId, fDateOfMonth.toString(),
				lDateOfMonth.toString(), Constants.TYPE_PARTS));
		typeList.add(typeParts);

		return typeList;
	}

	@Override
	public YearRevenue yearRevenueApi(String userId) {
		YearRevenue yearRevenue = new YearRevenue();
		yearRevenue.setUserid(userId);

		DateTime dateT = new DateTime();
		// get first date and last date of the current year (Year-to-Date).
		DateTime firstDayOfCurrentYear = getFirstDateOfYear(dateT.getYear());
		Date fDateCurrentYear = firstDayOfCurrentYear.toDate();
		Date lDateCurrentYear = getLastDateOfYear(firstDayOfCurrentYear.plusYears(1));
		Long fUnixDateCurrentYear = fDateCurrentYear.getTime() / 1000;
		Long lUnixDateOfCurrentYear = lDateCurrentYear.getTime() / 1000;

		List<Type> typeList = listOfTypeBasedRevenue(userId, fUnixDateCurrentYear.toString(),
				lUnixDateOfCurrentYear.toString());
		yearRevenue.setType(typeList);

		int totalRevenue = 0;
		for (int i = 0; i < typeList.size(); i++) {
			totalRevenue = totalRevenue + Integer.parseInt(typeList.get(i).getRevenue());
		}
		yearRevenue.setTotalRevenue(String.valueOf(totalRevenue));

		Customer customer = rRepo.findCurrencyByUserid(userId);
		yearRevenue.setCurrency(customer.getCurrency());

		List<PreviousYear> listPreviousYear = new ArrayList<PreviousYear>();

		String smallDate = revenueRepository.findSmallestDateByUserId(userId);
		Long smallDateLong = Long.parseLong(smallDate);
		DateTime sDate = new DateTime((long) smallDateLong);
		int i = 1;
		int smallYear = sDate.plusYears(i).getYear();
		while (dateT.getYear() > smallYear) {

			// get first date and last date of the current year (Year-to-Date).
			PreviousYear previousYear = new PreviousYear();

			int year = dateT.minusYears(i).getYear();
			DateTime firstDayOfPreviousYear = getFirstDateOfYear(year);
			Date fDatePreviousYear = firstDayOfPreviousYear.toDate();
			Date lDatePreviousYear = getLastDateOfYear(firstDayOfPreviousYear.plusYears(1));
			Long fUnixDatePreviousYear = fDatePreviousYear.getTime() / 1000;
			Long lUnixDatePreviousYear = lDatePreviousYear.getTime() / 1000;

			previousYear.setYear(String.valueOf(year));
			previousYear.setRevenue(revenueRepository.findRevenueByYear(userId, fUnixDatePreviousYear.toString(),
					lUnixDatePreviousYear.toString()));
			listPreviousYear.add(previousYear);
			smallYear = sDate.plusYears(i++).getYear();
		}
		yearRevenue.setPreviousYear(listPreviousYear);

		return yearRevenue;
	}

	private Date getQuarterStartDate(DateTime date) {
		return date.withMonthOfYear(((date.getMonthOfYear() / 3) + 1) * 3 - 2).withDayOfMonth(1).withTimeAtStartOfDay()
				.toDate();
	}

	private Date getQuarterEndDate(DateTime date) {
		Date endDay = date.withMonthOfYear(((date.getMonthOfYear() / 3) + 1) * 3)
				.withDayOfMonth(Month.of(((date.getMonthOfYear() / 3) + 1) * 3).maxLength()).withTimeAtStartOfDay()
				.toDate();
		return DateUtils.addHours(endDay, 24);
	}

	public static LocalDate getCurrentMonthFirstDate() {
		return LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000)).withDayOfMonth(1);
	}

	public static LocalDate getCurrentMonthLastDate() {
		return LocalDate.ofEpochDay(System.currentTimeMillis() / (24 * 60 * 60 * 1000)).plusMonths(1).withDayOfMonth(1)
				.minusDays(1);
	}

	private DateTime getFirstDateOfYear(int year) {
		return new DateTime(year, DateTimeConstants.JANUARY, 1, 0, 0, DateTimeZone.forID("GMT"));
	}

	private Date getLastDateOfYear(DateTime firstOfNextYear) {
		return DateUtils.addHours(firstOfNextYear.minusDays(1).toDate(), 24);
	}

	private Date getFormattedDate(Date date) {
		DateFormat readFormat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		DateFormat writeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date newDate = null;
		try {
			newDate = readFormat.parse(date.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String formattedDateStr = "";
		if (newDate != null) {
			formattedDateStr = writeFormat.format(newDate);
		}

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date formattedDate = null;
		try {
			formattedDate = dateFormat.parse(formattedDateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return formattedDate;
	}
}
