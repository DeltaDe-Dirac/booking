package com.cardlatch.hotel;

import com.cardlatch.hotel.entities.cust.SMS;
import com.cardlatch.hotel.services.RepoHelperService;
import com.cardlatch.hotel.services.SMSService;
import com.twilio.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class SMSController {

	@Autowired
	SMSService service;
	@Autowired
	RepoHelperService repoHelper;

	@Autowired
	private SimpMessagingTemplate webSocket;

	private static final String TOPIC_DESTINATION = "/topic/sms";

	@PostMapping(value = "/status", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void smsSubmit(@RequestBody SMS sms) {
		sms.setMessage(repoHelper.getHotelOccupancy());
		try {
			service.send(sms);
		} catch (ApiException e) {

			webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": Error sending the SMS: " + e.getMessage());
			throw e;
		}
		webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": SMS has been sent!: " + sms.getTo());
	}

	@PostMapping(value = "/smscallback", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void smsCallback(@RequestBody MultiValueMap<String, String> map) {
		service.receive(map);
		webSocket.convertAndSend(TOPIC_DESTINATION,
				getTimeStamp() + ": Twilio has made a callback request! Here are the contents: " + map.toString());
	}

	private String getTimeStamp() {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
	}
}
