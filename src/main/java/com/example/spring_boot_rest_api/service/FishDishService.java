package com.example.spring_boot_rest_api.service;

import org.springframework.stereotype.Service;

@Service
public class FishDishService implements IDishService {
	@Override
	public String getName() {
		return "Fish Dish";
	}
}
