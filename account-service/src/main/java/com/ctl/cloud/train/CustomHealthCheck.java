package com.ctl.cloud.train;


import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthCheck implements HealthIndicator {
   int errorCode = 0;
	
	@Override
	public Health health() {
		// TODO Auto-generated method stub
		System.out.println("health check . error code:"+errorCode); 
		if(errorCode >4 && errorCode <10) {
			errorCode++;
			return Health.down().withDetail("Error Code",errorCode).build();
		}else
			errorCode++;
		
		return Health.up().build();  
	}

}
