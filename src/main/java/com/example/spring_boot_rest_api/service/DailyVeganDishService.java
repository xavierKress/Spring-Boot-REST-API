package com.example.spring_boot_rest_api.service;

import com.example.spring_boot_rest_api.aspects.ExecutionTimeMonitoring;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Primary
@Profile("vegetarian")
public class DailyVeganDishService implements IDishService {

	@Override
	@ExecutionTimeMonitoring(operation = "get daily vegan dish")
	public String getName() {
		return "Daily Vegan Dish";
	}
}
