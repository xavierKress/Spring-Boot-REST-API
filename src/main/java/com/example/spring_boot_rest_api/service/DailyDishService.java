package com.example.spring_boot_rest_api.service;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Primary
@Profile("carnivorous")
public class DailyDishService implements IDishService{

	@Override
	public String getName() {
		return "Daily Dish";
	}
}
