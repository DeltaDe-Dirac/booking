package com.cardlatch.hotel.services;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.cardlatch.hotel.entities.cust.SMS;
import com.twilio.Twilio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
public class SMSService {
	private static final Logger LOG = LoggerFactory.getLogger(SMSService.class);

	@Autowired
	PropertyServiceForJasyptStarter propService;

	public void send(SMS sms) {
		Twilio.init(propService.getAccountSid(), propService.getAuthToken());

		Message message = Message
				.creator(new PhoneNumber(sms.getTo()), new PhoneNumber(propService.getFromNumber()), sms.getMessage())
				.create();
		LOG.info("Unique resource ID created to manage this transaction: {}", message.getSid());

	}

	public void receive(MultiValueMap<String, String> smscallback) {
		// Do nothing because ...
	}
}
