package com.cardlatch.hotel.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceForJasyptStarter {

	@Value("${db.password}")
	private String dbPassword;

	@Value("${twilio.sid}")
	private String accountSid;

	@Value("${twilio.token}")
	private String authToken;

	@Value("${twilio.number}")
	private String fromNumber;

	public String getDbPassword() {
		return dbPassword;
	}

	public String getAccountSid() {
		return accountSid;
	}

	public String getAuthToken() {
		return authToken;
	}

	public String getFromNumber() {
		return fromNumber;
	}

}
