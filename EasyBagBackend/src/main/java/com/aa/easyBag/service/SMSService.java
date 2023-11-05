package com.aa.easyBag.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SMSService {
    @Value("${twilio.account_sid}")
    private String twilioSid;

    @Value("${twilio.auth_token}")
    private String twilioAuthToken;

    @Value("${twilio.phone_number}")
    private String twilioPhoneNumber;

    public void sendSMS(String to, String message) {
        Twilio.init(twilioSid, twilioAuthToken);

        Message sms = Message.creator(
                new PhoneNumber(to), // To number
                new PhoneNumber(twilioPhoneNumber), // From number (Your Twilio number)
                message // SMS body
        ).create();

        System.out.println("Sent message with ID: " + sms.getSid());
    }
}
