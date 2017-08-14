package com.uptake.revenue.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import java.util.List;

import com.uptake.revenue.entities.Customer;
import com.uptake.revenue.entities.PreviousYear;
import com.uptake.revenue.entities.TotalRevenue;
import com.uptake.revenue.util.Constants;

@Component
@Repository
public class RevenueRepositoryImpl implements RevenueRepositoryCustom {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public String findRevenueByDateRange(String userId, String firstDate, String lastDate) {
		Aggregation agg = newAggregation(
				match(Criteria.where("date").gte(firstDate).lte(lastDate).and("userid").is(userId)),
				group(userId).sum("revenue").as("totalRevenue"));
		AggregationResults<TotalRevenue> result = mongoTemplate.aggregate(agg, Constants.REVENUE_COLLECTION,
				TotalRevenue.class);
		List<TotalRevenue> list = result.getMappedResults();
		if (list.isEmpty()) {
			return "0";
		}
		return list.get(0).getTotalRevenue();
	}

	@Override
	public String findRevenueByYear(String userId, String firstDate, String lastDate) {
		Aggregation agg = newAggregation(
				match(Criteria.where("date").gte(firstDate).lte(lastDate).and("userid").is(userId)),
				group(userId).sum("revenue").as("revenue"));
		AggregationResults<PreviousYear> result = mongoTemplate.aggregate(agg, Constants.REVENUE_COLLECTION,
				PreviousYear.class);
		List<PreviousYear> list = result.getMappedResults();
		if (list.isEmpty()) {
			return "0";
		}
		return list.get(0).getRevenue();
	}
	
	@Override
	public String findRevenueByType(String userId, String firstDate, String lastDate, String type) {
		Aggregation aggType = newAggregation(match(
				Criteria.where("date").gte(firstDate).lte(lastDate).and("userid").is(userId).and("type").is(type)),
				group(userId).sum("revenue").as("totalRevenue"));
		AggregationResults<TotalRevenue> result = mongoTemplate.aggregate(aggType, Constants.REVENUE_COLLECTION,
				TotalRevenue.class);
		List<TotalRevenue> list = result.getMappedResults();
		if (list.isEmpty()) {
			return "0";
		}
		return list.get(0).getTotalRevenue();
	}

	@Override
	public List<Customer> findCustomerByUserIdAndType(String userId, String firstDate, String lastDate) {
		Aggregation aggType = newAggregation(
				match(Criteria.where("date").gte(firstDate).lte(lastDate).and("userid").is(userId)));
		AggregationResults<Customer> result = mongoTemplate.aggregate(aggType, Constants.REVENUE_COLLECTION,
				Customer.class);
		List<Customer> list = result.getMappedResults();
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}
}
