package com.example.spring_boot_rest_api.bootstrap;

import com.example.spring_boot_rest_api.component.ClientOrderComponent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap {

	private final ClientOrderComponent clientOrder;

	public DevBootstrap(ClientOrderComponent clientOrder) {
		this.clientOrder = clientOrder;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void initData() {
		clientOrder.securedMethod();
	}
}
