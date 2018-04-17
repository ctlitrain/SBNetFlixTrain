package com.ctl.cloud.train;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/account/{accountId}")
	 public String accountDetail(@PathVariable String accountId) {
		 logger.info("Inside account-service accountDetail:" + accountId );
		return "Account Details for id:" + accountId; 
	 }
	
}
