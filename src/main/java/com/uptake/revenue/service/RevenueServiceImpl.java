package com.uptake.revenue.service;

import java.util.Date;
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
import org.springframework.data.mongodb.core.MongoTemplate;

import com.uptake.revenue.entities.Revenue;
import com.uptake.revenue.entities.User;
import com.uptake.revenue.exception.BadArgumentException;
import com.uptake.revenue.repository.RevenueRepositoryCustom;
import com.uptake.revenue.repository.UserRepository;
import com.uptake.revenue.util.Constants;

@org.springframework.stereotype.Service
public class RevenueServiceImpl implements IRevenueService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RevenueRepositoryCustom revenueRepository;

	public User loginApi(User user) {
		User userNew = userRepository.findByUsername(user.getUsername().trim());
		if (userNew == null) {
			throw new BadArgumentException(Constants.USER_NOT_EXIST);
		} else if (!userNew.getPassword().equals(user.getPassword())) {
			throw new BadArgumentException(Constants.WRONG_PASSWORD);
		}
		return userNew;
	}

	public Revenue revenueApi(String userId) {
		Revenue revenue = new Revenue();
		revenue.setUserid(userId);

		DateTime dateT = new DateTime();
		// get first date and last date of the current month (Month-to-Date).
		Date fdatemonth = getFormattedDate(
				Date.from(getCurrentMonthFirstDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		Date ldatemonth = getFormattedDate(
				Date.from(getCurrentMonthLastDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		Long fDateOfMonth = fdatemonth.getTime() / 1000;
		Long lDateOfMonth = DateUtils.addHours(ldatemonth, 24).getTime() / 1000;

		revenue.setMonthToDate(
				revenueRepository.getRevenueByDateRange(userId, fDateOfMonth.toString(), lDateOfMonth.toString()));

		// get first date and last date of the Quarter of the current month(Quarter-to-Date).
		Date fdateQuarter = getQuarterStartDate(dateT);
		Date ldateQuarter = getQuarterEndDate(dateT);
		Long fDateOfQuarter = getFormattedDate(fdateQuarter).getTime() / 1000;
		Long lDateOfQuarter = getFormattedDate(ldateQuarter).getTime() / 1000;

		revenue.setQuarterToDate(
				revenueRepository.getRevenueByDateRange(userId, fDateOfQuarter.toString(), lDateOfQuarter.toString()));

		// get first date and last date of the current year (Year-to-Date).
		DateTime firstDayOfYear = getFirstDateOfYear(dateT.getYear());
		Date fdateyear = firstDayOfYear.toDate();
		Date ldateyear = getLastDateOfYear(firstDayOfYear.plusYears(1));
		Long fDateOfYear = fdateyear.getTime() / 1000;
		Long lDateOfYear = ldateyear.getTime() / 1000;

		revenue.setYearToDate(
				revenueRepository.getRevenueByDateRange(userId, fDateOfYear.toString(), lDateOfYear.toString()));

		return revenue;
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
