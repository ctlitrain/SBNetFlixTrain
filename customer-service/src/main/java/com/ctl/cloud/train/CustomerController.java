package com.ctl.cloud.train;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class CustomerController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static String accountSvc = "ACCOUNT-SERVICE";
	
    @Autowired
    private DiscoveryClient discoveryClient;
    
	@Autowired
	private RestTemplate restcall;
	
    @Autowired
    private AccountClient accountClient;

	@Bean
	public RestTemplate getTemplate() {
		return new RestTemplate();			
	}
	
	@HystrixCommand(fallbackMethod="getFallbackCust")
	@RequestMapping("/customer/{id}")
	public String customerAccount(@PathVariable String  id) {		
		logger.info("Inside Customer-serice GET call");
		String customer= "Customer Detail from this service for id: " + id + "\n";
		String account = restcall.getForObject("http://localhost:61318/account/" + id, String.class);		
		return customer +  account;
	}
	
	
	@RequestMapping("/discover/customer/{id}")
	public String discoverAccount(@PathVariable String  id) {		
		logger.info("Inside discoverAccount GET call");
		List<ServiceInstance> list = discoveryClient.getInstances(accountSvc);
		
		String accountURL = "http://";
		if (list != null && list.size() > 0 ) {
			accountURL = accountURL + list.get(0).getHost();
			accountURL = accountURL + ":"  + list.get(0).getPort();
		} 
		
		logger.info("Inside discoverAccount GET call , URL="+ accountURL);
		String customer= "Customer Detail with invoking via discoveryClient for id: " + id + "\n";
		String account = restcall.getForObject(accountURL+"/account/" + id, String.class);
		return customer +  account;
	}
	
	@HystrixCommand(fallbackMethod="getFallbackCust")
	@RequestMapping("/fiegn/customer/{id}")
	public String fiegnAccount(@PathVariable String  id) {		
		logger.info("Inside fiegnAccount GET call");
		String customer= "Customer Detail with invoking via fiegnAccount for id: " + id + "\n";
		String account = accountClient.getAccounts(id);
		return customer +  account;
	}
	
	
	public String getFallbackCust(@PathVariable("id") String id) {
		logger.info("Inside getFallbackCust default method");		
		String customer= "Customer default Detail from this service for id: " + id + "\n";
		String account = "Account default Details for id:" + id; 
		return customer +  account; 		
	}
	
}
