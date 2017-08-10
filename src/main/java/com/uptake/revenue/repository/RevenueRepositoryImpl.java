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

import com.uptake.revenue.entities.TotalRevenue;

@Component
@Repository
public class RevenueRepositoryImpl implements RevenueRepositoryCustom {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public String getRevenueByDateRange(String userId, String firstDate, String lastDate) {
		Aggregation agg = newAggregation(match(Criteria.where("date").gte(firstDate).lte(lastDate)),
				match(Criteria.where("userid").is(userId)), group(userId).sum("revenue").as("totalRevenue"));
		AggregationResults<TotalRevenue> result = mongoTemplate.aggregate(agg, "revenue_new", TotalRevenue.class);
		List<TotalRevenue> list = result.getMappedResults();
		return list.get(0).getTotalRevenue();
	}
}
